package com.google.appinventor.components.runtime;

public class EventDispatcher {
  public native static boolean dispatchEvent(Component source, String eventName, Object... args) /*-{
    $wnd.console.log('Handling ' + eventName + ' for ' + source + ', args:', args);
    if ($wnd.registeredEvents && $wnd.registeredEvents[eventName]) {
      var event = $wnd.registeredEvents[eventName];
      if (event) {
        event.apply(source, args);
        return true;
      }
    }
    return false;
  }-*/;
}
