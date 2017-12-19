package com.android.common.utils.special;

import android.content.Context;
import android.util.Log;

public class Eth0Controller {
    public static final String APP_TAG = "ETH0";
    public static final String ETHERNET_SERVICE = "ethernet";
    private Context host;

    public Eth0Controller(Context context) {
        this.host = context;
    }

    public boolean setEth0Enable(boolean isOpen) {
        Object mEthManager = this.host.getSystemService(ETHERNET_SERVICE);
        Log.i(APP_TAG, mEthManager.toString());
        Log.i(APP_TAG, mEthManager.getClass().toString());
        try {
            Log.i(APP_TAG, "result = " + mEthManager.getClass().getMethod("getState", new Class[0]).invoke(mEthManager, new Object[0]));
            mEthManager.getClass().getMethod("setEnabled", new Class[]{Boolean.TYPE}).invoke(mEthManager, new Object[]{Boolean.valueOf(isOpen)});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
