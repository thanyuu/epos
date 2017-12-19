package com.epos.pospay.zxing.utils;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.epos.pospay.C0258R;
import com.epos.pospay.capture.CaptureActivity;
import com.epos.pospay.zxing.camera.CameraManager;
import com.epos.pospay.zxing.decode.DecodeThread;
import com.google.zxing.Result;

public class CaptureActivityHandler extends Handler {
    private final CaptureActivity activity;
    private final CameraManager cameraManager;
    private final DecodeThread decodeThread;
    private State state = State.SUCCESS;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public CaptureActivityHandler(CaptureActivity activity, CameraManager cameraManager, int decodeMode) {
        this.activity = activity;
        this.decodeThread = new DecodeThread(activity, decodeMode);
        this.decodeThread.start();
        this.cameraManager = cameraManager;
        cameraManager.startPreview();
        restartPreviewAndDecode();
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case C0258R.id.decode_failed:
                this.state = State.PREVIEW;
                this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), C0258R.id.decode);
                return;
            case C0258R.id.decode_succeeded:
                this.state = State.SUCCESS;
                this.activity.handleDecode((Result) message.obj, message.getData());
                return;
            case C0258R.id.restart_preview:
                restartPreviewAndDecode();
                return;
            case C0258R.id.return_scan_result:
                this.activity.setResult(-1, (Intent) message.obj);
                this.activity.finish();
                return;
            default:
                return;
        }
    }

    public void quitSynchronously() {
        this.state = State.DONE;
        this.cameraManager.stopPreview();
        Message.obtain(this.decodeThread.getHandler(), C0258R.id.quit).sendToTarget();
        try {
            this.decodeThread.join(500);
        } catch (InterruptedException e) {
        }
        removeMessages(C0258R.id.decode_succeeded);
        removeMessages(C0258R.id.decode_failed);
    }

    private void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), C0258R.id.decode);
        }
    }
}
