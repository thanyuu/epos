package com.cloudpos.jniinterface;

public class SmartCardInterface {
    public static final int[] LOGICAL_ID = new int[]{0, 1, 2, 3};
    public static SmartCardEvent event;
    public static Object objAbsent = new Object();
    public static Object objPresent = new Object();

    public static native int close(int i);

    public static native int open(int i);

    public static native int powerOff(int i);

    public static native int powerOn(int i, byte[] bArr, SmartCardSlotInfo smartCardSlotInfo);

    public static native int queryMaxNumber();

    public static native int queryPresence(int i);

    public static native int read(int i, int i2, byte[] bArr, int i3);

    public static native int setSlotInfo(int i, SmartCardSlotInfo smartCardSlotInfo);

    public static native int transmit(int i, byte[] bArr, byte[] bArr2);

    public static native int verify(int i, byte[] bArr);

    public static native int write(int i, int i2, byte[] bArr, int i3);

    static {
        System.loadLibrary("jni_cloudpos_smartcard");
    }

    public static void callBack(byte[] data) {
        event = new SmartCardEvent();
        event.nEventID = data[0];
        event.nSlotIndex = data[1];
        if (data[0] == SmartCardEvent.SMART_CARD_EVENT_INSERT_CARD) {
            synchronized (objPresent) {
                objPresent.notifyAll();
            }
        } else if (data[0] == SmartCardEvent.SMART_CARD_EVENT_REMOVE_CARD) {
            synchronized (objAbsent) {
                objAbsent.notifyAll();
            }
        }
    }
}
