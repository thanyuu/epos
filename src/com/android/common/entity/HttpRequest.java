package com.android.common.entity;

import com.android.common.utils.HttpUtils;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private int connectTimeout = -1;
    private Map<String, String> parasMap;
    private int readTimeout = -1;
    private Map<String, String> requestProperties = new HashMap();
    private String url;

    public HttpRequest(String url) {
        this.url = url;
    }

    public HttpRequest(String url, Map<String, String> parasMap) {
        this.url = url;
        this.parasMap = parasMap;
    }

    public String getUrl() {
        return this.url;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(int timeoutMillis) {
        if (timeoutMillis < 0) {
            throw new IllegalArgumentException("timeout can not be negative");
        }
        this.connectTimeout = timeoutMillis;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public void setReadTimeout(int timeoutMillis) {
        if (timeoutMillis < 0) {
            throw new IllegalArgumentException("timeout can not be negative");
        }
        this.readTimeout = timeoutMillis;
    }

    public Map<String, String> getParasMap() {
        return this.parasMap;
    }

    public void setParasMap(Map<String, String> parasMap) {
        this.parasMap = parasMap;
    }

    public String getParas() {
        return HttpUtils.joinParasWithEncodedValue(this.parasMap);
    }

    public void setRequestProperty(String field, String newValue) {
        this.requestProperties.put(field, newValue);
    }

    public String getRequestProperty(String field) {
        return (String) this.requestProperties.get(field);
    }

    public void setUserAgent(String value) {
        this.requestProperties.put("User-Agent", value);
    }

    public Map<String, String> getRequestProperties() {
        return this.requestProperties;
    }

    public void setRequestProperties(Map<String, String> requestProperties) {
        this.requestProperties = requestProperties;
    }
}
