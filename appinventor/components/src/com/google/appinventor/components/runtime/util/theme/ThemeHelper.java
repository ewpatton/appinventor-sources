package com.google.appinventor.components.runtime.util.theme;

public interface ThemeHelper {
  /**
   * Request the ActionBar functionality fot the platform, if available.
   */
  void requestActionBar();

  /**
   * Change the ActionBar visibility
   * @param visible true if the ActionBar should be made visible, otherwise false.
   * @return true if the ActionBar is available. If false is returned, no future requests for the
   * ActionBar will be honored (usually due to being in Classic theme mode).
   */
  boolean setActionBarVisible(boolean visible);

  boolean hasActionBar();

  void setTitle(String title);

  void setActionBarAnimation(boolean enabled);

  void setTitle(String title, boolean black);
}
