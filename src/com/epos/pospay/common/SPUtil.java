package com.epos.pospay.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Map;

public class SPUtil {
    public static final String FILE_NAME = "share_data";

    public static void put(Context context, String key, Object object) {
        try {
            Editor editor = context.getSharedPreferences("share_data", 0).edit();
            if (object instanceof String) {
                editor.putString(key, (String) object);
            } else if (object instanceof Integer) {
                editor.putInt(key, ((Integer) object).intValue());
            } else if (object instanceof Boolean) {
                editor.putBoolean(key, ((Boolean) object).booleanValue());
            } else if (object instanceof Float) {
                editor.putFloat(key, ((Float) object).floatValue());
            } else if (object instanceof Long) {
                editor.putLong(key, ((Long) object).longValue());
            } else if (object != null) {
                editor.putString(key, object.toString());
            }
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object get(Context context, String key, Object defaultObject) {
        try {
            SharedPreferences sp = context.getSharedPreferences("share_data", 0);
            if (defaultObject instanceof String) {
                return sp.getString(key, (String) defaultObject);
            }
            if (defaultObject instanceof Integer) {
                return Integer.valueOf(sp.getInt(key, ((Integer) defaultObject).intValue()));
            }
            if (defaultObject instanceof Boolean) {
                return Boolean.valueOf(sp.getBoolean(key, ((Boolean) defaultObject).booleanValue()));
            }
            if (defaultObject instanceof Float) {
                return Float.valueOf(sp.getFloat(key, ((Float) defaultObject).floatValue()));
            }
            if (defaultObject instanceof Long) {
                return Long.valueOf(sp.getLong(key, ((Long) defaultObject).longValue()));
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void remove(Context context, String key) {
        Editor editor = context.getSharedPreferences("share_data", 0).edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clear(Context context) {
        Editor editor = context.getSharedPreferences("share_data", 0).edit();
        editor.clear();
        editor.apply();
    }

    public static boolean contains(Context context, String key) {
        return context.getSharedPreferences("share_data", 0).contains(key);
    }

    public static Map<String, ?> getAll(Context context) {
        return context.getSharedPreferences("share_data", 0).getAll();
    }
}
