package com.google.appinventor.blocklyeditor.events;

public class ScreenSwitch extends Event {
  protected ScreenSwitch() {}

  public static native ScreenSwitch create(String projectId, String formName)/*-{
    return new AI.Events.ScreenSwitch(projectId, formName);
  }-*/;
}
