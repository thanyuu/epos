package com.cloudpos.jniinterface;

public class PrinterInterface {
    public static native int begin();

    public static native int close();

    public static native int end();

    public static native int open();

    public static native int queryStatus();

    public static native int queryVoltage(int[] iArr, int[] iArr2);

    public static native int write(byte[] bArr, int i);

    static {
        System.loadLibrary("jni_cloudpos_printer");
    }
}
