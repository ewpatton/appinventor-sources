package com.google.appinventor.blocklyeditor;

import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "Blockly")
public class BlockSvg {
  public native void rename(String oldName, String newName);
}
