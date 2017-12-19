package com.epos.pospay.capture;

import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;
import com.cloudpos.scanserver.aidl.AidlController;
import com.cloudpos.scanserver.aidl.IAIDLListener;
import com.cloudpos.scanserver.aidl.IScanCallBack.Stub;
import com.cloudpos.scanserver.aidl.IScanService;
import com.cloudpos.scanserver.aidl.ScanParameter;
import com.cloudpos.scanserver.aidl.ScanResult;
import com.epos.pospay.C0258R;
import com.epos.pospay.MainActivity;
import com.epos.pospay.common.BaseActivity;
import com.epos.pospay.common.BaseFragment;
import com.epos.pospay.common.IntentUtil;
import com.epos.pospay.zxing.camera.CameraManager;
import com.google.zxing.Result;

public class CaptureActivity extends BaseActivity implements IAIDLListener {
    private Handler handler = new C02622();
    private IAIDLListener listener;
    private ServiceConnection scanConn;
    private IScanService scanService;

    class C02622 extends Handler {
        C02622() {
        }

        public void handleMessage(Message msg) {
            ScanResult obj = msg.obj;
            switch (msg.what) {
                case 0:
                    Toast.makeText(CaptureActivity.this, "user cancel", 0).show();
                    return;
                case 1:
                    try {
                        CaptureActivity.this.scanService.stopScan();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("result", obj.getText());
                    CaptureActivity.this.startActivity(new Intent(CaptureActivity.this, MainActivity.class).putExtras(bundle));
                    return;
                case 2:
                    CaptureActivity.this.stopLoadingProgress();
                    return;
                default:
                    CaptureActivity.this.stopLoadingProgress();
                    Toast.makeText(CaptureActivity.this, obj.getText(), 0).show();
                    return;
            }
        }
    }

    class C06741 extends Stub {
        C06741() {
        }

        public void foundBarcode(ScanResult result) {
            Message message = new Message();
            message.what = result.getResultCode();
            message.obj = result;
            CaptureActivity.this.handler.sendMessage(message);
        }
    }

    protected int getContentViewResId() {
        return C0258R.layout.capture_layout;
    }

    protected int getContent() {
        return C0258R.id.content_fragment;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TextView) findViewById(C0258R.id.topTv)).setText(C0258R.string.scan);
    }

    protected void onResume() {
        super.onResume();
        init();
    }

    protected Class<? extends BaseFragment> getContentFragmentClass() {
        return EmptyFragment.class;
    }

    private CaptureFragment getCaptureFragment() {
        return (CaptureFragment) getFragment();
    }

    public void handleDecode(Result rawResult, Bundle bundle) {
        getCaptureFragment().handleDecode(rawResult, bundle);
    }

    public Handler getHandler() {
        return getCaptureFragment().getHandler();
    }

    public CameraManager getCameraManager() {
        return getCaptureFragment().getCameraManager();
    }

    public Rect getCropRect() {
        return getCaptureFragment().getCropRect();
    }

    public void onBackPressed() {
        super.onBackPressed();
        IntentUtil.gotoMainActivity(this);
        finish();
    }

    private void init() {
        this.listener = this;
        AidlController.getInstance().startScanService(this, this.listener);
        startLoadingProgress(getString(C0258R.string.wait_progress));
        ScanParameter scanParameter = new ScanParameter();
        scanParameter.set(ScanParameter.KEY_CAMERA_INDEX, 1);
        try {
            this.scanService.startScan(scanParameter, new C06741());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void serviceConnected(Object objService, ServiceConnection connection) {
        if (objService instanceof IScanService) {
            this.scanService = (IScanService) objService;
            this.scanConn = connection;
        }
    }

    public void onDestroy() {
        if (this.scanService != null) {
            unbindService(this.scanConn);
            this.scanService = null;
            this.scanConn = null;
        }
        super.onDestroy();
    }
}
