package com.google.appinventor.blocklyeditor.api;

public interface WorkspaceSettingsProvider {
  void onGridEnabled(boolean enabled);
  void onSnapEnabled(boolean enabled);
  boolean getGridEnabled();
  boolean getSnapEnabled();
}
