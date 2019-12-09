// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2009-2011 Google, All Rights reserved
// Copyright © 2011-2016 Massachusetts Institute of Technology, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.editor.youngandroid;

import com.google.appinventor.client.ComponentsTranslation;
import com.google.appinventor.client.DesignToolbar;
import com.google.appinventor.client.ErrorReporter;
import com.google.appinventor.client.Ode;
import com.google.appinventor.client.TopToolbar;
import com.google.appinventor.client.output.OdeLog;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.appinventor.client.settings.user.BlocksSettings;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.shared.settings.SettingsConstants;
import com.google.common.collect.Sets;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;

import com.google.gwt.query.client.builders.JsniBundle;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static com.google.appinventor.client.Ode.MESSAGES;

/**
 * Debugger editor panel.
 */
public class DebuggerPanel extends HTMLPanel {

  // The currently displayed form (project/screen)
  private static String currentForm;

  // My form name
  private final String formName;


  /**
   * Reference to the native Blockly.WorkspaceSvg.
   */
  private JavaScriptObject workspace;

  /**
   * If true, the loading of the debugger editor has not completed.
   */
  private boolean loadComplete = false;

  /**
   * If true, the loading of the debugger editor resulted in an error.
   */
  private boolean loadError = false;

  public DebuggerPanel(YaBlocksEditor blocksEditor, String formName) {
    this(blocksEditor, formName, false);
  }

  public DebuggerPanel(YaBlocksEditor blocksEditor, String formName, boolean readOnly) {
    super("");
    getElement().addClassName("svg");
    getElement().setId(formName + "-debug");
    this.formName = formName;
    initWorkspace(Long.toString(blocksEditor.getProjectId()), readOnly, LocaleInfo.getCurrentLocale().isRTL());
    OdeLog.log("Created DebuggerPanel for " + formName);
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
    doResetYail();
  }

  /**
   * Get Yail code for current blocks workspace
   *
   * @return the yail code as a String
   * @throws YailGenerationException if there was a problem generating the Yail
   */
  public String getYail(String formJson, String packageName) throws YailGenerationException {
    try {
      return doGetYail(formJson, packageName);
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
      OdeLog.log("Not working on " + currentForm + " (while sending for " + formName + ")");
      return;
    }
    try {
      doSendJson(formJson, packageName);
    } catch (JavaScriptException e) {
      throw new YailGenerationException(e.getDescription(), formName);
    }
  }

  // Set currentScreen
  // We use this to determine if we should send Yail to a
  // a connected device.
  public static void setCurrentForm(String formName) {
    currentForm = formName;
  }

  public void getBlocksImage(Callback<String, String> callback) {
    doFetchBlocksImage(callback);
  }


  public static String getComponentInfo(String typeName) {
    return YaBlocksEditor.getComponentInfo(typeName);
  }

  public static String getComponentsJSONString(String projectId) {
    return YaBlocksEditor.getComponentsJSONString(Long.parseLong(projectId));
  }

  public static String getComponentInstanceTypeName(String formName, String instanceName) {
    return YaBlocksEditor.getComponentInstanceTypeName(formName, instanceName);
  }

  public static int getYaVersion() {
    return YaVersion.YOUNG_ANDROID_VERSION;
  }

  public static int getBlocksLanguageVersion() {
    return YaVersion.BLOCKS_LANGUAGE_VERSION;
  }

  public static String getQRCode(String inString) {
    return doQRCode(inString);
  }

  /**
   * Trigger and Update of the Companion if the Companion is connected
   * and an update is available. Note: We do not compare the currently
   * running Companion's version against the version we are going to load
   * we just do it. If YaVersion.COMPANION_UPDATE_URL is "", then no
   * Update is available.
   */

  public void updateCompanion() {
    updateCompanion(formName);
  }

