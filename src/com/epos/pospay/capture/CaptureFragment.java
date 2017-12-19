package com.epos.pospay.capture;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.epos.pospay.C0258R;
import com.epos.pospay.MainActivity;
import com.epos.pospay.common.BaseFragment;
import com.epos.pospay.common.IntentUtil;
import com.epos.pospay.zxing.camera.CameraManager;
import com.epos.pospay.zxing.decode.DecodeThread;
import com.epos.pospay.zxing.utils.BeepManager;
import com.epos.pospay.zxing.utils.CaptureActivityHandler;
import com.epos.pospay.zxing.utils.InactivityTimer;
import com.google.zxing.Result;
import java.io.IOException;

public class CaptureFragment extends BaseFragment implements Callback {
    private static final String TAG = CaptureActivity.class.getSimpleName();
    private BeepManager beepManager;
    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private InactivityTimer inactivityTimer;
    private boolean isHasSurface = false;
    private Rect mCropRect = null;
    private RelativeLayout scanContainer;
    private RelativeLayout scanCropView;
    private ImageView scanLine;
    private SurfaceView scanPreview = null;

    class C02631 implements OnClickListener {
        C02631() {
        }

        public void onClick(View v) {
            IntentUtil.gotoMainActivity(CaptureFragment.this.getActivity());
            CaptureFragment.this.getActivity().finish();
        }
    }

    class C02642 implements DialogInterface.OnClickListener {
        C02642() {
        }

        public void onClick(DialogInterface dialog, int which) {
            CaptureFragment.this.getActivity().finish();
        }
    }

    class C02653 implements OnCancelListener {
        C02653() {
        }

        public void onCancel(DialogInterface dialog) {
            CaptureFragment.this.getActivity().finish();
        }
    }

    public Handler getHandler() {
        return this.handler;
    }

    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.scanPreview = (SurfaceView) view.findViewById(C0258R.id.capture_preview);
        this.scanContainer = (RelativeLayout) view.findViewById(C0258R.id.capture_container);
        this.scanCropView = (RelativeLayout) view.findViewById(C0258R.id.capture_crop_view);
        this.scanLine = (ImageView) view.findViewById(C0258R.id.capture_scan_line);
        this.inactivityTimer = new InactivityTimer(getActivity());
        this.beepManager = new BeepManager(getActivity());
        TranslateAnimation animation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, 0.0f, 2, 0.9f);
        animation.setDuration(4500);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(1);
        this.scanLine.startAnimation(animation);
        view.findViewById(C0258R.id.btn_cancle).setOnClickListener(new C02631());
    }

    protected int getContentViewResId() {
        return C0258R.layout.fragment_capture;
    }

    public void onResume() {
        super.onResume();
        this.cameraManager = new CameraManager(getActivity().getApplication());
        this.handler = null;
        if (this.isHasSurface) {
            initCamera(this.scanPreview.getHolder());
        } else {
            this.scanPreview.getHolder().addCallback(this);
        }
        this.inactivityTimer.onResume();
    }

    public void onPause() {
        if (this.handler != null) {
            this.handler.quitSynchronously();
            this.handler = null;
        }
        this.inactivityTimer.onPause();
        this.beepManager.close();
        this.cameraManager.closeDriver();
        if (!this.isHasSurface) {
            this.scanPreview.getHolder().removeCallback(this);
        }
        super.onPause();
    }

    public void onDestroy() {
        this.inactivityTimer.shutdown();
        super.onDestroy();
    }

    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!this.isHasSurface) {
            this.isHasSurface = true;
            initCamera(holder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        this.isHasSurface = false;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void handleDecode(Result rawResult, Bundle bundle) {
        this.inactivityTimer.onActivity();
        this.beepManager.playBeepSoundAndVibrate();
        bundle.putInt("width", this.mCropRect.width());
        bundle.putInt("height", this.mCropRect.height());
        bundle.putString("result", rawResult.getText());
        Log.e(TAG, "handleDecode: " + rawResult.getText());
        startActivity(new Intent(getActivity(), MainActivity.class).putExtras(bundle));
        getActivity().finish();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        } else if (this.cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
        } else {
            try {
                this.cameraManager.openDriver(surfaceHolder);
                if (this.handler == null) {
                    this.handler = new CaptureActivityHandler((CaptureActivity) getActivity(), this.cameraManager, DecodeThread.ALL_MODE);
                }
                initCrop();
            } catch (IOException ioe) {
                Log.w(TAG, ioe);
                displayFrameworkBugMessageAndExit();
            } catch (RuntimeException e) {
                Log.w(TAG, "Unexpected error initializing camera", e);
                displayFrameworkBugMessageAndExit();
            }
        }
    }

    private void displayFrameworkBugMessageAndExit() {
        Builder builder = new Builder(getActivity());
        builder.setTitle(getString(C0258R.string.app_name));
        builder.setMessage("相机打开出错，请稍后重试");
        builder.setPositiveButton("确定", new C02642());
        builder.setOnCancelListener(new C02653());
        builder.show();
    }

    public void restartPreviewAfterDelay(long delayMS) {
        if (this.handler != null) {
            this.handler.sendEmptyMessageDelayed(C0258R.id.restart_preview, delayMS);
        }
    }

    public Rect getCropRect() {
        return this.mCropRect;
    }

    private void initCrop() {
        int cameraWidth = this.cameraManager.getCameraResolution().y;
        int cameraHeight = this.cameraManager.getCameraResolution().x;
        int[] location = new int[2];
        this.scanCropView.getLocationInWindow(location);
        int cropLeft = location[0];
        int cropTop = (location[1] - getStatusBarHeight()) - getNavigationBarHeight();
        int cropWidth = this.scanCropView.getWidth();
        int cropHeight = this.scanCropView.getHeight();
        int containerWidth = this.scanContainer.getWidth();
        int containerHeight = this.scanContainer.getHeight();
        int x = (cropLeft * cameraWidth) / containerWidth;
        int y = (cropTop * cameraHeight) / containerHeight;
        this.mCropRect = new Rect(x, y, ((cropWidth * cameraWidth) / containerWidth) + x, ((cropHeight * cameraHeight) / containerHeight) + y);
    }

    private int getStatusBarHeight() {
        int result = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            result = getResources().getDimensionPixelSize(Integer.parseInt(c.getField("status_bar_height").get(c.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private int getNavigationBarHeight() {
        return getResources().getDimensionPixelSize(C0258R.dimen.title_bar_height);
    }
}
