package com.epos.pospay.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.epos.pospay.view.CustomDialog;
import com.epos.pospay.view.CustomDialog.DialogType;

public abstract class BaseFragment extends Fragment {
    private CustomDialog mLoadingDialog;

    protected abstract int getContentViewResId();

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getContentViewResId(), null);
    }

    protected void startLoadingProgress(String hint) {
        this.mLoadingDialog = new CustomDialog(getActivity(), DialogType.Loading);
        this.mLoadingDialog.setLoadingText(hint);
        this.mLoadingDialog.show();
    }

    protected void stopLoadingProgress() {
        if (this.mLoadingDialog != null && this.mLoadingDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
            this.mLoadingDialog = null;
        }
    }
}
