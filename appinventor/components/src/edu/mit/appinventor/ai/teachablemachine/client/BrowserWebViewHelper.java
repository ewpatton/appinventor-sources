package edu.mit.appinventor.ai.teachablemachine.client;

import com.google.gwt.core.client.JsArrayString;
import edu.mit.appinventor.ai.teachablemachine.IWebViewHelper;
import edu.mit.appinventor.ai.teachablemachine.TeachableMachine;
import java.util.ArrayList;
import java.util.List;

class BrowserWebViewHelper implements IWebViewHelper {
  private boolean scriptsLoaded = false;
  private final TeachableMachine owner;

  BrowserWebViewHelper(TeachableMachine owner) {
    this.owner = owner;
  }

  @Override
  public native void configureWebView(Object container) /*-{
    var self = this;
    $wnd.TeachableMachine = {
      ready: function(labels) {
        var parsedLabels = JSON.parse(labels);
        self.@edu.mit.appinventor.ai.teachablemachine.client.BrowserWebViewHelper::handleLabels(*)(parsedLabels);
      },
      reportResult: function(result) {
        // TODO: Implement this function
      },
      error: function(errorCode) {
        // TODO: Implement this function
      },
      isLoaded: function(isComplete) {
        // TODO: Implement this function
      }
    }
  }-*/;

  @Override
  public void assertWebView(String method) {
    // TODO: Determine whether we need to do anything here
  }

  @Override
  public native void evaluateJavascript(String js) /*-{
    $wnd.eval(js);
  }-*/;

  @Override
  public void loadUrl(String url) {
    if (scriptsLoaded) {
      return;
    }
    doLoadUrl(url);
    scriptsLoaded = true;
  }

  @Override
  public void classifyImageData(String path) {
    // TODO: Decide whether we need this for the web version
  }

  @Override
  public boolean shouldAskForPermission() {
    // TODO: Check whether we can test for Camera access
    return false;
  }

  @Override
  public void askForPermission(Runnable next) {
    // TODO: Trigger the camera permission dialog if needed
  }

  private void handleLabels(JsArrayString labels) {
    List<String> labelList = new ArrayList<>();
    for (int i = 0; i < labels.length(); i++) {
      labelList.add(labels.get(i));
    }
    owner.setLabels(labelList);
    owner.ClassifierReady();
  }

  private native void doLoadUrl(String url) /*-{
    var script = $doc.createElement('script');
    script.src = '//cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js';
    $wnd.body.appendChild(script);
    script = $doc.createElement('script');
    script.src = '//cdn.jsdelivr.net/npm/@teachablemachine/image@0.8/dist/teachablemachine-image.min.js';
    $wnd.body.appendChild(script);
    // TODO: Add the teachablemachine.js script
  }-*/;
}
