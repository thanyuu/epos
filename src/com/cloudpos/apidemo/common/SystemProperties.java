package com.cloudpos.apidemo.common;

import android.support.v4.os.EnvironmentCompat;
import android.util.Log;

public class SystemProperties {
    public static String getsystemPropertie(String key) {
        Object obj = null;
        try {
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            Log.i("systemProperties", systemProperties.toString());
            obj = systemProperties.getMethod("get", new Class[]{String.class, String.class}).invoke(systemProperties, new Object[]{key, EnvironmentCompat.MEDIA_UNKNOWN});
            Log.i("bootloaderVersion", obj.getClass().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
}
