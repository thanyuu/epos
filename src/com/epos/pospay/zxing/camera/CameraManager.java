package com.epos.pospay.zxing.camera;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.epos.pospay.zxing.camera.open.OpenCameraInterface;
import java.io.IOException;

public class CameraManager {
    private static final String TAG = CameraManager.class.getSimpleName();
    private AutoFocusManager autoFocusManager;
    private Camera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private boolean initialized;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private int requestedCameraId = -1;

    public CameraManager(Context context) {
        this.context = context;
        this.configManager = new CameraConfigurationManager(context);
        this.previewCallback = new PreviewCallback(this.configManager);
    }

    public synchronized void openDriver(SurfaceHolder holder) throws IOException {
        Camera theCamera = this.camera;
        if (theCamera == null) {
            if (this.requestedCameraId >= 0) {
                theCamera = OpenCameraInterface.open(this.requestedCameraId);
            } else {
                theCamera = OpenCameraInterface.open();
            }
            if (theCamera == null) {
                throw new IOException();
            }
            this.camera = theCamera;
        }
        theCamera.setPreviewDisplay(holder);
        if (!this.initialized) {
            this.initialized = true;
            this.configManager.initFromCameraParameters(theCamera);
        }
        Parameters parameters = theCamera.getParameters();
        String parametersFlattened = parameters == null ? null : parameters.flatten();
        try {
            this.configManager.setDesiredCameraParameters(theCamera, false);
        } catch (RuntimeException e) {
            Log.w(TAG, "Camera rejected parameters. Setting only minimal safe-mode parameters");
            Log.i(TAG, "Resetting to saved camera params: " + parametersFlattened);
            if (parametersFlattened != null) {
                parameters = theCamera.getParameters();
                parameters.unflatten(parametersFlattened);
                try {
                    theCamera.setParameters(parameters);
                    this.configManager.setDesiredCameraParameters(theCamera, true);
                } catch (RuntimeException e2) {
                    Log.w(TAG, "Camera rejected even safe-mode parameters! No configuration");
                }
            }
        }
    }

    public synchronized boolean isOpen() {
        return this.camera != null;
    }

    public synchronized void closeDriver() {
        if (this.camera != null) {
            this.camera.release();
            this.camera = null;
        }
    }

    public synchronized void startPreview() {
        Camera theCamera = this.camera;
        if (!(theCamera == null || this.previewing)) {
            theCamera.startPreview();
            this.previewing = true;
            this.autoFocusManager = new AutoFocusManager(this.context, this.camera);
        }
    }

    public synchronized void stopPreview() {
        if (this.autoFocusManager != null) {
            this.autoFocusManager.stop();
            this.autoFocusManager = null;
        }
        if (this.camera != null && this.previewing) {
            this.camera.stopPreview();
            this.previewCallback.setHandler(null, 0);
            this.previewing = false;
        }
    }

    public synchronized void requestPreviewFrame(Handler handler, int message) {
        Camera theCamera = this.camera;
        if (theCamera != null && this.previewing) {
            this.previewCallback.setHandler(handler, message);
            theCamera.setOneShotPreviewCallback(this.previewCallback);
        }
    }

    public synchronized void setManualCameraId(int cameraId) {
        this.requestedCameraId = cameraId;
    }

    public Point getCameraResolution() {
        return this.configManager.getCameraResolution();
    }

    public Size getPreviewSize() {
        if (this.camera != null) {
            return this.camera.getParameters().getPreviewSize();
        }
        return null;
    }
}
