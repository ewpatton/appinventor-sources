package com.google.appinventor.blocklyeditor.api;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

/**
 * BackpackChangeListener allows other elements to listen and respond to changes in the
 * block workspace backpack.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
@JsType
public interface BackpackSource {
  @JsFunction
  interface BackpackReceiver {
    void receive(String contents);
  }
  @JsMethod
  void setBackpackContents(String contents);
  @JsMethod
  void getBackpackContents(BackpackReceiver receiver);
}
