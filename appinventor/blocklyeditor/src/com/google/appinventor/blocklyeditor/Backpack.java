package com.google.appinventor.blocklyeditor;

import com.google.appinventor.blocklyeditor.api.BackpackSource;
import jsinterop.annotations.JsIgnore;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "Blockly")
public class Backpack {
  public native static void getSharedBackpack(BackpackSource.BackpackReceiver receiver);
  public static native void setSharedBackpack(String content);
  public static native void setBackpackSource(BackpackSource source);
}
