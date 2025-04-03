package edu.mit.appinventor.ai.teachablemachine;

import com.google.appinventor.components.runtime.Form;

public interface IWebViewHelper {
  void configureWebView(Object container);
  void assertWebView(String method);
  void evaluateJavascript(String js);
  void loadUrl(String url);
  void classifyImageData(String path);
  boolean shouldAskForPermission();
  void askForPermission(final Runnable next);
}
