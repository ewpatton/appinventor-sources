package com.google.appinventor.common.datamodel;

import com.google.gwt.core.client.JavaScriptObject;
import jsinterop.annotations.JsType;

@JsType
public class ComponentInfo extends JavaScriptObject {
  protected ComponentInfo() {
  }

  public final native String getType();
}
