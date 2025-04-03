package edu.mit.appinventor.ai.teachablemachine;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.PermissionResultHandler;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.YailDictionary;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

class AndroidWebViewHelper implements IWebViewHelper {
  private static final String LOG_TAG = "TeachableMachine";
  private static final int IMAGE_WIDTH = 500;
  private static final int IMAGE_QUALITY = 100;

  private final TeachableMachine owner;
  private final Form form;

  AndroidWebViewHelper(TeachableMachine owner) {
    this.owner = owner;
    this.form = owner.getForm();
    requestHardwareAcceleration(form);
    WebView.setWebContentsDebuggingEnabled(true);
  }

  // Common for model
  @SuppressLint("SetJavaScriptEnabled")
  public void configureWebView(Object container) {
    WebView webview = (WebView) container;
    owner.webview = webview;
    webview.getSettings().setJavaScriptEnabled(true);
    webview.getSettings().setMediaPlaybackRequiresUserGesture(false);
    // adds a way to send strings to the javascript
    webview.addJavascriptInterface(new JsObject(), "TeachableMachine");


    webview.setWebViewClient(new WebViewClient() {
      @Override
      public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        Log.d(LOG_TAG, "shouldInterceptRequest called");

        Log.d(LOG_TAG, url);

        try {
          if ((url.startsWith(TeachableMachine.MODEL_URL))
              || (url.startsWith("https://cdn.jsdelivr.net/npm/"))) {
            return null;
          }
          InputStream file = null;
          String charSet;
          String contentType;
          String fileName;

          if (url.startsWith("http://localhost/")) {
            fileName = url.substring("http://localhost/".length());
            file = form.openAssetForExtension(owner, fileName);
          }
          if (url.endsWith(".json")) {
            contentType = "application/json";
            charSet = "UTF-8";
          } else {
            contentType = "application/octet-stream";
            charSet = "binary";
          }

          // For android permission
          if (file != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
              Map<String, String> responseHeaders = new HashMap<>();
              responseHeaders.put("Access-Control-Allow-Origin", "*");
              return new WebResourceResponse(contentType, charSet, 200, "OK", responseHeaders, file);
            } else {
              return new WebResourceResponse(contentType, charSet, file);
            }
          }

        } catch (IOException e) {
          e.printStackTrace();
          return super.shouldInterceptRequest(view, url);
        }

        return super.shouldInterceptRequest(view, url);
      }
      public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        final String url = request.getUrl().toString();
        Log.d(LOG_TAG, "shouldInterceptRequest called");

        return shouldInterceptRequest(view, url);
      }
    });

    // permission to capture video
    webview.setWebChromeClient(new WebChromeClient() {
      @Override
      public void onPermissionRequest(final PermissionRequest request) {
        Log.d(LOG_TAG, "onPermissionRequest called");

        String[] requestedResources = request.getResources();
        for (String r : requestedResources) {
          if (r.equals(PermissionRequest.RESOURCE_VIDEO_CAPTURE)) {
            request.grant(new String[]{PermissionRequest.RESOURCE_VIDEO_CAPTURE});
          }
        }
      }
    });
    webview.requestLayout();
  }

  public void assertWebView(String method) {
    if (owner.webview == null) {
      throw new RuntimeException(String.format(TeachableMachine.ERROR_WEBVIEWER_NOT_SET, method));
    }
  }

  public void evaluateJavascript(String js) {
    WebView webview = (WebView) owner.webview;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      webview.evaluateJavascript(js, null);
    } else {
      webview.loadUrl("javascript:" + js);
    }
  }

  public void loadUrl(String url) {
    WebView webview = (WebView) owner.webview;
    webview.loadUrl(url);
  }

  public void classifyImageData(String image) {
    Log.d(LOG_TAG, "Entered Classify");
    Log.d(LOG_TAG, image);
    String imagePath = (image == null) ? "" : image;
    BitmapDrawable imageDrawable;
    Bitmap scaledImageBitmap = null;

    try {
      imageDrawable = MediaUtil.getBitmapDrawable(form.$form(), imagePath);
      scaledImageBitmap = Bitmap.createScaledBitmap(imageDrawable.getBitmap(), IMAGE_WIDTH, (int) (imageDrawable.getBitmap().getHeight() * ((float) IMAGE_WIDTH) / imageDrawable.getBitmap().getWidth()), false);
    } catch (IOException ioe) {
      Log.e(LOG_TAG, "Unable to load " + imagePath);
    }

    // compression format of PNG -> not lossy
    Bitmap immagex = scaledImageBitmap;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    immagex.compress(Bitmap.CompressFormat.PNG, IMAGE_QUALITY, baos);
    byte[] b = baos.toByteArray();

    String imageEncodedbase64String = Base64.encodeToString(b, 0).replace("\n", "");
    Log.d(LOG_TAG, "imageEncodedbase64String: " + imageEncodedbase64String);

    evaluateJavascript("classifyImageData(\"" + imageEncodedbase64String + "\");");
  }

  public boolean shouldAskForPermission() {
    return form.getApplicationInfo().targetSdkVersion >= 23 &&
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
  }

  public void askForPermission(final Runnable next) {
    owner.getForm().askPermission(Manifest.permission.CAMERA, new PermissionResultHandler() {
      @Override
      public void HandlePermissionResponse(String permission, boolean granted) {
        if (granted) {
          next.run();
        } else {
          owner.getForm().PermissionDenied(owner, "WebViewer", permission);
        }
      }
    });
  }

  private static void requestHardwareAcceleration(Activity activity) {
    activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
  }

  private static List<String> parseLabels(String labels) {
    List<String> result = new ArrayList<>();
    try {
      JSONArray arr = new JSONArray(labels);
      for (int i = 0; i < arr.length(); i++) {
        result.add(arr.getString(i));
      }
    } catch (JSONException e) {
      throw new YailRuntimeError("Got unparsable array from Javascript", "RuntimeError");
    }
    return result;
  }

  private class JsObject {
    @JavascriptInterface
    public void ready(String labels) {
      Log.d(LOG_TAG, "Entered ready");

      owner.labels = parseLabels(labels);
      form.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          owner.ClassifierReady();
        }
      });
    }


    @JavascriptInterface
    public void reportResult(final String result) {
      Log.d(LOG_TAG, "Entered reportResult: " + result);
      try {
        Log.d(LOG_TAG, "Entered try of reportResult");
        JSONArray list = new JSONArray(result);
        final YailDictionary resultDict = new YailDictionary();
        for (int i = 0; i < list.length(); i++) {
          JSONArray pair = list.getJSONArray(i);
          resultDict.put(pair.getString(0), pair.getDouble(1));
        }
        Log.d(LOG_TAG, "Result Dict: " + resultDict);
        form.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            owner.GotClassification(resultDict);
          }

        });
      } catch (JSONException e) {
        Log.d(LOG_TAG, "Entered catch of reportResult");
        e.printStackTrace();
        owner.Error(TeachableMachine.ERROR_CLASSIFICATION_FAILED);
      }
    }

    @JavascriptInterface
    public void error(final int errorCode) {
      Log.d(LOG_TAG, "Entered error: " + errorCode);
      form.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          owner.Error(errorCode);
        }
      });
    }

    @JavascriptInterface
    public String isLoaded(boolean isComplete) {
      String modelLink;
      if (isComplete) {
        modelLink = owner.modelPath;

        Log.d(LOG_TAG, "Function in JsObject that is called from js");

        return modelLink;
      }
      return owner.modelPath;
    }
  }
}
