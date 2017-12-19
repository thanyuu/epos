package com.cloudpos.jniinterface;

public class SerialPortInterface2 {
    public static native int close();

    public static native int flushIO();

    public static native int open(String str);

    public static native int read(byte[] bArr, int i, int i2, int i3);

    public static native int setBaudrate(int i);

    public static native int write(byte[] bArr, int i);

    static {
        System.loadLibrary("jni_cloudpos_serial2");
    }
}
