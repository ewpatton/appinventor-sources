// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2009-2011 Google, All Rights reserved
// Copyright © 2011-2019 Massachusetts Institute of Technology, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.blocklyeditor;

import com.google.appinventor.blocklyeditor.api.WorkspaceSettingsProvider;
import com.google.appinventor.blocklyeditor.api.WorkspaceChangeListener;
import com.google.appinventor.blocklyeditor.events.Event;
import com.google.appinventor.blocklyeditor.events.ScreenSwitch;
import com.google.appinventor.blocklyeditor.impl.SimpleSettingsProvider;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import jsinterop.annotations.JsFunction;

/**
 * Blocks editor panel.
 * The contents of the blocks editor panel is in an iframe identified by
 * the formName passed to the constructor. That identifier is also the hashtag
 * on the URL that is the source of the iframe. This class provides methods for
 * calling the Javascript Blockly code from the rest of the Designer.
 *
 * @author sharon@google.com (Sharon Perl)
 */
public class BlocklyPanel extends HTMLPanel {

  private static final Logger LOG = Logger.getLogger(BlocklyPanel.class.getName());

  static {
    BlocklyEditor.ensureTranslationsLoaded(null);
    exportMethodsToJavascript();
  }

  // The currently displayed form (project/screen)
  private static String currentForm;

  // Warning indicator visibility status
  private static boolean isWarningVisible = false;

  // My form name
  private final String formName;

  /**
   * Objects registered to listen for workspace changes.
   */
  private final Set<WorkspaceChangeListener> listeners = new HashSet<>();

  /**
   * Reference to the native Blockly.WorkspaceSvg.
   */
  private WorkspaceSvg workspace;

  /**
   * If true, the loading of the blocks editor has not completed.
   */
  private boolean loadComplete = false;

  /**
   * If true, the loading of the blocks editor resulted in an error.
   */
  private boolean loadError = false;

  private boolean readOnly;

  public BlocklyPanel(long projectId, String formName) {
    this(projectId, formName, false, new SimpleSettingsProvider());
  }

  public BlocklyPanel(long projectId, String formName, boolean readOnly, WorkspaceSettingsProvider settingsProvider) {
    super("");
    getElement().addClassName("svg");
    getElement().setId(projectId + "_" + formName);
    this.formName = formName;
    this.readOnly = readOnly;
    /* Blockly initialization now occurs in three stages. This is due to the fact that certain
     * Blockly objects rely on SVG methods such as getScreenCTM(), which are not properly
     * initialized and/or null prior to the svg element being attached to the DOM. The first
     * stage of initialization happens here.
     *
     * Stages 2 and 3 can occur in different orders depending on network latency. On a fast
     * connection, the second stage will be loading of the .bky content into the workspace.
     * The third stage will then be rendering of the workspace when the user switches to the
     * Blocks editor. On slow connections, the workspace may render blank until the blocks file
     * has been downloaded from the server.
     */
    initWorkspace(settingsProvider);
    LOG.fine("Created BlocklyPanel for " + formName);
  }

  /**
   * Register an object to listen for changes in the Blockly workspace.
   *
   * @param listener A listener for workspace change events
   */
  public void addChangeListener(WorkspaceChangeListener listener) {
    listeners.add(listener);
  }

  /**
   * Unregister an object from listening to changes in the Blockly workspace.
   *
   * @param listener A listener for workspace change events
   */
  public void removeChangeListener(WorkspaceChangeListener listener) {
    listeners.remove(listener);
  }

  /**
   * Notify listeners that an event has occurred in the Blockly workspace.
   *
   * @param event Native JavaScript event object with additional details.
   */
  private void workspaceChanged(Event event) {
    // ignore workspaceChanged events until after the load finishes
    if (!loadComplete) {
      return;
    }
    if (!loadError) {
      for (WorkspaceChangeListener listener : listeners) {
        listener.onWorkspaceChange(this, event);
      }
    }
  }

  public WorkspaceSvg getWorkspace() {
    return workspace;
  }

  public static void switchWarningVisibility() {
    BlocklyPanel.isWarningVisible = !BlocklyPanel.isWarningVisible;
  }

  public void callToggleWarning() {
    for (WorkspaceChangeListener listener : listeners) {
      listener.onToggleWarning(this);
    }
  }

