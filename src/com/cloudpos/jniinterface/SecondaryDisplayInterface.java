package com.cloudpos.jniinterface;

public class SecondaryDisplayInterface {
    public static native int buzzerBeep();

    public static native int close();

    public static native int displayDefaultScreen();

    public static native int ledPower(int i);

    public static native int open();

    public static native int setBackground(int i);

    public static native int writePicture(int i, int i2, int i3, int i4, byte[] bArr, int i5);

    static {
        System.loadLibrary("jni_cloudpos_secondarydisplay");
    }
}
