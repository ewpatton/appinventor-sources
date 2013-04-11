package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.YaVersion;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * The Network component provides a basic interface for app developers
 * to access information about the device's networking state and respond
 * to when the networking state changes (e.g. due to dropping a cellular
 * connection or WiFi being enabled).
 * 
 * @author Evan W. Patton <ewpatton@gmail.com>
 *
 */
@DesignerComponent(version = YaVersion.NETWORK_COMPONENT_VERSION,
    description = "Provides basic information about the network connectivity of the device",
    category = ComponentCategory.MISC,
    nonVisible = true,
    iconName = "images/network.png")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.ACCESS_NETWORK_STATE,android.permission.ACCESS_WIFI_STATE")
public class Network extends AndroidNonvisibleComponent implements Component {

  private final Activity activity;
  private boolean connected = false;
  private String type = "NONE";

  /**
   * Creates a Network component and registers the broadcast receiver
   * in response to connectivity actions in ConnectivityManager
   * @see android.net.ConnectivityManager
   * @param container the Form that this component is contained in.
   */
  public Network(ComponentContainer container) {
    super(container.$form());
    activity = container.$context();
    IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    activity.registerReceiver(receiver, filter);
  }

  /**
   * Returns whether the device has an active network connection.
   * @return true if the device is connected to the internet, false otherwise
   */
  @SimpleFunction
  public boolean Connected() {
    return connected;
  }

  /**
   * Returns the type of the active network connection, e.g. "wifi" or "mobile"
   * @return
   */
  @SimpleFunction
  public String Type() {
    return type;
  }

  /**
   * Fired when the device goes from a disconnected state to a connected state.
   */
  @SimpleEvent
  public void OnConnect() {
    EventDispatcher.dispatchEvent(this, "OnConnect");
  }

  /**
   * Fired when the device goes from a connected state to a disconnected state.
   */
  @SimpleEvent
  public void OnDisconnect() {
    EventDispatcher.dispatchEvent(this, "OnDisconnect");
  }

  /**
   * Stores the broadcast receiver used to respond to connectivity events.
   */
  private final BroadcastReceiver receiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      connected = !intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
      if(connected) {
        ConnectivityManager mgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mgr.getActiveNetworkInfo();
        if(info.getTypeName() == null) {
          type = "unknown";
        } else {
          type = info.getTypeName().toLowerCase();
        }
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            OnConnect();
          }
        });
      } else {
        type = "none";
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            OnDisconnect();
          }
        });
      }
    }
  };
}
