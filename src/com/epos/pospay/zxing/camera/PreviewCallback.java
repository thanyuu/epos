package com.epos.pospay.zxing.camera;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

public class PreviewCallback implements android.hardware.Camera.PreviewCallback {
    private static final String TAG = PreviewCallback.class.getSimpleName();
    private final CameraConfigurationManager configManager;
    private Handler previewHandler;
    private int previewMessage;

    public PreviewCallback(CameraConfigurationManager configManager) {
        this.configManager = configManager;
    }

    public void setHandler(Handler previewHandler, int previewMessage) {
        this.previewHandler = previewHandler;
        this.previewMessage = previewMessage;
    }

    public void onPreviewFrame(byte[] data, Camera camera) {
        Point cameraResolution = this.configManager.getCameraResolution();
        Handler thePreviewHandler = this.previewHandler;
        if (cameraResolution == null || thePreviewHandler == null) {
            Log.d(TAG, "Got preview callback, but no handler or resolution available");
            return;
        }
        thePreviewHandler.obtainMessage(this.previewMessage, cameraResolution.x, cameraResolution.y, data).sendToTarget();
        this.previewHandler = null;
    }
}
