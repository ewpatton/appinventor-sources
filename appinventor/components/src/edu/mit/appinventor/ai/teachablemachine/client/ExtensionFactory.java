package edu.mit.appinventor.ai.teachablemachine.client;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class ExtensionFactory {
  @JsFunction
  public interface ExtensionConstructor {
    Object create();
  }

  public static native void registerExtension(String name, ExtensionConstructor constructor);
}