  /**
   * Remember any component instances for this form in case
   * the workspace gets reinitialized later (we get detached from
   * our parent object and then our blocks editor gets loaded
   * again later). Also, remember the current state of the blocks
   * area in case we get reloaded.
   *
   * This method originally stashed a bunch of iframe related state
   * that is no longer necessary due to the removal of blocklyframe.html.
   * To maintain the correct logic with the ReplMgr, it remains for now.
   */
  public void saveComponentsAndBlocks() {
    // Call doResetYail which will stop the timer that is polling the phone. It is important
    // that it be stopped to avoid a race condition where the last timer on this form fires
    // while the new form is loading.
    ReplMgr.resetYail(true);
  }

  /**
   * Load the blocks described by blocksContent into the blocks workspace.
   *
   * @param formJson JSON description of Form's structure for upgrading
   * @param blocksContent XML description of a blocks workspace in format expected by Blockly
   * @throws LoadBlocksException if Blockly throws an uncaught exception
   */
  // [lyn, 2014/10/27] added formJson for upgrading
  public void loadBlocksContent(String formJson, String blocksContent) throws LoadBlocksException {
    WorkspaceSvg previousWorkspace = Blockly.mainWorkspace;
    try {
      Blockly.mainWorkspace = this.workspace;
      workspace.loadBlocksFile(formJson, blocksContent).verifyAllBlocks();
    } catch (JavaScriptException e) {
      loadError = true;
      LOG.severe("Error loading blocks for screen " + formName + ": "
          + e.getDescription());
      throw new LoadBlocksException(e, formName);
    } finally {
      loadComplete = true;
      Blockly.mainWorkspace = previousWorkspace;
    }
  }

  /**
   * Get Yail code for current blocks workspace
   *
   * @return the yail code as a String
   * @throws YailGenerationException if there was a problem generating the Yail
   */
  public String getYail(String formJson, String packageName) throws YailGenerationException {
    try {
      return workspace.getFormYail(formJson, packageName);
    } catch (JavaScriptException e) {
      throw new YailGenerationException(e.getDescription(), formName);
    }
  }

  /**
   * Send component data (json and form name) to Blockly for building
   * yail for the REPL.
   *
   * @throws YailGenerationException if there was a problem generating the Yail
   */
  public void sendComponentData(String formJson, String packageName) throws YailGenerationException {
    if (!currentForm.equals(formName)) { // Not working on the current form...
      LOG.warning("Not working on " + currentForm + " (while sending for " + formName + ")");
      return;
    }
    try {
      ReplMgr.sendFormData(formJson, packageName, workspace);
    } catch (JavaScriptException e) {
      throw new YailGenerationException(e.getDescription(), formName);
    }
  }

  public void startRepl(boolean alreadyRunning, boolean forEmulator, boolean forUsb) { // Start the Repl
    makeActive();
    ReplMgr.startRepl(alreadyRunning, forEmulator, forUsb);
  }

  public void hardReset() {
    ReplMgr.ehardreset(formName);
  }

  public void getBlocksImage(WorkspaceSvg.ExportCallback callback) {
    workspace.exportBlocksImageToUri(callback);
  }

  // The code below (4 methods worth) is for creating a GWT dialog box
  // from the blockly code. See the comment in replmgr.js for more
  // information.

  @JsFunction
  public interface DialogCallback {
    void finished(String buttonName);
  }

  /**
   * Create a Dialog Box. We call this from Javascript (blockly) to
   * display a dialog box.  We do this here because we can get calls
   * from the blocklyframe when it is not visible.  Because we are in
   * the parent window, we can display dialogs that will be visible
   * even when the blocklyframe is not visible.
   *
   * @param title      Title for the Dialog Box
   * @param mess       The message to display
   * @param buttonName The string to display in the "OK" button.
   * @param size       0 or 1. 0 makes a smaller box 1 makes a larger box.
   * @param destructive Indicates if the button should be styled as a destructive action.
   * @param callback   an opague JavaScriptObject that contains the
   *                   callback function provided by the Javascript code.
   * @return The created dialog box.
   */

