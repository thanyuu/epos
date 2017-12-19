package com.epos.pospay.capture;

import android.content.Context;
import android.util.Log;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.epos.pospay.common.HttpRequestManager;
import com.epos.pospay.common.MainApplication;
import com.epos.pospay.common.VolleyErrorHelper;
import com.epos.pospay.model.CheckSuccessBean.OrderInfoBean;
import com.epos.pospay.model.ErrorResult;
import com.google.gson.Gson;
import java.io.Serializable;

public class ZxingProtocol {
    private static final String TAG = "===============";

    public interface OnResponseListener {
        void onErrorResponse(String str);

        void onResponse(Root root);
    }

    public class ResultBean implements Serializable {

        public class Root implements Serializable {
            private String message;
            private String messageCode;
            private OrderInfoBean orderInfo;
            private String orderNum;
            private boolean success;

            public OrderInfoBean getOrderInfo() {
                return this.orderInfo;
            }

            public Root setOrderInfo(OrderInfoBean orderInfo) {
                this.orderInfo = orderInfo;
                return this;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }

            public boolean getSuccess() {
                return this.success;
            }

            public void setMessageCode(String messageCode) {
                this.messageCode = messageCode;
            }

            public String getMessageCode() {
                return this.messageCode;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getMessage() {
                return this.message;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getOrderNum() {
                return this.orderNum;
            }
        }
    }

    public static void getResult(final Context cx, String result, String priceNz, final OnResponseListener listener) {
        Log.e(TAG, "扫描结果：" + result);
        HttpRequestManager.getInstance().checkIn(new Listener<String>() {
            public void onResponse(String response) {
                Log.e(ZxingProtocol.TAG, "onResponse: " + response);
                try {
                    Root bean = ZxingProtocol.getBean(response);
                    if (listener != null) {
                        listener.onResponse(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onErrorResponse("支付失败");
                    }
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                if (listener != null) {
                    listener.onErrorResponse(((ErrorResult) new Gson().fromJson(VolleyErrorHelper.getMessage(error, cx), ErrorResult.class)).getError().getDescription());
                }
            }
        }, "1", priceNz, result, cx, MainApplication.getInstance().getRemark(), MainApplication.getInstance().getCustomer_poundageFee(), MainApplication.getInstance().getTotal_fee()).commit();
    }

    private static Root getBean(String result) {
        return (Root) new Gson().fromJson(result, Root.class);
    }
}
