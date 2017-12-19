package com.epos.pospay.zxing.decode;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.epos.pospay.C0258R;
import com.epos.pospay.capture.CaptureActivity;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public class DecodeHandler extends Handler {
    private final CaptureActivity activity;
    private final MultiFormatReader multiFormatReader = new MultiFormatReader();
    private boolean running = true;

    public DecodeHandler(CaptureActivity activity, Map<DecodeHintType, Object> hints) {
        this.multiFormatReader.setHints(hints);
        this.activity = activity;
    }

    public void handleMessage(Message message) {
        if (this.running) {
            switch (message.what) {
                case C0258R.id.decode:
                    decode((byte[]) message.obj, message.arg1, message.arg2);
                    return;
                case C0258R.id.quit:
                    this.running = false;
                    Looper.myLooper().quit();
                    return;
                default:
                    return;
            }
        }
    }

    private void decode(byte[] data, int width, int height) {
        Size size = this.activity.getCameraManager().getPreviewSize();
        byte[] rotatedData = new byte[data.length];
        for (int y = 0; y < size.height; y++) {
            for (int x = 0; x < size.width; x++) {
                rotatedData[(((size.height * x) + size.height) - y) - 1] = data[(size.width * y) + x];
            }
        }
        int tmp = size.width;
        size.width = size.height;
        size.height = tmp;
        Result rawResult = null;
        PlanarYUVLuminanceSource source = buildLuminanceSource(rotatedData, size.width, size.height);
        if (source != null) {
            try {
                rawResult = this.multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(source)));
            } catch (ReaderException e) {
            } finally {
                this.multiFormatReader.reset();
            }
        }
        Handler handler = this.activity.getHandler();
        if (rawResult != null) {
            if (handler != null) {
                Message message = Message.obtain(handler, C0258R.id.decode_succeeded, rawResult);
                Bundle bundle = new Bundle();
                bundleThumbnail(source, bundle);
                message.setData(bundle);
                message.sendToTarget();
            }
        } else if (handler != null) {
            Message.obtain(handler, C0258R.id.decode_failed).sendToTarget();
        }
    }

    private static void bundleThumbnail(PlanarYUVLuminanceSource source, Bundle bundle) {
        int[] pixels = source.renderThumbnail();
        int width = source.getThumbnailWidth();
        Bitmap bitmap = Bitmap.createBitmap(pixels, 0, width, width, source.getThumbnailHeight(), Config.ARGB_8888);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 50, out);
        bundle.putByteArray(DecodeThread.BARCODE_BITMAP, out.toByteArray());
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] data, int width, int height) {
        Rect rect = this.activity.getCropRect();
        if (rect == null) {
            return null;
        }
        return new PlanarYUVLuminanceSource(data, width, height, rect.left, rect.top, rect.width(), rect.height(), false);
    }
}
