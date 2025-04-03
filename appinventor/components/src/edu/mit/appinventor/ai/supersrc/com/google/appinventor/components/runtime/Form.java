package com.google.appinventor.components.runtime;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType
@SuppressWarnings("unusable-by-js")
public class Form implements Component, HandlesEventDispatching {
  @Override
  public HandlesEventDispatching getDispatchDelegate() {
    return this;
  }

  @Override
  public boolean canDispatchEvent(Component component, String eventName) {
    return true;
  }

  @Override
  public native boolean dispatchEvent(Component component, String componentName, String eventName,
      Object[] args) /*-{
    console.log(arguments);
    return false;
  }-*/;

  @Override
  public void dispatchErrorOccurredEvent(Component component, String functionName, int errorCode,
      Object... args) {

  }

  @Override
  public void dispatchGenericEvent(Component component, String eventName, boolean notAlreadyHandled,
      Object[] args) {

  }

  public String getAssetPathForExtension(Component component, String path) {
    return path;
  }
}
