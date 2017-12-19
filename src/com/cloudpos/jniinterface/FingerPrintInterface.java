package com.cloudpos.jniinterface;

public class FingerPrintInterface {
    public static native int cancel();

    public static native int close();

    public static native int getFea(byte[] bArr, int i, int[] iArr, int i2);

    public static native int getLastImage(byte[] bArr, int i, int[] iArr, int[] iArr2, int[] iArr3);

    public static native int match(byte[] bArr, int i, byte[] bArr2, int i2);

    public static native int open();

    static {
        System.loadLibrary("jni_cloudpos_fingerprint");
    }
}
