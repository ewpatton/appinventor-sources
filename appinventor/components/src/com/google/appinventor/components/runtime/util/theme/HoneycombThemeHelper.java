package com.google.appinventor.components.runtime.util.theme;

import android.app.ActionBar;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.Window;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.ImageViewUtil;

public class HoneycombThemeHelper implements ThemeHelper {

  private static final String LOG_TAG = HoneycombThemeHelper.class.getSimpleName();

  private final AppInventorCompatActivity activity;

  public HoneycombThemeHelper(AppInventorCompatActivity activity) {
    this.activity = activity;
  }

  @Override
  public void requestActionBar() {
    Log.d(LOG_TAG, "has action bar? " + activity.getWindow().hasFeature(Window.FEATURE_ACTION_BAR));
    if (!activity.getWindow().hasFeature(Window.FEATURE_ACTION_BAR)) {
      activity.getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
    }
    Log.d(LOG_TAG, "has action bar now? " + activity.getWindow().hasFeature(Window.FEATURE_ACTION_BAR));
    Log.d(LOG_TAG, "FEATURE_LEFT_ICON = " + activity.getWindow().hasFeature(Window.FEATURE_LEFT_ICON));
    Log.d(LOG_TAG, "FEATURE_RIGHT_ICON = " + activity.getWindow().hasFeature(Window.FEATURE_RIGHT_ICON));
    Log.d(LOG_TAG, "FEATURE_CUSTOM_TITLE = " + activity.getWindow().hasFeature(Window.FEATURE_CUSTOM_TITLE));
    Log.d(LOG_TAG, "FEATURE_NO_TITLE = " + activity.getWindow().hasFeature(Window.FEATURE_NO_TITLE));
  }

  @Override
  public boolean setActionBarVisible(boolean visible) {
    ActionBar actionBar = activity.getActionBar();
    if (actionBar == null) {
      if (activity instanceof Form) {
        ((Form) activity).dispatchErrorOccurredEvent((Form) activity, "ActionBar", ErrorMessages.ERROR_ACTIONBAR_NOT_SUPPORTED);
      }
      return false;
    } else if (visible) {
      actionBar.show();
    } else {
      actionBar.hide();
    }
    return true;
  }

  @Override
  public boolean hasActionBar() {
    return activity.getActionBar() != null;
  }

  @Override
  public void setTitle(String title) {
    ActionBar actionBar = activity.getActionBar();
    if (actionBar != null) {
      actionBar.setTitle(title);
    }
  }

  @Override
  public void setActionBarAnimation(boolean enabled) {
    // Canceling the transition animation is only provided in the AppCompat version
  }

  @Override
  public void setTitle(String title, boolean black) {
    ActionBar actionBar = activity.getActionBar();
    if (actionBar != null) {
      if (black) {
        actionBar.setTitle(Html.fromHtml("<font color=\"black\">" + title + "</font>"));
        ImageViewUtil.setMenuButtonColor(activity, Color.BLACK);
      } else {
        actionBar.setTitle(title);
        ImageViewUtil.setMenuButtonColor(activity, Color.WHITE);
      }
    }
  }
}
