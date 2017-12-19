package com.cloudpos.jniinterface;

public class RFCardInterface {
    public static final int CONTACTLESS_CARD_EVENT_COMM_ERROR = 2;
    public static final int CONTACTLESS_CARD_EVENT_FOUND_CARD = 0;
    public static final int CONTACTLESS_CARD_EVENT_TIME_OUT = 1;
    public static final int CONTACTLESS_CARD_EVENT_USER_CANCEL = 3;
    public static int CONTACTLESS_CARD_MODE_AUTO = 0;
    public static int CONTACTLESS_CARD_MODE_TYPE_A = 1;
    public static int CONTACTLESS_CARD_MODE_TYPE_B = 2;
    public static int CONTACTLESS_CARD_MODE_TYPE_C = 3;
    public static final int NONE = -1;
    public static int RC500_COMMON_CMD_GET_READER_VERSION = 64;
    public static int RC500_COMMON_CMD_RF_CONTROL = 56;
    public static byte[] arryEventData = null;
    public static int nEventID = -1;
    public static Object object = new Object();

    public static native int attach(byte[] bArr);

    public static native int close();

    public static native int decrement(int i, int i2, int i3);

    public static native int detach();

    public static native int increment(int i, int i2, int i3);

    public static native int open();

    public static native int queryInfo(int[] iArr, int[] iArr2);

    public static native int read(int i, int i2, byte[] bArr, int i3);

    public static native int readValue(int i, int i2, byte[] bArr, int i3, byte[] bArr2);

    public static native int restore(int i, int i2);

    public static native int searchBegin(int i, int i2, int i3);

    public static native int searchEnd();

    public static native int sendControlCommand(int i, byte[] bArr, int i2);

    public static native int transfer(int i, int i2);

    public static native int transmit(byte[] bArr, int i, byte[] bArr2);

    public static native int verify(int i, int i2, byte[] bArr, int i3);

    public static native int write(int i, int i2, byte[] bArr, int i3);

    public static native int writeValue(int i, int i2, int i3, int i4, byte b);

    static {
        System.loadLibrary("jni_cloudpos_rfcard");
    }

    public static void callBack(int nEvent, byte[] nData) {
        nEventID = nEvent;
        arryEventData = nData;
        synchronized (object) {
            object.notifyAll();
        }
    }
}
