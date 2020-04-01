package com.google.appinventor.blocklyeditor;

import com.google.appinventor.blocklyeditor.api.WorkspaceSettingsProvider;
import com.google.appinventor.blocklyeditor.events.Event;
import com.google.gwt.core.client.JavaScriptObject;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "Blockly")
public class WorkspaceSvg {

  @JsFunction
  public interface ExportCallback {
    void run(String result, String error);
  }

  @JsFunction
  public interface WorkspaceChangeListener {
    void run(Event event);
  }

  /**
   * Add a component to the blocks workspace
   *
   * @param uuid            the unique id of the component instance
   * @param instanceName    the name of the component instance
   * @param typeName        the type of the component instance
   */
  public native void addComponent(String uuid, String instanceName, String typeName);

  /**
   * Remove the component instance instanceName, with the given typeName
   * and uid from the workspace.
   *
   * @param uuid         unique id
   */
  public native void removeComponent(String uuid);

  /**
   * Rename the component whose old name is oldName (and whose
   * unique id is uid and type name is typeName) to newName.
   *
   * @param uuid     unique id
   * @param oldName  old instance name
   * @param newName  new instance name
   */
  public native void renameComponent(String uuid, String oldName, String newName);

  public native BlockSvg getBlockById(String blockId);

  public native void checkAllBlocksForWarningsAndErrors();

  public native void verifyAllBlocks();

  public native WorkspaceSvg populateComponentTypes(String componentTypes,
      JavaScriptObject translations);

  public native void exportBlocksImageToUri(ExportCallback callback);

  public native WorkspaceSvg resize();

  public native WorkspaceSvg render();

  public native WorkspaceSvg hideDrawer();

  public native WorkspaceSvg showBuiltin(String category);

  public native WorkspaceSvg showGeneric(String typeName);

  public native WorkspaceSvg showComponent(String instanceName);

  public native String saveBlocksFile();

  public native boolean isDrawerShowing();

  public native WarningHandler getWarningHandler();

  public native String getFormYail(String formJson, String packageName);

  public native void addChangeListener(WorkspaceChangeListener listener);

  public native void requestErrorChecking(BlockSvg block);

  public native WorkspaceSvg loadBlocksFile(String formJson, String blocksContent);

  public native void fireChangeListener(Event event);

  public native void setSettingsProvider(WorkspaceSettingsProvider provider);

  public native void setYailMenuEnabled(boolean enabled);

  boolean rendered;
  String formName;

}
