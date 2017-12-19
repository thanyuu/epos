package com.android.common.utils;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ParcelUtils {
    public static boolean readBoolean(Parcel in) {
        return in.readInt() == 1;
    }

    public static void writeBoolean(boolean b, Parcel out) {
        out.writeInt(b ? 1 : 0);
    }

    public static Map<String, String> readHashMapStringAndString(Parcel in) {
        Map<String, String> map = null;
        if (in != null) {
            int size = in.readInt();
            if (size != -1) {
                map = new HashMap();
                for (int i = 0; i < size; i++) {
                    map.put(in.readString(), in.readString());
                }
            }
        }
        return map;
    }

    public static void writeHashMapStringAndString(Map<String, String> map, Parcel out, int flags) {
        if (map != null) {
            out.writeInt(map.size());
            for (Entry<String, String> entry : map.entrySet()) {
                out.writeString((String) entry.getKey());
                out.writeString((String) entry.getValue());
            }
            return;
        }
        out.writeInt(-1);
    }

    public static <V extends Parcelable> Map<String, V> readHashMapStringKey(Parcel in, ClassLoader loader) {
        Map<String, V> map = null;
        if (in != null) {
            int size = in.readInt();
            if (size != -1) {
                map = new HashMap();
                for (int i = 0; i < size; i++) {
                    map.put(in.readString(), in.readParcelable(loader));
                }
            }
        }
        return map;
    }

    public static <V extends Parcelable> void writeHashMapStringKey(Map<String, V> map, Parcel out, int flags) {
        if (map != null) {
            out.writeInt(map.size());
            for (Entry<String, V> entry : map.entrySet()) {
                out.writeString((String) entry.getKey());
                out.writeParcelable((Parcelable) entry.getValue(), flags);
            }
            return;
        }
        out.writeInt(-1);
    }

    public static <K extends Parcelable, V extends Parcelable> Map<K, V> readHashMap(Parcel in, ClassLoader loader) {
        Map<K, V> map = null;
        if (in != null) {
            int size = in.readInt();
            if (size != -1) {
                map = new HashMap();
                for (int i = 0; i < size; i++) {
                    map.put(in.readParcelable(loader), in.readParcelable(loader));
                }
            }
        }
        return map;
    }

    public static <K extends Parcelable, V extends Parcelable> void writeHashMap(Map<K, V> map, Parcel out, int flags) {
        if (map != null) {
            out.writeInt(map.size());
            for (Entry<K, V> entry : map.entrySet()) {
                out.writeParcelable((Parcelable) entry.getKey(), flags);
                out.writeParcelable((Parcelable) entry.getValue(), flags);
            }
            return;
        }
        out.writeInt(-1);
    }
}
