package com.google.appinventor.blocklyeditor.events;

import com.google.gwt.core.client.JavaScriptObject;

public class Event extends JavaScriptObject {

  protected Event() {}

  public final native String getBlockId()/*-{
    return this.blockId;
  }-*/;

  public final native String getName()/*-{
    return this.name;
  }-*/;

  public final native String getOldValue()/*-{
    return this.oldValue;
  }-*/;

  public final native String getNewValue()/*-{
    return this.newValue;
  }-*/;

  public final native boolean isTransient()/*-{
    if (this.isTransient) {
      return true;
    } else if (this.type === Blockly.Events.UI) {
      // Blockly ui events are transient if they are selection changes, clicks, opening of mutator
      // and warning bubbles.
      return this.element === 'selected' || this.element === 'click' ||
        this.element === 'mutatorOpen' || this.element === 'warningOpen';
    }
    return false;
  }-*/;

  public final native boolean isUi()/*-{
    return this && this.type === Blockly.Events.UI;
  }-*/;

  public final native boolean isSave()/*-{
    return this && this.type === AI.Events.FORCE_SAVE;
  }-*/;
}
