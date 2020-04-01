package com.google.appinventor.blocklyeditor.api;

public interface ReplListener {
  boolean onPushScreen(String screenName);
  void onPopScreen();
  void onDisconnect();
}
