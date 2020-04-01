package com.google.appinventor.blocklyeditor;

import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "Blockly")
public class WarningHandler {
  public native void toggleWarning();

  public native void determineDuplicateComponentEventHandlers();
}
