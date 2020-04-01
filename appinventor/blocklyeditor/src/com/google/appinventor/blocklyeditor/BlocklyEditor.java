package com.google.appinventor.blocklyeditor;

import com.google.appinventor.blocklyeditor.i18n.Messages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsIgnore;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "Blockly")
public class BlocklyEditor {
  @JsOverlay
  private static boolean translationsLoaded = false;
  @JsOverlay
  private static final Set<TranslationCallback> pendingCallbacks = new HashSet<>();
  @JsOverlay
  private static boolean blocklyInitialized = false;

  @JsFunction
  public interface TranslationCallback {
    void run();
  }

  static {
    ((BlocklySource) GWT.create(BlocklySource.class)).initBlockly();
    loadTranslation();
    blocklyInitialized = true;
  }

  @JsOverlay
  public static void initialize() {
    // do nothing
    if (!blocklyInitialized) {
      throw new IllegalStateException("Unable to initialize Blockly");
    }
  }

  public static native WorkspaceSvg create(DivElement container, String formName, boolean readOnly, boolean rtl);

  @JsOverlay
  static void ensureTranslationsLoaded(@Nullable TranslationCallback callback) {
    if (callback != null) {
      if (translationsLoaded) {
        callback.run();
      } else {
        pendingCallbacks.add(callback);
      }
    }
  }

  @JsOverlay
  private static void loadTranslation() {
    try {
      ((Messages) GWT.create(Messages.class)).messages().getText(new ResourceCallback<TextResource>() {
        @Override
        public void onError(ResourceException e) {
        }

        @Override
        public void onSuccess(TextResource textResource) {
          JsniImpl.installTranslations(textResource.getText());
          translationsLoaded = true;
          for (TranslationCallback callback : pendingCallbacks) {
            try {
              callback.run();
            } catch (Exception e) {
              // foo
            }
          }
          pendingCallbacks.clear();
        }
      });
    } catch (ResourceException e) {
      // Should never happen?
    }
  }

  private static class JsniImpl {
    private static native void installTranslations(String translations)/*-{
      var messages = JSON.parse(translations);
      Object.keys(messages).forEach(function (key) {
        if (key.indexOf("Blockly.Msg.") === 0) {
          Blockly.Msg[key.replace("Blockly.Msg.", "")] = messages[key];
        }
      });
    }-*/;
  }
}
