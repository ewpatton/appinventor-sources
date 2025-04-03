package com.google.appinventor.components.runtime;

public class EventDispatcher {
  public native static boolean dispatchEvent(Component source, String eventName, Object... args) /*-{
    $wnd.console.log('Handling ' + eventName + ' for ' + source + ', args:', args);
    return true;
  }-*/;
}
