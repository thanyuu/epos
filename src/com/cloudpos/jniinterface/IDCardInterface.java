package com.cloudpos.jniinterface;

public class IDCardInterface {
    public static native int close();

    public static native int getInformation(IDCardProperty iDCardProperty);

    public static native int open();

    public static native int searchTarget();

    static {
        System.loadLibrary("jni_cloudpos_idcard");
    }
}
