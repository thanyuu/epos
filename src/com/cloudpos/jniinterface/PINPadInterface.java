package com.cloudpos.jniinterface;

public class PINPadInterface {
    public static final int ALGORITH_3DES = 1;
    public static final int ALGORITH_DES = 0;
    public static final int KEY_TYPE_DUKPT = 0;
    public static final int KEY_TYPE_FIX = 4;
    public static final int KEY_TYPE_MASTER = 2;
    public static final int KEY_TYPE_PUBLIC = 3;
    public static final int KEY_TYPE_TDUKPT = 1;
    public static final int MAC_METHOD_ECB = 1;
    public static final int MAC_METHOD_X99 = 0;
    public static final int[] MASTER_KEY_ID = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static final int[] USER_KEY_ID = new int[]{0, 1};
    private static PinPadCallbackHandler callbackHandler;

    public static native int calculateMac(byte[] bArr, int i, int i2, byte[] bArr2);

    public static native int calculatePINBlock(byte[] bArr, int i, byte[] bArr2, int i2, int i3);

    public static native int close();

    public static native int encrypt(byte[] bArr, int i, byte[] bArr2);

    public static native int getSerialNo(byte[] bArr);

    public static native int open();

    public static native int selectKey(int i, int i2, int i3, int i4);

    public static native int setPinLength(int i, int i2);

    public static native int setPinblockCallback();

    public static native int showText(int i, byte[] bArr, int i2, int i3);

    public static native int updateCipherMasterKey(int i, byte[] bArr, int i2);

    public static native int updateMasterKey(int i, byte[] bArr, int i2, byte[] bArr2, int i3);

    public static native int updateUserKey(int i, int i2, byte[] bArr, int i3);

    public static native int updateUserKeyWithCheck(int i, int i2, byte[] bArr, int i3, int i4, byte[] bArr2, int i5);

    static {
        System.loadLibrary("jni_cloudpos_pinpad");
    }

    public static int setupCallbackHandler(PinPadCallbackHandler handler) {
        callbackHandler = handler;
        if (handler != null) {
            return setPinblockCallback();
        }
        return 0;
    }

    public static void pinpadCallback(byte[] data) {
        if (callbackHandler != null) {
            callbackHandler.processCallback(data);
        }
    }
}
