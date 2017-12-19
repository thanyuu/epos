package com.epos.pospay.zxing.decode;

import android.os.Handler;
import android.os.Looper;
import com.epos.pospay.capture.CaptureActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class DecodeThread extends Thread {
    public static final int ALL_MODE = 768;
    public static final String BARCODE_BITMAP = "barcode_bitmap";
    public static final int BARCODE_MODE = 256;
    public static final int QRCODE_MODE = 512;
    private final CaptureActivity activity;
    private Handler handler;
    private final CountDownLatch handlerInitLatch = new CountDownLatch(1);
    private final Map<DecodeHintType, Object> hints = new EnumMap(DecodeHintType.class);

    public DecodeThread(CaptureActivity activity, int decodeMode) {
        this.activity = activity;
        Collection<BarcodeFormat> decodeFormats = new ArrayList();
        decodeFormats.addAll(EnumSet.of(BarcodeFormat.AZTEC));
        decodeFormats.addAll(EnumSet.of(BarcodeFormat.PDF_417));
        switch (decodeMode) {
            case 256:
                decodeFormats.addAll(DecodeFormatManager.getBarCodeFormats());
                break;
            case 512:
                decodeFormats.addAll(DecodeFormatManager.getQrCodeFormats());
                break;
            case ALL_MODE /*768*/:
                decodeFormats.addAll(DecodeFormatManager.getBarCodeFormats());
                decodeFormats.addAll(DecodeFormatManager.getQrCodeFormats());
                break;
        }
        this.hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
    }

    public Handler getHandler() {
        try {
            this.handlerInitLatch.await();
        } catch (InterruptedException e) {
        }
        return this.handler;
    }

    public void run() {
        Looper.prepare();
        this.handler = new DecodeHandler(this.activity, this.hints);
        this.handlerInitLatch.countDown();
        Looper.loop();
    }
}
