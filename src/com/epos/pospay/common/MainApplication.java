package com.epos.pospay.common;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.cloudpos.apidemo.manager.AppManager;

public class MainApplication extends AppManager {
    public static final String TAG = MainApplication.class.getSimpleName();
    private static MainApplication mInstance;
    private String customer_poundageFee;
    private RequestQueue mRequestQueue;
    private String orderNum;
    private String priceNz;
    private String remark;
    private String total_fee;
    private String trans_id;
    private String transactionNumUnion;

    public MainApplication() {
        mInstance = this;
    }

    public static MainApplication getInstance() {
        if (mInstance == null) {
            mInstance = new MainApplication();
        }
        return mInstance;
    }

    public void onCreate() {
        super.onCreate();
    }

    public <T> void addToRequestQueue(Request<T> req, Object tag) {
        if (tag == null) {
            tag = TAG;
        }
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.cancelAll(tag);
        }
    }

    public RequestQueue getRequestQueue() {
        if (this.mRequestQueue == null) {
            this.mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return this.mRequestQueue;
    }

    public String getPriceNz() {
        return this.priceNz;
    }

    public void setPriceNz(String priceNz) {
        this.priceNz = priceNz;
    }

    public String getTotal_fee() {
        return this.total_fee;
    }

    public void setTotal_fee(String total_fee) {
        Log.e(TAG, "setTotal_fee: " + total_fee);
        this.total_fee = total_fee;
    }

    public String getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTrans_id() {
        return this.trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public String getTransactionNumUnion() {
        return this.transactionNumUnion;
    }

    public void setTransactionNumUnion(String transactionNumUnion) {
        this.transactionNumUnion = transactionNumUnion;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCustomer_poundageFee() {
        return this.customer_poundageFee;
    }

    public void setCustomer_poundageFee(String customer_poundageFee) {
        this.customer_poundageFee = customer_poundageFee;
    }
}
