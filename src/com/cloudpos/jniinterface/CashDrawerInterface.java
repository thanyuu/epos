package com.cloudpos.jniinterface;

public class CashDrawerInterface {
    public static native int close();

    public static native int kickOut();

    public static native int open();

    static {
        System.loadLibrary("jni_cloudpos_cashdrawer");
    }
}
