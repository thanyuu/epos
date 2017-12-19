package com.android.common.entity;

import com.android.common.constant.HttpConstants;
import com.android.common.utils.HttpUtils;
import com.android.common.utils.StringUtils;
import com.android.common.utils.TimeUtils;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private long expiredTime;
    private boolean isInCache;
    private String responseBody;
    private int responseCode;
    private Map<String, Object> responseHeaders;
    private int type;
    private String url;

    public HttpResponse(String url) {
        this.responseCode = -1;
        this.url = url;
        this.type = 0;
        this.isInCache = false;
        this.responseHeaders = new HashMap();
    }

    public HttpResponse() {
        this.responseCode = -1;
        this.responseHeaders = new HashMap();
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    private Map<String, Object> getResponseHeaders() {
        return this.responseHeaders;
    }

    public void setResponseHeaders(Map<String, Object> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        if (type < 0) {
            throw new IllegalArgumentException("The type of HttpResponse cannot be smaller than 0.");
        }
        this.type = type;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public long getExpiredTime() {
        return this.expiredTime;
    }

    public boolean isExpired() {
        return TimeUtils.getCurrentTimeInLong() > this.expiredTime;
    }

    public boolean isInCache() {
        return this.isInCache;
    }

    public HttpResponse setInCache(boolean isInCache) {
        this.isInCache = isInCache;
        return this;
    }

    public String getExpiresHeader() {
        try {
            return this.responseHeaders == null ? null : (String) this.responseHeaders.get("expires");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getCacheControlMaxAge() {
        try {
            String cacheControl = (String) this.responseHeaders.get(HttpConstants.CACHE_CONTROL);
            if (StringUtils.isEmpty(cacheControl)) {
                return -1;
            }
            int start = cacheControl.indexOf("max-age=");
            if (start == -1) {
                return -1;
            }
            String maxAge;
            int end = cacheControl.indexOf(",", start);
            if (end != -1) {
                maxAge = cacheControl.substring("max-age=".length() + start, end);
            } else {
                maxAge = cacheControl.substring("max-age=".length() + start);
            }
            return Integer.parseInt(maxAge);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long getExpiresInMillis() {
        int maxAge = getCacheControlMaxAge();
        if (maxAge != -1) {
            return System.currentTimeMillis() + ((long) (maxAge * 1000));
        }
        if (StringUtils.isEmpty(getExpiresHeader())) {
            return -1;
        }
        return HttpUtils.parseGmtTime(getExpiresHeader());
    }

    public void setResponseHeader(String field, String newValue) {
        if (this.responseHeaders != null) {
            this.responseHeaders.put(field, newValue);
        }
    }

    private Object getResponseHeader(String field) {
        return this.responseHeaders == null ? null : this.responseHeaders.get(field);
    }
}
