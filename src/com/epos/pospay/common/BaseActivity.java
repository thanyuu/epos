package com.epos.pospay.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

public abstract class BaseActivity extends BActivity {
    private BaseFragment fragment;

    protected abstract int getContent();

    protected abstract Class<? extends BaseFragment> getContentFragmentClass();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        try {
            this.fragment = (BaseFragment) getContentFragmentClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        transaction.replace(getContent(), this.fragment);
        transaction.commit();
    }

    public BaseFragment getFragment() {
        return this.fragment;
    }
}
