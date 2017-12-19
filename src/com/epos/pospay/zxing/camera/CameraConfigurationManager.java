package com.epos.pospay.zxing.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class CameraConfigurationManager {
    private static final double MAX_ASPECT_DISTORTION = 0.15d;
    private static final int MIN_PREVIEW_PIXELS = 153600;
    private static final String TAG = "CameraConfiguration";
    private Point cameraResolution;
    private final Context context;
    private Point screenResolution;

    class C02741 implements Comparator<Size> {
        C02741() {
        }

        public int compare(Size a, Size b) {
            int aPixels = a.height * a.width;
            int bPixels = b.height * b.width;
            if (bPixels < aPixels) {
                return -1;
            }
            if (bPixels > aPixels) {
                return 1;
            }
            return 0;
        }
    }

    public CameraConfigurationManager(Context context) {
        this.context = context;
    }

    public void initFromCameraParameters(Camera camera) {
        Parameters parameters = camera.getParameters();
        Display display = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        this.screenResolution = getDisplaySize(display);
        Log.i(TAG, "Screen resolution: " + this.screenResolution);
        Point screenResolutionForCamera = new Point();
        screenResolutionForCamera.x = this.screenResolution.x;
        screenResolutionForCamera.y = this.screenResolution.y;
        if (this.screenResolution.x < this.screenResolution.y) {
            screenResolutionForCamera.x = this.screenResolution.y;
            screenResolutionForCamera.y = this.screenResolution.x;
        }
        this.cameraResolution = findBestPreviewSizeValue(parameters, screenResolutionForCamera);
        Log.i(TAG, "Camera resolution x: " + this.cameraResolution.x);
        Log.i(TAG, "Camera resolution y: " + this.cameraResolution.y);
    }

    @SuppressLint({"NewApi"})
    private Point getDisplaySize(Display display) {
        Point point = new Point();
        try {
            display.getSize(point);
        } catch (NoSuchMethodError e) {
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        return point;
    }

    public void setDesiredCameraParameters(Camera camera, boolean safeMode) {
        Parameters parameters = camera.getParameters();
        if (parameters == null) {
            Log.w(TAG, "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i(TAG, "Initial camera parameters: " + parameters.flatten());
        if (safeMode) {
            Log.w(TAG, "In camera config safe mode -- most settings will not be honored");
        }
        parameters.setPreviewSize(this.cameraResolution.x, this.cameraResolution.y);
        camera.setParameters(parameters);
        Size afterSize = camera.getParameters().getPreviewSize();
        if (!(afterSize == null || (this.cameraResolution.x == afterSize.width && this.cameraResolution.y == afterSize.height))) {
            Log.w(TAG, "Camera said it supported preview size " + this.cameraResolution.x + 'x' + this.cameraResolution.y + ", but after setting it, preview size is " + afterSize.width + 'x' + afterSize.height);
            this.cameraResolution.x = afterSize.width;
            this.cameraResolution.y = afterSize.height;
        }
        camera.setDisplayOrientation(90);
    }

    public Point getCameraResolution() {
        return this.cameraResolution;
    }

    public Point getScreenResolution() {
        return this.screenResolution;
    }

    private Point findBestPreviewSizeValue(Parameters parameters, Point screenResolution) {
        List<Size> rawSupportedSizes = parameters.getSupportedPreviewSizes();
        if (rawSupportedSizes == null) {
            Log.w(TAG, "Device returned no supported preview sizes; using default");
            Size defaultSize = parameters.getPreviewSize();
            return new Point(defaultSize.width, defaultSize.height);
        }
        Size supportedPreviewSize;
        List<Size> arrayList = new ArrayList(rawSupportedSizes);
        Collections.sort(arrayList, new C02741());
        if (Log.isLoggable(TAG, 4)) {
            StringBuilder previewSizesString = new StringBuilder();
            for (Size supportedPreviewSize2 : arrayList) {
                previewSizesString.append(supportedPreviewSize2.width).append('x').append(supportedPreviewSize2.height).append(' ');
            }
            Log.i(TAG, "Supported preview sizes: " + previewSizesString);
        }
        double screenAspectRatio = ((double) screenResolution.x) / ((double) screenResolution.y);
        Iterator<Size> it = arrayList.iterator();
        while (it.hasNext()) {
            supportedPreviewSize2 = (Size) it.next();
            int realWidth = supportedPreviewSize2.width;
            int realHeight = supportedPreviewSize2.height;
            if (realWidth * realHeight < MIN_PREVIEW_PIXELS) {
                it.remove();
            } else {
                int maybeFlippedWidth;
                int maybeFlippedHeight;
                boolean isCandidatePortrait = realWidth < realHeight;
                if (isCandidatePortrait) {
                    maybeFlippedWidth = realHeight;
                } else {
                    maybeFlippedWidth = realWidth;
                }
                if (isCandidatePortrait) {
                    maybeFlippedHeight = realWidth;
                } else {
                    maybeFlippedHeight = realHeight;
                }
                if (Math.abs((((double) maybeFlippedWidth) / ((double) maybeFlippedHeight)) - screenAspectRatio) > MAX_ASPECT_DISTORTION) {
                    it.remove();
                } else if (maybeFlippedWidth == screenResolution.x && maybeFlippedHeight == screenResolution.y) {
                    Point exactPoint = new Point(realWidth, realHeight);
                    Log.i(TAG, "Found preview size exactly matching screen size: " + exactPoint);
                    return exactPoint;
                }
            }
        }
        if (arrayList.isEmpty()) {
            Size defaultPreview = parameters.getPreviewSize();
            Point defaultSize2 = new Point(defaultPreview.width, defaultPreview.height);
            Log.i(TAG, "No suitable preview sizes, using default: " + defaultSize2);
            return defaultSize2;
        }
        Size largestPreview = (Size) arrayList.get(0);
        Point largestSize = new Point(largestPreview.width, largestPreview.height);
        Log.i(TAG, "Using largest suitable preview size: " + largestSize);
        return largestSize;
    }
}
