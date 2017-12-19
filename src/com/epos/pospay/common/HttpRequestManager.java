package com.epos.pospay.common;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpRequestManager {
    public static final String PAY_CHANNEL = "wechatPay";
    public static final String URL = "baseUrl";
    public static String baseUrl = "https://www.wetopay.com";
    protected static HttpRequestManager sInstance;

    public static HttpRequestManager getInstance() {
        if (sInstance == null) {
            sInstance = new HttpRequestManager();
        }
        return sInstance;
    }

    public static String getURL() {
        if (TextUtils.isEmpty(SPUtil.get(MainApplication.getInstance(), URL, "").toString())) {
            return baseUrl;
        }
        return SPUtil.get(MainApplication.getInstance(), URL, "").toString();
    }

    public HttpRequest login(Listener<String> listener, ErrorListener errorListener, String loginName, String password) {
        String url = String.format("%s/userInfo/loginApp", new Object[]{getURL()});
        String nonce_str = UUID.randomUUID().toString();
        String signature = "";
        Map<String, Object> map = new HashMap();
        map.put("fee_type", "NZD");
        map.put("loginName", loginName);
        map.put("password", password);
        map.put("pos_sn", Build.SERIAL);
        map.put("nonce_str", nonce_str);
        return new HttpRequest(0, url + "?fee_type=NZD&loginName=" + loginName + "&password=" + password + "&pos_sn=" + Build.SERIAL + "&nonce_str=" + nonce_str + "&signature=" + CommonUtil.createSignature(map), null, listener, errorListener);
    }

    public HttpRequest checkIn(Listener<String> listener, ErrorListener errorListener, String productname, String priceNz, String u_code, Context context, String remark, String customer_poundageFee, String total_fee) {
        String url = String.format("%s/pay/micropayApp", new Object[]{getURL()});
        String signature = "";
        Map<String, Object> map = new HashMap();
        map.put("productname", productname);
        map.put("price_type", SPUtil.get(context, "feeType", "").toString());
        map.put("rate", SPUtil.get(context, "rate", ""));
        map.put("device_info", SPUtil.get(context, "weposId", ""));
        map.put("priceNz", priceNz);
        map.put("auth_code", SPUtil.get(context, "authCode", ""));
        map.put("u_code", u_code);
        map.put("remark", remark);
        map.put("total_fee", total_fee);
        map.put("customer_poundageFee", customer_poundageFee);
        url = url + "?productname=" + productname + "&price_type=" + SPUtil.get(context, "feeType", "").toString() + "&rate=" + SPUtil.get(context, "rate", "") + "&device_info=" + SPUtil.get(context, "weposId", "") + "&priceNz=" + priceNz + "&auth_code=" + SPUtil.get(context, "authCode", "") + "&u_code=" + u_code + "&signature=" + CommonUtil.createSignature(map) + "&remark=" + remark + "&customer_poundageFee=" + customer_poundageFee + "&total_fee=" + total_fee;
        Log.e("=================", "checkIn: " + url);
        return new HttpRequest(0, url, null, listener, errorListener);
    }

    public HttpRequest checkSuccess(Listener<String> listener, ErrorListener errorListener, String orderNum, String id, String transactionNumUnion) {
        String url = String.format("%s/pay/getOrderInfoApp", new Object[]{getURL()});
        String signature = "";
        Map<String, Object> map = new HashMap();
        map.put("orderNum", orderNum);
        map.put("tid", id);
        map.put("transactionNumUnion", transactionNumUnion);
        url = url + "?orderNum=" + orderNum + "&tid=" + id + "&transactionNumUnion=" + transactionNumUnion + "&signature=" + CommonUtil.createSignature(map);
        Log.e("=================", "checkSuccess: " + url);
        return new HttpRequest(0, url, null, listener, errorListener);
    }

    public HttpRequest refundOrder(Listener<String> listener, ErrorListener errorListener, Context cx, String transactionId, String refundInstructions, String refundPassword, String refund_amount) {
        String url = String.format("%s/pay/refundOrderApp", new Object[]{getURL()});
        String signature = "";
        Map<String, Object> map = new HashMap();
        map.put("transactionId", transactionId);
        map.put("device_info", SPUtil.get(cx, "weposId", ""));
        map.put("auth_code", SPUtil.get(cx, "authCode", ""));
        map.put("refundInstructions", refundInstructions);
        map.put("refundPassword", refundPassword);
        map.put("refund_amount", refund_amount);
        url = url + "?transactionId=" + transactionId + "&device_info=" + SPUtil.get(cx, "weposId", "") + "&auth_code=" + SPUtil.get(cx, "authCode", "") + "&refundInstructions=" + refundInstructions + "&refundPassword=" + refundPassword + "&signature=" + CommonUtil.createSignature(map) + "&refund_amount=" + refund_amount;
        Log.e("==========", "refundOrder: " + url);
        return new HttpRequest(0, url, null, listener, errorListener);
    }

    public HttpRequest orderList(Listener<String> listener, ErrorListener errorListener, Context cx, int max, int offset, String transactionState, String startDate, String endDate) {
        String url = String.format("%s/pay/getOrderListApp", new Object[]{getURL()});
        String signature = "";
        Map<String, Object> map = new HashMap();
        map.put("device_info", SPUtil.get(cx, "weposId", ""));
        map.put("transactionType", PAY_CHANNEL);
        map.put("max", Integer.valueOf(max));
        map.put("offset", Integer.valueOf(offset));
        map.put("transactionState", transactionState);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        url = url + "?device_info=" + SPUtil.get(cx, "weposId", "") + "&transactionType=" + PAY_CHANNEL + "&max=" + max + "&offset=" + offset + "&transactionState=" + transactionState + "&startDate=" + startDate + "&endDate=" + endDate + "&signature=" + CommonUtil.createSignature(map);
        Log.e("===============", "orderList: " + url);
        return new HttpRequest(0, url, null, listener, errorListener);
    }

    public HttpRequest getRate(Listener<String> listener, ErrorListener errorListener, String merchantId) {
        String url = String.format("%s/pay/getMerchantRate?feeType=" + SPUtil.get(MainApplication.getInstance(), "feeType", "").toString(), new Object[]{getURL()});
        String signature = "";
        Map<String, Object> map = new HashMap();
        map.put("merchantId", merchantId);
        return new HttpRequest(0, url + "&merchantId=" + merchantId + "&signature=" + CommonUtil.createSignature(map), null, listener, errorListener);
    }

    public HttpRequest getBills(Listener<String> listener, ErrorListener errorListener, Context context) {
        String url = String.format("%s/pay/totalStatistics", new Object[]{getURL()});
        String signature = "";
        Map<String, Object> map = new HashMap();
        map.put("device_info", SPUtil.get(context, "weposId", ""));
        map.put("sign", MD5Util.MD5(SPUtil.get(context, "weposId", "") + "-token_wetopay_app"));
        map.put("dateType", "NZ");
        return new HttpRequest(0, url + "?device_info=" + SPUtil.get(context, "weposId", "") + "&sign=" + MD5Util.MD5(SPUtil.get(context, "weposId", "") + "-token_wetopay_app") + "&dateType=NZ&signature=" + CommonUtil.createSignature(map), null, listener, errorListener);
    }

    public HttpRequest getBills1(Listener<String> listener, ErrorListener errorListener, Context context, String startDate, String endDate) {
        String url = String.format("%s/pay/totalStatistics", new Object[]{getURL()});
        String startTime = null;
        String endTime = null;
        try {
            startTime = URLEncoder.encode(startDate, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            endTime = URLEncoder.encode(endDate, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        String signature = "";
        Map<String, Object> map = new HashMap();
        map.put("device_info", SPUtil.get(context, "weposId", ""));
        map.put("sign", MD5Util.MD5(SPUtil.get(context, "weposId", "") + "-token_wetopay_app"));
        map.put("dateType", "NZ");
        map.put("startDate", startTime);
        map.put("endDate", endTime);
        url = url + "?device_info=" + SPUtil.get(context, "weposId", "") + "&sign=" + MD5Util.MD5(SPUtil.get(context, "weposId", "") + "-token_wetopay_app") + "&dateType=NZ&startDate=" + startTime + "&endDate=" + endTime + "&signature=" + CommonUtil.createSignature(map);
        Log.e("======", "getBills1: " + url);
        return new HttpRequest(0, url, null, listener, errorListener);
    }
}
