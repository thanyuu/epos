package com.cloudpos.jniinterface;

public class LEDInterface {
    public static native int close();

    public static native int getStatus(int i);

    public static native int open();

    public static native int turnOff(int i);

    public static native int turnOn(int i);

    static {
        System.loadLibrary("jni_cloudpos_led");
    }
}
