package com.cloudpos.scanserver.aidl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.UnsupportedEncodingException;

public class ScanResult implements Parcelable {
    public static final Creator<ScanResult> CREATOR = new C02251();
    public static final int CUSTOM_CANCEL = 0;
    public static final int ERROR_DEVICE_OPEN_FAILED = -2;
    public static final int ERROR_OPEN_AGAIN = -1;
    public static final int ERROR_PARAMETER = -4;
    public static final int ERROR_SCAN_TIME_OUT = -3;
    public static final int ERROR_UNKNOWN = -999;
    private static final String KEY_BITMAP = "bitmap_buffer";
    private static final String KEY_FORMAT = "barcode_format";
    private static final String KEY_RAW_BUFFER = "raw_buffer";
    public static final int SCAN_INIT_COMPLETE = 2;
    public static final int SCAN_SUCCESS = 1;
    private String barcodeFormat;
    private Bitmap decodeBitmap;
    private byte[] rawBuffer;
    private int resultCode;
    private String text;

    static class C02251 implements Creator<ScanResult> {
        C02251() {
        }

        public ScanResult createFromParcel(Parcel source) {
            return new ScanResult(source);
        }

        public ScanResult[] newArray(int size) {
            return new ScanResult[size];
        }
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public byte[] getRawBuffer() {
        return this.rawBuffer;
    }

    public Bitmap getDecodeBitmap() {
        return this.decodeBitmap;
    }

    public String getText() {
        return this.text;
    }

    public String getBarcodeFormat() {
        return this.barcodeFormat;
    }

    public void setDecodeBitmap(Bitmap decodeBitmap) {
        this.decodeBitmap = decodeBitmap;
    }

    public ScanResult(int errorCode) {
        this.resultCode = ERROR_UNKNOWN;
        this.rawBuffer = null;
        this.decodeBitmap = null;
        this.text = null;
        this.barcodeFormat = null;
        this.resultCode = errorCode;
    }

    public ScanResult(byte[] rawBuffer, String barcodeFormat) {
        this.resultCode = ERROR_UNKNOWN;
        this.rawBuffer = null;
        this.decodeBitmap = null;
        this.text = null;
        this.barcodeFormat = null;
        this.resultCode = 1;
        this.rawBuffer = rawBuffer;
        this.barcodeFormat = barcodeFormat;
        try {
            this.text = new String(rawBuffer, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public ScanResult(Parcel source) {
        this.resultCode = ERROR_UNKNOWN;
        this.rawBuffer = null;
        this.decodeBitmap = null;
        this.text = null;
        this.barcodeFormat = null;
        this.resultCode = source.readInt();
        if (this.resultCode == 1) {
            Bundle bundle = source.readBundle();
            this.rawBuffer = bundle.getByteArray(KEY_RAW_BUFFER);
            this.barcodeFormat = bundle.getString(KEY_FORMAT);
            try {
                this.text = new String(this.rawBuffer, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] tempBuffer = bundle.getByteArray(KEY_BITMAP);
            if (tempBuffer != null) {
                this.decodeBitmap = convertBuffer2Bitmap(tempBuffer);
            }
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.resultCode);
        if (this.resultCode == 1) {
            Bundle bundle = new Bundle();
            bundle.putByteArray(KEY_RAW_BUFFER, this.rawBuffer);
            bundle.putString(KEY_FORMAT, this.barcodeFormat);
            if (this.decodeBitmap != null) {
                bundle.putByteArray(KEY_BITMAP, convertBitmap2Buffer(this.decodeBitmap));
            }
            dest.writeBundle(bundle);
        }
    }

    public String toString() {
        return String.format("resultCode = %s\n rawBuffer =%s\n text = %s\n format = %s\n decodeBitmap = %s ", new Object[]{Integer.valueOf(this.resultCode), this.rawBuffer, this.text, this.barcodeFormat, this.decodeBitmap});
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] convertBitmap2Buffer(android.graphics.Bitmap r6) {
        /*
        r5 = this;
        r2 = 0;
        r1 = new java.io.ByteArrayOutputStream;
        r1.<init>();
        r3 = android.graphics.Bitmap.CompressFormat.PNG;	 Catch:{ Exception -> 0x0018 }
        r4 = 100;
        r6.compress(r3, r4, r1);	 Catch:{ Exception -> 0x0018 }
        r6.recycle();	 Catch:{ Exception -> 0x0018 }
        r2 = r1.toByteArray();	 Catch:{ Exception -> 0x0018 }
        r1.close();	 Catch:{ Exception -> 0x0027 }
    L_0x0017:
        return r2;
    L_0x0018:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0022 }
        r1.close();	 Catch:{ Exception -> 0x0020 }
        goto L_0x0017;
    L_0x0020:
        r3 = move-exception;
        goto L_0x0017;
    L_0x0022:
        r3 = move-exception;
        r1.close();	 Catch:{ Exception -> 0x0029 }
    L_0x0026:
        throw r3;
    L_0x0027:
        r3 = move-exception;
        goto L_0x0017;
    L_0x0029:
        r4 = move-exception;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cloudpos.scanserver.aidl.ScanResult.convertBitmap2Buffer(android.graphics.Bitmap):byte[]");
    }

    private Bitmap convertBuffer2Bitmap(byte[] buffer) {
        return BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
    }

    public int describeContents() {
        return 0;
    }
}
