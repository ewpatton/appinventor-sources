package com.google.appinventor.components.runtime.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public final class YailDictionary implements Map<Object, Object> {
  public YailDictionary() {
  }

  @Override
  public native int size() /*-{
    return Object.keys(this).length;
  }-*/;

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public native boolean containsKey(Object key) /*-{
    return this.hasOwnProperty(key);
  }-*/;

  @Override
  public native boolean containsValue(Object value) /*-{
    for (var k in this) {
      if (this[k] === value) {
        return true;
      }
    }
    return false;
  }-*/;

  @Override
  public native Object get(Object key) /*-{
    return this[key];
  }-*/;

  @Override
  public native Object put(Object key, Object value) /*-{
    var old = this[key];
    this[key] = value;
    return old;
  }-*/;

  @Override
  public native Object remove(Object key) /*-{
    var old = this[key];
    delete this[key];
    return old;
  }-*/;

  @Override
  public void putAll(Map<?, ?> m) {
    for (Map.Entry<?, ?> entry : m.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public void clear() {

  }

  @Override
  public Set<Object> keySet() {
    return null;
  }

  @Override
  public Collection<Object> values() {
    return null;
  }

  @Override
  public Set<Entry<Object, Object>> entrySet() {
    return null;
  }
}
