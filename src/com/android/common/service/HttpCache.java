package com.android.common.service;

import android.content.Context;
import android.os.AsyncTask;
import com.android.common.constant.HttpConstants;
import com.android.common.dao.HttpCacheDao;
import com.android.common.dao.impl.HttpCacheDaoImpl;
import com.android.common.entity.HttpRequest;
import com.android.common.entity.HttpResponse;
import com.android.common.utils.ArrayUtils;
import com.android.common.utils.HttpUtils;
import com.android.common.utils.SqliteUtils;
import com.android.common.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HttpCache {
    private Map<String, HttpResponse> cache;
    private Context context;
    private HttpCacheDao httpCacheDao;
    private int type;

    public static abstract class HttpCacheListener {
        protected void onPreGet() {
        }

        protected void onPostGet(HttpResponse httpResponse, boolean isInCache) {
        }
    }

    private class HttpCacheRequestAsyncTask extends AsyncTask<HttpRequest, Void, HttpResponse> {
        private HttpCacheListener listener;

        public HttpCacheRequestAsyncTask(HttpCacheListener listener) {
            this.listener = listener;
        }

        protected HttpResponse doInBackground(HttpRequest... httpRequest) {
            if (ArrayUtils.isEmpty(httpRequest)) {
                return null;
            }
            return HttpCache.this.httpGet(httpRequest[0]);
        }

        protected void onPreExecute() {
            if (this.listener != null) {
                this.listener.onPreGet();
            }
        }

        protected void onPostExecute(HttpResponse httpResponse) {
            if (this.listener != null) {
                this.listener.onPostGet(httpResponse, httpResponse == null ? false : httpResponse.isInCache());
            }
        }
    }

    private class HttpCacheStringAsyncTask extends AsyncTask<String, Void, HttpResponse> {
        private HttpCacheListener listener;

        public HttpCacheStringAsyncTask(HttpCacheListener listener) {
            this.listener = listener;
        }

        protected HttpResponse doInBackground(String... url) {
            if (ArrayUtils.isEmpty(url)) {
                return null;
            }
            return HttpCache.this.httpGet(url[0]);
        }

        protected void onPreExecute() {
            if (this.listener != null) {
                this.listener.onPreGet();
            }
        }

        protected void onPostExecute(HttpResponse httpResponse) {
            if (this.listener != null) {
                this.listener.onPostGet(httpResponse, httpResponse == null ? false : httpResponse.isInCache());
            }
        }
    }

    public HttpCache(Context context) {
        this.type = -1;
        if (context == null) {
            throw new IllegalArgumentException("The context can not be null.");
        }
        this.context = context;
        this.cache = new ConcurrentHashMap();
        this.httpCacheDao = new HttpCacheDaoImpl(SqliteUtils.getInstance(context));
    }

    private HttpCache(Context context, int type) {
        this(context);
        this.type = type;
        initData(type);
    }

    private void initData(int type) {
        this.cache = this.httpCacheDao.getHttpResponsesByType(type);
        if (this.cache == null) {
            this.cache = new HashMap();
        }
    }

    public HttpResponse httpGet(HttpRequest request) {
        if (request != null) {
            String url = request.getUrl();
            if (!StringUtils.isEmpty(url)) {
                HttpResponse cacheResponse = null;
                boolean isNoCache = false;
                boolean isNoStore = false;
                String requestCacheControl = request.getRequestProperty(HttpConstants.CACHE_CONTROL);
                if (!StringUtils.isEmpty(requestCacheControl)) {
                    String[] requestCacheControls = requestCacheControl.split(",");
                    if (!ArrayUtils.isEmpty(requestCacheControls)) {
                        List<String> requestCacheControlList = new ArrayList();
                        for (String s : requestCacheControls) {
                            if (s != null) {
                                requestCacheControlList.add(s.trim());
                            }
                        }
                        if (requestCacheControlList.contains("no-cache")) {
                            isNoCache = true;
                        }
                        if (requestCacheControlList.contains("no-store")) {
                            isNoStore = true;
                        }
                    }
                }
                if (!isNoCache) {
                    cacheResponse = getFromCache(url);
                }
                if (cacheResponse != null) {
                    return cacheResponse;
                }
                if (isNoStore) {
                    return HttpUtils.httpGet(url);
                }
                return putIntoCache(HttpUtils.httpGet(url));
            }
        }
        return null;
    }

    public void httpGet(String url, HttpCacheListener listener) {
        new HttpCacheStringAsyncTask(listener).execute(new String[]{url});
    }

    public void httpGet(HttpRequest request, HttpCacheListener listener) {
        new HttpCacheRequestAsyncTask(listener).execute(new HttpRequest[]{request});
    }

    public HttpResponse httpGet(String url) {
        return httpGet(new HttpRequest(url));
    }

    public String httpGetString(String url) {
        HttpResponse cacheResponse = httpGet(new HttpRequest(url));
        return cacheResponse == null ? null : cacheResponse.getResponseBody();
    }

    public HttpResponse httpGetString(HttpRequest httpRequest) {
        return httpGet(httpRequest);
    }

    public boolean containsKey(String url) {
        return getFromCache(url) != null;
    }

    protected boolean isExpired(String url) {
        return getFromCache(url) == null;
    }

    public void clear() {
        this.cache.clear();
        this.httpCacheDao.deleteAllHttpResponse();
    }

    private int getType() {
        return this.type;
    }

    private HttpResponse putIntoCache(HttpResponse httpResponse) {
        if (httpResponse != null) {
            String url = httpResponse.getUrl();
            if (url != null) {
                if (this.type != -1 && this.type == httpResponse.getType()) {
                    this.cache.put(url, httpResponse);
                }
                return this.httpCacheDao.insertHttpResponse(httpResponse) == -1 ? null : httpResponse;
            }
        }
        return null;
    }

    private HttpResponse getFromCache(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        HttpResponse cacheResponse = (HttpResponse) this.cache.get(url);
        if (cacheResponse == null) {
            cacheResponse = this.httpCacheDao.getHttpResponse(url);
        }
        if (cacheResponse == null || cacheResponse.isExpired()) {
            return null;
        }
        return cacheResponse.setInCache(true);
    }
}
