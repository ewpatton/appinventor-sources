package com.google.appinventor.blocklyeditor;

import com.google.gwt.core.client.JavaScriptObject;
import java.util.Map;

/**
 * NativeTranslationMap is a plain JavaScriptObject that provides key-value mappings for
 * user interface translations in Blockly. This reduces the overhead of crossing GWT's
 * JSNI barrier by replacing a more expensive function call with a dictionary lookup.
 *
 * @author ewpatton
 *
 */
class NativeTranslationMap extends JavaScriptObject {
  // GWT requires JSO constructors to be non-visible.
  protected NativeTranslationMap() {}

  /**
   * Instantiate a new NativeTranslationMap.
   * @return An empty NativeTranslationMap
   */
  private static native NativeTranslationMap make()/*-{
    return {};
  }-*/;

  /**
   * Add a key-value pair to the translation map.
   * @param key Untranslated term
   * @param value Translated term for the user's current locale
   */
  private native void put(String key, String value)/*-{
    this[key] = value;
  }-*/;

  /**
   * Transforms a Java Collections Map into a NativeTranslationMap.
   * @param map The source mapping of key-value pairs
   * @return A new NativeTranslationMap with the same contents as <i>map</i> but as a
   * JavaScript Object usable in native code.
   */
  public static NativeTranslationMap transform(Map<String, String> map) {
    NativeTranslationMap result = make();
    for(Map.Entry<String, String> entry : map.entrySet()) {
      result.put(entry.getKey(), entry.getValue());
    }
    return result;
  }
}