  public static DialogBox createDialog(String title, String mess, final String buttonName, Boolean destructive,
                                       final String cancelButtonName, int size, final DialogCallback callback) {
    final DialogBox dialogBox = new DialogBox();
    dialogBox.setStylePrimaryName("ode-DialogBox");
    dialogBox.setText(title);
    if (size == 0) {
      dialogBox.setHeight("150px");
    } else {
      dialogBox.setHeight("400px");
    }
    dialogBox.setWidth("400px");
    dialogBox.setGlassEnabled(true);
    dialogBox.setAnimationEnabled(true);
    dialogBox.center();
    VerticalPanel DialogBoxContents = new VerticalPanel();
    HTML message = new HTML(mess);
    message.setStyleName("DialogBox-message");
    HorizontalPanel holder = new HorizontalPanel();
    if (cancelButtonName != null) {
      Button cancel = new Button(cancelButtonName);
      cancel.addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          callback.finished(cancelButtonName);
        }
      });
      holder.add(cancel);
    }
    if (buttonName != null) {           // If buttonName and cancelButtonName are null
      Button ok = new Button(buttonName); // We won't have any buttons and other
      if (destructive) {
        ok.addStyleName("destructive-action");
      }
      ok.addClickHandler(new ClickHandler() { // code is needed to dismiss us
        @Override
        public void onClick(ClickEvent event) {
          callback.finished(buttonName);
        }
      });
      holder.add(ok);
    }
    DialogBoxContents.add(message);
    DialogBoxContents.add(holder);
    dialogBox.setWidget(DialogBoxContents);
    Blockly.terminateDrag_();  // cancel a drag before showing the modal dialog
