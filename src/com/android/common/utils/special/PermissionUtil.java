package com.android.common.utils.special;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.util.Log;

public class PermissionUtil {
    private static final String APP_TAG = "PermissionUtil";

    public static boolean verifyPackageName(Context host, String packageName) {
        String caller = host.getPackageManager().getNameForUid(Binder.getCallingUid());
        Log.v(APP_TAG, new StringBuilder(String.valueOf(caller)).append(":invoker, verifyPackageName. caller = ").append(caller).toString());
        if (caller.equals(packageName)) {
            Log.v(APP_TAG, new StringBuilder(String.valueOf(caller)).append("invoke success").toString());
            return true;
        }
        Log.v(APP_TAG, new StringBuilder(String.valueOf(caller)).append("invoke failed").toString());
        return false;
    }

    public static boolean verifyPackageNames(Context host, String[] packageNames) {
        String caller = host.getPackageManager().getNameForUid(Binder.getCallingUid());
        Log.v(APP_TAG, new StringBuilder(String.valueOf(caller)).append(":invoker, verifyPackageNames.").toString());
        for (String pn : packageNames) {
            if (caller.equals(pn)) {
                Log.v(APP_TAG, new StringBuilder(String.valueOf(caller)).append("invoke success").toString());
                return true;
            }
        }
        return false;
    }

    public static boolean checkPermission(Context host, String permissionName) {
        String caller = host.getPackageManager().getNameForUid(Binder.getCallingUid());
        Log.v(APP_TAG, new StringBuilder(String.valueOf(caller)).append(":invoker, checkPermission").toString());
        try {
            for (String permission : host.getPackageManager().getPackageInfo(caller, 4096).requestedPermissions) {
                if (permission.equals(permissionName)) {
                    Log.v(APP_TAG, new StringBuilder(String.valueOf(caller)).append("invoke success , permission ").append(permission).toString());
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
