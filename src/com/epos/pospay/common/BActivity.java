package com.epos.pospay.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import com.epos.pospay.view.CustomDialog;
import com.epos.pospay.view.CustomDialog.DialogType;
import com.umeng.analytics.MobclickAgent;

public abstract class BActivity extends FragmentActivity {
    private CustomDialog mLoadingDialog;

    protected abstract int getContentViewResId();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
    }

    protected void startLoadingProgress(String hint) {
        this.mLoadingDialog = new CustomDialog(this, DialogType.Loading);
        this.mLoadingDialog.setLoadingText(hint);
        this.mLoadingDialog.show();
    }

    protected void stopLoadingProgress() {
        if (this.mLoadingDialog != null && this.mLoadingDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
            this.mLoadingDialog = null;
        }
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