//    ConnectProgressBar.tempHide(true); // Hide any connection progress bar
    dialogBox.show();
    return dialogBox;
  }

  /**
   * Hide a dialog box. This function is here so it can be called from
   * the blockly code. We cannot call "hide" directly from the blockly
   * code because when this code is compiled, the "hide" method disappears!
   *
   * @param dialog The dialogbox to hide.
   */

  public static void HideDialog(DialogBox dialog) {
//    ConnectProgressBar.tempHide(false); // unhide the progress bar if it was hidden
    dialog.hide();
  }

  public static void SetDialogContent(DialogBox dialog, String mess) {
    HTML html = (HTML) ((VerticalPanel) dialog.getWidget()).getWidget(0);
    html.setHTML(mess);
  }

  /**
   * Trigger and Update of the Companion if the Companion is connected
   * and an update is available. Note: We do not compare the currently
   * running Companion's version against the version we are going to load
   * we just do it. If YaVersion.COMPANION_UPDATE_URL is "", then no
   * Update is available.
   */

  public void updateCompanion() {
    ReplMgr.triggerUpdate();
  }

  /**
   * Access UI translations for generating a deletion warning dialog.
   * @param message Identifier of message
   * @return Translated message
   * @throws IllegalArgumentException if the identifier is not understood
   */
  public static String getOdeMessage(String message) {
    // TODO(ewpatton): Investigate using a generator to work around
    // lack of reflection
    if ("deleteButton".equals(message)) {
      return "Delete";
    } else if ("cancelButton".equals(message)) {
      return "Cancel";
    } else {
      throw new IllegalArgumentException("Unexpected argument in getOdeMessage: " + message);
    }
  }

  // ------------ Native methods ------------

  /**
   * Take a Javascript function, embedded in an opaque JavaScriptObject,
   * and call it.
   *
   * @param callback the Javascript callback.
   * @param arg argument to the callback
   */

  private static native void doCallBack(JavaScriptObject callback, String arg) /*-{
    callback.call(null, arg);
  }-*/;

  private static native void exportMethodsToJavascript() /*-{
//    $wnd.BlocklyPanel_callToggleWarning =
//        $entry(@com.google.appinventor.blocklyeditor.BlocklyPanel::callToggleWarning());
//    $wnd.BlocklyPanel_indicateDisconnect =
//        $entry(@com.google.appinventor.blocklyeditor.BlocklyPanel::indicateDisconnect());
//    $wnd.BlocklyPanel_pushScreen =
//        $entry(@com.google.appinventor.blocklyeditor.BlocklyPanel::pushScreen(Ljava/lang/String;));
//    $wnd.BlocklyPanel_popScreen =
//        $entry(@com.google.appinventor.blocklyeditor.BlocklyPanel::popScreen());
    $wnd.BlocklyPanel_createDialog =
        $entry(@com.google.appinventor.blocklyeditor.BlocklyPanel::createDialog(*));
    $wnd.BlocklyPanel_hideDialog =
        $entry(@com.google.appinventor.blocklyeditor.BlocklyPanel::HideDialog(Lcom/google/gwt/user/client/ui/DialogBox;));
    $wnd.BlocklyPanel_setDialogContent =
        $entry(@com.google.appinventor.blocklyeditor.BlocklyPanel::SetDialogContent(Lcom/google/gwt/user/client/ui/DialogBox;Ljava/lang/String;));
//    $wnd.BlocklyPanel_getComponentInstancePropertyValue =
//        $entry(@com.google.appinventor.blocklyeditor.BlocklyPanel::getComponentInstancePropertyValue(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;));
    $wnd.BlocklyPanel_getOdeMessage =
      $entry(@com.google.appinventor.blocklyeditor.BlocklyPanel::getOdeMessage(Ljava/lang/String;));
  }-*/;

  private void initWorkspace(WorkspaceSettingsProvider settingsProvider) {
    workspace = BlocklyEditor.create(DivElement.as(getElement()), formName, readOnly, LocaleInfo.getCurrentLocale().isRTL());
    workspace.setSettingsProvider(settingsProvider);
    workspace.addChangeListener(new WorkspaceSvg.WorkspaceChangeListener() {
      @Override
      public void run(Event event) {
        BlockSvg block = workspace.getBlockById(event.getBlockId());
        if (block != null && "COMPONENT_SELECTOR".equals(event.getName())) {
          block.rename(event.getOldValue(), event.getNewValue());
        }
        workspaceChanged(event);
        if (workspace.rendered && !event.isTransient()) {
          WarningHandler handler = workspace.getWarningHandler();
          if (handler != null) {
            handler.determineDuplicateComponentEventHandlers();
            workspace.requestErrorChecking(block);
          }
        }
      }
    });
  }

  /**
   * Inject the workspace into the &lt;div&gt; element.
   */
  public void injectWorkspace() {
    Blockly.ai_inject(DivElement.as(getElement()), workspace);
  }

  /**
   * Make the workspace associated with the BlocklyPanel the main workspace.
   */
  public void makeActive() {
    currentForm = getElement().getId();
    Blockly.mainWorkspace = workspace;
    String[] parts = workspace.formName.split("_");
    if (ReplMgr.isConnected()) {
      ReplMgr.pollYail(workspace);
    }
    workspace.fireChangeListener(ScreenSwitch.create(parts[0], parts[1]));
  }

  /**
   * Return the XML string describing the current state of the blocks workspace
   */
  public String getBlocksContent() {
    return workspace.saveBlocksFile();
  }

  /**
   * Show the drawer for component with the specified instance name
   *
   * @param instanceName The name of the component instance to show.
   */
  public void showComponentBlocks(String instanceName) {
    workspace.hideDrawer().showComponent(instanceName);
  }

  /**
   * Show the built-in blocks drawer with the specified name
   *
   * @param category The category of built-in blocks to show.
   */
  public void showBuiltinBlocks(String category) {
    workspace.hideDrawer().showBuiltin(category);
  }

  /**
   * Show the generic blocks drawer with the specified name
   *
   * @param typeName The type of the component to show generic blocks for.
   */
  public void showGenericBlocks(String typeName) {
    workspace.hideDrawer().showGeneric(typeName);
  }

  /**
   * Hide the blocks drawer
   */
  public void hideDrawer() {
    workspace.hideDrawer();
  }

  /**
   * @return true if the blocks drawer is showing, false otherwise.
   */
  public boolean isDrawerShowing() {
    return workspace.isDrawerShowing();
  }

  public void render() {
    workspace.resize().render();
  }

  public void hideChaff() {
    Blockly.hideChaff();
  }

  public void toggleWarning() {
    WarningHandler handler = workspace.getWarningHandler();
    if (handler != null) {
      handler.toggleWarning();
    }
  }

  public static native void setLanguageVersion(int yaVersion, int blocksVersion)/*-{
    $wnd.YA_VERSION = yaVersion;
    $wnd.BLOCKS_VERSION = blocksVersion;
  }-*/;

  public static native void setPreferredCompanion(String preferredCompanionString,
      String base64CompanionUrl, String binaryCompanionUrl, String emulatorUrl) /*-{
    $wnd.PREFERRED_COMPANION = preferredCompanionString;
    $wnd.COMPANION_UPDATE_URL = base64CompanionUrl;
    $wnd.COMPANION_UPDATE_URL1 = binaryCompanionUrl;
    $wnd.COMPANION_UPDATE_EMULATOR_URL = emulatorUrl;
  }-*/;

  public static native void setAcceptableCompanionPackage(String comp) /*-{
    $wnd.ACCEPTABLE_COMPANION_PACKAGE = comp;
  }-*/;

  public static native void addAcceptableCompanion(String comp) /*-{
    if ($wnd.ACCEPTABLE_COMPANIONS === null ||
        $wnd.ACCEPTABLE_COMPANIONS === undefined) {
      $wnd.ACCEPTABLE_COMPANIONS = [];
    }
    $wnd.ACCEPTABLE_COMPANIONS.push(comp);
  }-*/;

}
