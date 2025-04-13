package edu.mit.appinventor.ai.teachablemachine.client;

import com.google.appinventor.components.runtime.util.YailDictionary;
import com.google.gwt.core.client.JavaScriptObject;
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
        var parsedResult = JSON.parse(result);
        self.@edu.mit.appinventor.ai.teachablemachine.client.BrowserWebViewHelper::handleResults(*)(parsedResult);
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
  public void loadUrl(String url, Runnable callback) {
    if (scriptsLoaded) {
      return;
    }
    doLoadUrl(url, callback);
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

  private void handleResults(YailDictionary results) {
    owner.GotClassification(results);
  }

  private native void doLoadUrl(String url, Runnable callback) /*-{
    var script = $doc.createElement('script');
    script.setAttribute('src', '//cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js');
    script.addEventListener('load', function() {
      var script = $doc.createElement('script');
      script = $doc.createElement('script');
      script.setAttribute('src', '//cdn.jsdelivr.net/npm/@teachablemachine/image@0.8/dist/teachablemachine-image.min.js');
      script.addEventListener('load', function() {
        var script = $doc.createElement('script');
        script.setAttribute('src', 'src/edu/mit/appinventor/ai/teachablemachine/assets/teachable_machine.js');
        script.addEventListener('load', function() {
          if (callback) {
            callback.@java.lang.Runnable::run(*)();
          }
        });
        $doc.body.appendChild(script);
      });
      $doc.body.appendChild(script);
    })
    $doc.body.appendChild(script);
  }-*/;
}
