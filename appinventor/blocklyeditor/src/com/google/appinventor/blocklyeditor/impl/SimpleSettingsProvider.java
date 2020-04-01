package com.google.appinventor.blocklyeditor.impl;

import com.google.appinventor.blocklyeditor.api.WorkspaceSettingsProvider;

public class SimpleSettingsProvider implements WorkspaceSettingsProvider {
  private boolean gridEnabled = false;
  private boolean snapEnabled = false;

  @Override
  public void onGridEnabled(boolean enabled) {
    gridEnabled = enabled;
  }

  @Override
  public void onSnapEnabled(boolean enabled) {
    snapEnabled = enabled;
  }

  @Override
  public boolean getGridEnabled() {
    return gridEnabled;
  }

  @Override
  public boolean getSnapEnabled() {
    return snapEnabled;
  }
}
