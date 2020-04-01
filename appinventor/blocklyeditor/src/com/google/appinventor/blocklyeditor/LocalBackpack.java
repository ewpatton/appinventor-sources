package com.google.appinventor.blocklyeditor;

import com.google.appinventor.blocklyeditor.api.BackpackSource;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "Blockly")
public class LocalBackpack implements BackpackSource {
  @Override
  public native void setBackpackContents(String contents);

  @Override
  public native void getBackpackContents(BackpackReceiver receiver);
}