  public static void updateCompanion(String formName) {
    doUpdateCompanion(formName);
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
      return Ode.getMessages().deleteButton();
    } else if ("cancelButton".equals(message)) {
      return Ode.getMessages().cancelButton();
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
    $wnd.BlocklyPanel_callToggleWarning =
        $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::callToggleWarning());
    $wnd.BlocklyPanel_checkIsAdmin =
        $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::checkIsAdmin());
    $wnd.BlocklyPanel_indicateDisconnect =
        $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::indicateDisconnect());
    // Note: above lines are longer than 100 chars but I'm not sure whether they can be split
    $wnd.BlocklyPanel_pushScreen =
        $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::pushScreen(Ljava/lang/String;));
    $wnd.BlocklyPanel_popScreen =
        $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::popScreen());
    $wnd.BlocklyPanel_createDialog =
        $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::createDialog(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;ILcom/google/gwt/core/client/JavaScriptObject;));
    $wnd.BlocklyPanel_hideDialog =
        $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::HideDialog(Lcom/google/gwt/user/client/ui/DialogBox;));
    $wnd.BlocklyPanel_setDialogContent =
        $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::SetDialogContent(Lcom/google/gwt/user/client/ui/DialogBox;Ljava/lang/String;));
    $wnd.BlocklyPanel_getComponentInstanceTypeName =
        $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::getComponentInstanceTypeName(Ljava/lang/String;Ljava/lang/String;));
    $wnd.BlocklyPanel_getComponentInfo =
        $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::getComponentInfo(Ljava/lang/String;));
    $wnd.BlocklyPanel_getComponentsJSONString =
        $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::getComponentsJSONString(Ljava/lang/String;));
    $wnd.BlocklyPanel_storeBackpack =
      $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::storeBackpack(Ljava/lang/String;));
    $wnd.BlocklyPanel_getOdeMessage =
      $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::getOdeMessage(Ljava/lang/String;));
    $wnd.BlocklyPanel_setGridEnabled =
      $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::setGridEnabled(Z));
    $wnd.BlocklyPanel_setSnapEnabled =
      $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::setSnapEnabled(Z));
    $wnd.BlocklyPanel_getGridEnabled =
      $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::getGridEnabled());
    $wnd.BlocklyPanel_getSnapEnabled =
      $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::getSnapEnabled());
    $wnd.BlocklyPanel_saveUserSettings =
      $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::saveUserSettings());
    $wnd.BlocklyPanel_getSharedBackpack =
      $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::getSharedBackpack(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;));
    $wnd.BlocklyPanel_storeSharedBackpack =
      $entry(@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::storeSharedBackpack(Ljava/lang/String;Ljava/lang/String;));

  }-*/;

  private native void initWorkspace(String projectId, boolean readOnly, boolean rtl)/*-{
    var el = this.@com.google.gwt.user.client.ui.UIObject::getElement()();
    var workspace = Blockly.BlocklyEditor.createdebugger(el,
      this.@com.google.appinventor.client.editor.youngandroid.DebuggerPanel::formName,
      readOnly, rtl);
    workspace.projectId = projectId;
    this.@com.google.appinventor.client.editor.youngandroid.DebuggerPanel::workspace = workspace;
  }-*/;

  /**
   * Inject the workspace into the &lt;div&gt; element.
   */
  native void injectWorkspace()/*-{
    var el = this.@com.google.gwt.user.client.ui.UIObject::getElement()();
    var oldMain = Blockly.mainWorkspace;
    Blockly.debug_inject(el, this.@com.google.appinventor.client.editor.youngandroid.DebuggerPanel::workspace);
    Blockly.mainWorkspace = oldMain;
  }-*/;

  /**
   * Make the workspace associated with the BlocklyPanel the main workspace.
   */
  native void makeActive()/*-{
    Blockly.debugWorkspace = this.@com.google.appinventor.client.editor.youngandroid.DebuggerPanel::workspace;
  }-*/;

  // [lyn, 2014/10/27] added formJson for upgrading
  public native void doLoadBlocksContent(String formJson, String blocksContent) /*-{
    var workspace = this.@com.google.appinventor.client.editor.youngandroid.DebuggerPanel::workspace;
    var previousdebugWorkspace = Blockly.debugWorkspace;
    try {
      Blockly.debugWorkspace = workspace;
      workspace.loadBlocksFile(formJson, blocksContent).verifyAllBlocks();
    } catch(e) {
      workspace.loadError = true;
      throw e;
    } finally {
      workspace.loadComplete = true;
      Blockly.debugWorkspace = previousdebugWorkspace;
    }
  }-*/;

  /**
   * Return the XML string describing the current state of the blocks workspace
   */
  public native String getBlocksContent() /*-{
    return this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .saveBlocksFile();
  }-*/;

  /**
   * Add a component to the blocks workspace
   *
   * @param uid             the unique id of the component instance
   * @param instanceName    the name of the component instance
   * @param typeName        the type of the component instance
   */
  public native void addComponent(String uid, String instanceName, String typeName)/*-{
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .addComponent(uid, instanceName, typeName);
  }-*/;

  /**
   * Remove the component instance instanceName, with the given typeName
   * and uid from the workspace.
   *
   * @param uid          unique id
   */
  public native void removeComponent(String uid)/*-{
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .removeComponent(uid);
  }-*/;

  /**
   * Rename the component whose old name is oldName (and whose
   * unique id is uid and type name is typeName) to newName.
   *
   * @param uid      unique id
   * @param oldName  old instance name
   * @param newName  new instance name
   */
  public native void renameComponent(String uid, String oldName, String newName)/*-{
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .renameComponent(uid, oldName, newName);
  }-*/;

  /**
   * Show the drawer for component with the specified instance name
   *
   * @param name
   */
  public native void showComponentBlocks(String name)/*-{
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .hideDrawer()
      .showComponent(name);
  }-*/;

  /**
   * Show the built-in blocks drawer with the specified name
   *
   * @param drawerName
   */
  public native void showBuiltinBlocks(String drawerName)/*-{
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .hideDrawer()
      .showBuiltin(drawerName);
  }-*/;

  /**
   * Show the generic blocks drawer with the specified name
   *
   * @param drawerName
   */
  public native void showGenericBlocks(String drawerName)/*-{
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .hideDrawer()
      .showGeneric(drawerName);
  }-*/;

  /**
   * Hide the blocks drawer
   */
  public native void hideDrawer()/*-{
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .hideDrawer();
  }-*/;

  /**
   * @returns true if the blocks drawer is showing, false otherwise.
   */
  public native boolean drawerShowing()/*-{
    return this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .isDrawerShowing();
  }-*/;

  public native void render()/*-{
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .resize()
      .render();
  }-*/;

  public native void hideChaff()/*-{
    Blockly.hideChaff();
  }-*/;

  public native void toggleWarning()/*-{
    var handler =
      this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
        .getWarningHandler();
    if (handler) {  // handler won't exist if the workspace hasn't rendered yet.
      handler.toggleWarning();
    }
  }-*/;

  public native String doGetYail(String formJson, String packageName) /*-{
    return this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .getFormYail(formJson, packageName);
  }-*/;

  public native void doSendJson(String formJson, String packageName) /*-{
    Blockly.ReplMgr.sendFormData(formJson, packageName,
      this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace);
  }-*/;

  public native void doResetYail() /*-{
    Blockly.ReplMgr.resetYail();
  }-*/;

  public native void doPollYail() /*-{
    try {
      Blockly.ReplMgr.pollYail();
    } catch (e) {
      $wnd.console.log("doPollYail() Failed");
      $wnd.console.log(e);
    }
  }-*/;

  public native void doStartRepl(boolean alreadyRunning, boolean forEmulator, boolean forUsb) /*-{
    Blockly.ReplMgr.startRepl(alreadyRunning, forEmulator, forUsb);
  }-*/;

  public native void doHardReset() /*-{
    Blockly.ReplMgr.ehardreset(
      this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::formName
    );
  }-*/;

  public native void doCheckWarnings() /*-{
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .checkAllBlocksForWarningsAndErrors();
  }-*/;

  static native void setLanguageVersion(int yaVersion, int blocksVersion)/*-{
    $wnd.YA_VERSION = yaVersion;
    $wnd.BLOCKS_VERSION = blocksVersion;
  }-*/;

  public static native String getCompVersion() /*-{
    return $wnd.PREFERRED_COMPANION;
  }-*/;

  static native void setPreferredCompanion(String comp, String url) /*-{
    $wnd.PREFERRED_COMPANION = comp;
    $wnd.COMPANION_UPDATE_URL = url;
  }-*/;

  static native void addAcceptableCompanionPackage(String comp) /*-{
    $wnd.ACCEPTABLE_COMPANION_PACKAGE = comp;
  }-*/;

  static native void addAcceptableCompanion(String comp) /*-{
    if ($wnd.ACCEPTABLE_COMPANIONS === null ||
        $wnd.ACCEPTABLE_COMPANIONS === undefined) {
      $wnd.ACCEPTABLE_COMPANIONS = [];
    }
    $wnd.ACCEPTABLE_COMPANIONS.push(comp);
  }-*/;

  static native String doQRCode(String inString) /*-{
    return Blockly.ReplMgr.makeqrcode(inString);
  }-*/;

  public static native void doUpdateCompanion(String formName) /*-{
    Blockly.ReplMgr.triggerUpdate();
  }-*/;

  /**
   * Update Component Types in Blockly ComponentTypes
   */
  public native void populateComponentTypes(String jsonComponentsStr) /*-{
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .populateComponentTypes(jsonComponentsStr, @com.google.appinventor.client.editor.youngandroid.BlocklyPanel::SIMPLE_COMPONENT_TRANSLATIONS);
  }-*/;

  /**
   * Update Component Types in Blockly ComponentTypes
   */
  public native void doVerifyAllBlocks() /*-{
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .verifyAllBlocks();
  }-*/;

  public native void doFetchBlocksImage(Callback<String,String> callback) /*-{
    var callb = $entry(function(result, error) {
      if (error) {
        callback.@com.google.gwt.core.client.Callback::onFailure(Ljava/lang/Object;)(error);
      } else {
        callback.@com.google.gwt.core.client.Callback::onSuccess(Ljava/lang/Object;)(result);
      }
    });
    this.@com.google.appinventor.client.editor.youngandroid.BlocklyPanel::workspace
      .exportBlocksImageToUri(callb);
  }-*/;

  /**
   * NativeTranslationMap is a plain JavaScriptObject that provides key-value mappings for
   * user interface translations in Blockly. This reduces the overhead of crossing GWT's
   * JSNI barrier by replacing a more expensive function call with a dictionary lookup.
   *
   * @author ewpatton
   *
   */
  private static class NativeTranslationMap extends JavaScriptObject {
    // GWT requires JSO constructors to be non-visible.
    protected NativeTranslationMap() {}

    /**
     * Instantiate a new NativeTranslationMap.
     * @return An empty NativeTranslationMap
     */
    private static native NativeTranslationMap make()/*-{
      return {};
    }-*/;

    /**
     * Add a key-value pair to the translation map.
     * @param key Untranslated term
     * @param value Translated term for the user's current locale
     */
    private native void put(String key, String value)/*-{
      this[key] = value;
    }-*/;

    /**
     * Transforms a Java Collections Map into a NativeTranslationMap.
     * @param map The source mapping of key-value pairs
     * @return A new NativeTranslationMap with the same contents as <i>map</i> but as a
     * JavaScript Object usable in native code.
     */
    public static NativeTranslationMap transform(Map<String, String> map) {
      NativeTranslationMap result = make();
      for(Entry<String, String> entry : map.entrySet()) {
        result.put(entry.getKey(), entry.getValue());
      }
      return result;
    }
  }

}
