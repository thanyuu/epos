package com.cloudpos.jniinterface;

import android.util.Log;

public class MSRInterface {
    public static int CONTACTLESS_CARD_EVENT_FOUND_CARD = 0;
    public static int CONTACTLESS_CARD_EVENT_USER_CANCEL = 3;
    public static int TRACK_COUNT = 3;
    public static int eventID = -4;
    public static Object object = new Object();

    public static native int close();

    public static native int getTrackData(int i, byte[] bArr, int i2);

    public static native int getTrackDataLength(int i);

    public static native int getTrackError(int i);

    public static native int open();

    public static native int poll(int i);

    static {
        System.loadLibrary("jni_cloudpos_msr");
    }

    public static void callBack(int nEventID) {
        synchronized (object) {
            Log.i("MSRCard", "notify");
            eventID = nEventID;
            object.notifyAll();
        }
    }
}
