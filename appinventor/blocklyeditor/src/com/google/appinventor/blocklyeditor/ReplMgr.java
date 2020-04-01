package com.google.appinventor.blocklyeditor;

import com.google.appinventor.blocklyeditor.api.ReplListener;
import com.google.gwt.core.client.JavaScriptException;
import java.util.HashSet;
import java.util.Set;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "Blockly")
public class ReplMgr {

  @JsOverlay
  private static final Set<ReplListener> replListeners = new HashSet<>();

  @JsOverlay
  public static void addReplListener(ReplListener listener) {
    replListeners.add(listener);
  }

  @JsOverlay
  public static void removeReplListener(ReplListener listener) {
    replListeners.remove(listener);
  }

  @JsOverlay
  public static void indicateDisconnect() {
    for (ReplListener listener : replListeners) {
      listener.onDisconnect();
    }
  }

  @JsOverlay
  public static boolean pushScreen(String newScreen) {
    for (ReplListener listener : replListeners) {
      if (!listener.onPushScreen(newScreen)) {
        return false;
      }
    }
    return true;
  }

  @JsOverlay
  public static void popScreen() {
    for (ReplListener listener : replListeners) {
      listener.onPopScreen();
    }
  }

  public static native void ehardreset(String formName);

  public static native void startRepl(boolean alreadyRunning, boolean forEmulator, boolean forUsb);

  public static native void pollYail(WorkspaceSvg workspace) throws JavaScriptException;

  public static native void resetYail(boolean partial);

  public static native String makeqrcode(String content);

  public static native void triggerUpdate();

  public static native void sendFormData(String formJson, String packageName, WorkspaceSvg workspace);

  public static native boolean isConnected();

  @JsOverlay
  public static void resendAssetsAndExtensions() {
    JsniImpl.resendAssetsAndExtensions();
  }

  @JsOverlay
  public static void resendExtensionsList() {
    JsniImpl.resendExtensionsList();
  }

  private static class JsniImpl {
    public static native void resendAssetsAndExtensions()/*-{
      if (top.ReplState && (top.ReplState.state === Blockly.ReplMgr.rsState.CONNECTED ||
        top.ReplState.state === Blockly.ReplMgr.rsState.EXTENSIONS ||
        top.ReplState.state === Blockly.ReplMgr.rsState.ASSET)) {
        Blockly.ReplMgr.resendAssetsAndExtensions();
      }
    }-*/;

    public static native void resendExtensionsList()/*-{
      if (top.ReplState && top.ReplState.state === Blockly.ReplMgr.rsState.CONNECTED) {
        Blockly.ReplMgr.loadExtensions();
      }
    }-*/;
  }
}
