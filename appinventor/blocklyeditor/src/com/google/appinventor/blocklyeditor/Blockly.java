package com.google.appinventor.blocklyeditor;

import com.google.gwt.dom.client.DivElement;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Blockly {

  public static WorkspaceSvg mainWorkspace;

  public static native void terminateDrag_();

  public static native void ai_inject(DivElement el, WorkspaceSvg workspace);

  public static native void hideChaff();
}
