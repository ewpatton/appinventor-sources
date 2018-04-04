// -*- mode: java; c-basic-offset: 2; -*-
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

/**
 * @Author NMD (Next Mobile Development) [nmdofficialhelp@gmail.com]
 * To use this class: import com.google.appinventor.components.runtime.util.PermissionUtil;
 * copy 'PermissionUtil.checkRuntimePermission(this);' method to Form.java before 'Initialize();'
 */

package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.util.Log;
import android.support.v4.app.ActivityCompat;
import java.util.*;

import android.net.Uri;
import android.provider.Settings;
import android.content.Intent;


public class PermissionUtil {
    private PermissionUtil() {
    }

    private static String LOG_TAG = "Permission Util";


   /**
    * Helper to check if the needed permission is granted | for >= Api 26 | since target api is > 22
    * @Author NMD (Next Mobile Development) [nmdofficialhelp@gmail.com]
    *
    * @param context  the application context
    */
    public static void checkRuntimePermission(final Context context) {
        if (VERSION.SDK_INT < 23) {
            //nothing to do
            Log.i(LOG_TAG, "No permission check needed since api level is "+VERSION.SDK_INT+" and not >= 23");
            return;
        }
        ArrayList<String> permissionList = new ArrayList<String>();
        String[] permission = getNeededPermissions(context);

        if(permission == null || permission.length == 0) {
            Log.i(LOG_TAG, "No need to grant any permission since no needed permission was found in the manifest file.");
            return;
        }
        for (int i = 0; i < permission.length; i++) {
            if (ActivityCompat.checkSelfPermission((Activity)context, permission[i]) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission[i]);
            }
        }
        final String[] newPermissionsArray = permissionList.toArray(new String[permissionList.size()]);
        if(newPermissionsArray == null || newPermissionsArray.length == 0) {
            Log.i(LOG_TAG, "No need to give a permit. Maybe they were given before.");
            return;
        }
    ActivityCompat.requestPermissions((Activity)context, newPermissionsArray, 1);
    }

   /**
    * Helper to get all needed app permissions from the application
    * @Author NMD (Next Mobile Development) [nmdofficialhelp@gmail.com]
    *
    * @param context  the application context
    * @return  String []
    *
    */
    public static String[] getNeededPermissions(Context context) {
    PackageManager packageManager = context.getPackageManager();
    ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    return packageInfo.requestedPermissions;
    }

   /**
    * Helper to return a boolean if all needed app permissions was granted or not
    * @Author NMD (Next Mobile Development) [nmdofficialhelp@gmail.com]
    *
    * @param context  the application context
    * @return  result (boolean)
    *
    */
    public static boolean arePermissionsGranted(final Context context) {
    boolean result = false;
    String[] permission = getNeededPermissions(context);

        if(permission == null || permission.length == 0) {
            Log.i(LOG_TAG, "No need to grant any permission since no needed permission was found in the manifest file.");
            return true;
        }

        for (int i = 0; i < permission.length; i++) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)context, permission[i])) {
                result = false;
            } else {
                if (ActivityCompat.checkSelfPermission((Activity)context, permission[i]) == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            }
        }
    return result;
    }

    /**
     * Helper to open the current applications settings page
     * @Author NMD (Next Mobile Development) [nmdofficialhelp@gmail.com]
     *
     * @param context  the application context
     *
     */
    public static void appSettings(final Context context) {
    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
    Uri.fromParts("package", context.getPackageName(), null));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
    }

}
