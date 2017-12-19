package com.epos.pospay.common;

import com.android.common.utils.HttpUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest extends Request<String> {
    protected static final String CHARSET = "utf-8";
    public static final String HEADER_KEY_TOKEN = "Authorization";
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", new Object[]{"utf-8"});
    private static final Map<String, String> sHeaders = new HashMap();
    private Listener<String> mListener;
    private StringBuilder mQueryString;
    private final String mRequestBody;
    private String mTag;

    public static void setCommonHeaderEntry(String name, String value) {
        sHeaders.put(name, value);
    }

    public HttpRequest(int method, String url, String requestBody, Listener<String> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(9000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.mListener = listener;
        this.mRequestBody = requestBody;
    }

    public String getUrl() {
        if (this.mQueryString == null) {
            return super.getUrl();
        }
        return super.getUrl() + HttpUtils.URL_AND_PARA_SEPARATOR + this.mQueryString.toString();
    }

    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(new String(response.data, HttpHeaderParser.parseCharset(response.headers)), HttpHeaderParser.parseCacheHeaders(response));
        } catch (Throwable e) {
            return Response.error(new ParseError(e));
        }
    }

    protected void deliverResponse(String response) {
        if (this.mListener != null) {
            this.mListener.onResponse(response);
        }
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return sHeaders;
    }

    public byte[] getBody() throws AuthFailureError {
        byte[] bArr = null;
        try {
            if (this.mRequestBody != null) {
                bArr = this.mRequestBody.getBytes("utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", this.mRequestBody, "utf-8");
        }
        return bArr;
    }

    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    public HttpRequest commit() {
        MainApplication.getInstance().addToRequestQueue(this, this.mTag);
        return this;
    }

    public void commit(Object tag) {
        if (tag != null) {
            MainApplication.getInstance().addToRequestQueue(this, tag);
        } else {
            MainApplication.getInstance().addToRequestQueue(this, this.mTag);
        }
    }
}
