package com.android.common.utils;

import android.os.AsyncTask;
import com.android.common.constant.HttpConstants;
import com.android.common.entity.HttpRequest;
import com.android.common.entity.HttpResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtils {
    public static final String EQUAL_SIGN = "=";
    private static final SimpleDateFormat GMT_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
    public static final String PARAMETERS_SEPARATOR = "&";
    public static final String PATHS_SEPARATOR = "/";
    public static final String URL_AND_PARA_SEPARATOR = "?";

    public static abstract class HttpListener {
        protected void onPreGet() {
        }

        protected void onPostGet(HttpResponse httpResponse) {
        }
    }

    private static class HttpRequestAsyncTask extends AsyncTask<HttpRequest, Void, HttpResponse> {
        private HttpListener listener;

        public HttpRequestAsyncTask(HttpListener listener) {
            this.listener = listener;
        }

        protected HttpResponse doInBackground(HttpRequest... httpRequest) {
            if (ArrayUtils.isEmpty(httpRequest)) {
                return null;
            }
            return HttpUtils.httpGet(httpRequest[0]);
        }

        protected void onPreExecute() {
            if (this.listener != null) {
                this.listener.onPreGet();
            }
        }

        protected void onPostExecute(HttpResponse httpResponse) {
            if (this.listener != null) {
                this.listener.onPostGet(httpResponse);
            }
        }
    }

    private static class HttpStringAsyncTask extends AsyncTask<String, Void, HttpResponse> {
        private HttpListener listener;

        public HttpStringAsyncTask(HttpListener listener) {
            this.listener = listener;
        }

        protected HttpResponse doInBackground(String... url) {
            if (ArrayUtils.isEmpty(url)) {
                return null;
            }
            return HttpUtils.httpGet(url[0]);
        }

        protected void onPreExecute() {
            if (this.listener != null) {
                this.listener.onPreGet();
            }
        }

        protected void onPostExecute(HttpResponse httpResponse) {
            if (this.listener != null) {
                this.listener.onPostGet(httpResponse);
            }
        }
    }

    public static HttpResponse httpGet(HttpRequest request) {
        IOException e;
        MalformedURLException e1;
        Throwable th;
        if (request == null) {
            return null;
        }
        BufferedReader input = null;
        HttpURLConnection con = null;
        try {
            URL url = new URL(request.getUrl());
            try {
                HttpResponse response = new HttpResponse(request.getUrl());
                con = (HttpURLConnection) url.openConnection();
                setURLConnection(request, con);
                BufferedReader input2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String s = input2.readLine();
                        if (s == null) {
                            break;
                        }
                        sb.append(s).append(ShellUtils.COMMAND_LINE_END);
                    }
                    response.setResponseBody(sb.toString());
                    setHttpResponse(con, response);
                    if (input2 != null) {
                        try {
                            input2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (con == null) {
                        return response;
                    }
                    con.disconnect();
                    return response;
                } catch (IOException e3) {
                    e2 = e3;
                    input = input2;
                } catch (MalformedURLException e4) {
                    e1 = e4;
                    input = input2;
                } catch (Throwable th2) {
                    th = th2;
                    input = input2;
                }
            } catch (IOException e5) {
                e2 = e5;
                e2.printStackTrace();
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return null;
            }
        } catch (MalformedURLException e6) {
            e1 = e6;
            try {
                e1.printStackTrace();
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                throw th;
            }
        }
    }

    public static HttpResponse httpGet(String httpUrl) {
        return httpGet(new HttpRequest(httpUrl));
    }

    public static String httpGetString(HttpRequest request) {
        HttpResponse response = httpGet(request);
        return response == null ? null : response.getResponseBody();
    }

    public static String httpGetString(String httpUrl) {
        HttpResponse response = httpGet(new HttpRequest(httpUrl));
        return response == null ? null : response.getResponseBody();
    }

    public static void httpGet(String url, HttpListener listener) {
        new HttpStringAsyncTask(listener).execute(new String[]{url});
    }

    public static void httpGet(HttpRequest request, HttpListener listener) {
        new HttpRequestAsyncTask(listener).execute(new HttpRequest[]{request});
    }

    public static HttpResponse httpPost(HttpRequest request) {
        IOException e;
        MalformedURLException e1;
        Throwable th;
        if (request == null) {
            return null;
        }
        BufferedReader input = null;
        HttpURLConnection con = null;
        try {
            URL url = new URL(request.getUrl());
            try {
                HttpResponse response = new HttpResponse(request.getUrl());
                con = (HttpURLConnection) url.openConnection();
                setURLConnection(request, con);
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                String paras = request.getParas();
                if (!StringUtils.isEmpty(paras)) {
                    con.getOutputStream().write(paras.getBytes());
                }
                BufferedReader input2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String s = input2.readLine();
                        if (s == null) {
                            break;
                        }
                        sb.append(s).append(ShellUtils.COMMAND_LINE_END);
                    }
                    response.setResponseBody(sb.toString());
                    setHttpResponse(con, response);
                    if (input2 != null) {
                        try {
                            input2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (con == null) {
                        return response;
                    }
                    con.disconnect();
                    return response;
                } catch (IOException e3) {
                    e2 = e3;
                    input = input2;
                } catch (MalformedURLException e4) {
                    e1 = e4;
                    input = input2;
                } catch (Throwable th2) {
                    th = th2;
                    input = input2;
                }
            } catch (IOException e5) {
                e2 = e5;
                e2.printStackTrace();
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return null;
            }
        } catch (MalformedURLException e6) {
            e1 = e6;
            try {
                e1.printStackTrace();
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
                throw th;
            }
        }
    }

    public static HttpResponse httpPost(String httpUrl) {
        return httpPost(new HttpRequest(httpUrl));
    }

    public static String httpPostString(String httpUrl) {
        HttpResponse response = httpPost(new HttpRequest(httpUrl));
        return response == null ? null : response.getResponseBody();
    }

    public static String httpPostString(String httpUrl, Map<String, String> parasMap) {
        HttpResponse response = httpPost(new HttpRequest(httpUrl, parasMap));
        return response == null ? null : response.getResponseBody();
    }

    public static String getUrlWithParas(String url, Map<String, String> parasMap) {
        if (StringUtils.isEmpty(url)) {
            url = "";
        }
        StringBuilder urlWithParas = new StringBuilder(url);
        String paras = joinParas(parasMap);
        if (!StringUtils.isEmpty(paras)) {
            urlWithParas.append(URL_AND_PARA_SEPARATOR).append(paras);
        }
        return urlWithParas.toString();
    }

    public static String getUrlWithValueEncodeParas(String url, Map<String, String> parasMap) {
        if (StringUtils.isEmpty(url)) {
            url = "";
        }
        StringBuilder urlWithParas = new StringBuilder(url);
        String paras = joinParasWithEncodedValue(parasMap);
        if (!StringUtils.isEmpty(paras)) {
            urlWithParas.append(URL_AND_PARA_SEPARATOR).append(paras);
        }
        return urlWithParas.toString();
    }

    public static String joinParas(Map<String, String> parasMap) {
        if (parasMap == null || parasMap.size() == 0) {
            return null;
        }
        StringBuilder paras = new StringBuilder();
        Iterator<Entry<String, String>> ite = parasMap.entrySet().iterator();
        while (ite.hasNext()) {
            Entry<String, String> entry = (Entry) ite.next();
            paras.append((String) entry.getKey()).append(EQUAL_SIGN).append((String) entry.getValue());
            if (ite.hasNext()) {
                paras.append(PARAMETERS_SEPARATOR);
            }
        }
        return paras.toString();
    }

    public static String joinParasWithEncodedValue(Map<String, String> parasMap) {
        StringBuilder paras = new StringBuilder("");
        if (parasMap != null && parasMap.size() > 0) {
            Iterator<Entry<String, String>> ite = parasMap.entrySet().iterator();
            while (ite.hasNext()) {
                try {
                    Entry<String, String> entry = (Entry) ite.next();
                    paras.append((String) entry.getKey()).append(EQUAL_SIGN).append(StringUtils.utf8Encode((String) entry.getValue()));
                    if (ite.hasNext()) {
                        paras.append(PARAMETERS_SEPARATOR);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return paras.toString();
    }

    public static String appendParaToUrl(String url, String paraKey, String paraValue) {
        if (StringUtils.isEmpty(url)) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        if (url.contains(URL_AND_PARA_SEPARATOR)) {
            sb.append(PARAMETERS_SEPARATOR);
        } else {
            sb.append(URL_AND_PARA_SEPARATOR);
        }
        return sb.append(paraKey).append(EQUAL_SIGN).append(paraValue).toString();
    }

    public static long parseGmtTime(String gmtTime) {
        try {
            return GMT_FORMAT.parse(gmtTime).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static void setURLConnection(HttpRequest request, HttpURLConnection urlConnection) {
        if (request != null && urlConnection != null) {
            setURLConnection(request.getRequestProperties(), urlConnection);
            if (request.getConnectTimeout() >= 0) {
                urlConnection.setConnectTimeout(request.getConnectTimeout());
            }
            if (request.getReadTimeout() >= 0) {
                urlConnection.setReadTimeout(request.getReadTimeout());
            }
        }
    }

    public static void setURLConnection(Map<String, String> requestProperties, HttpURLConnection urlConnection) {
        if (!MapUtils.isEmpty(requestProperties) && urlConnection != null) {
            for (Entry<String, String> entry : requestProperties.entrySet()) {
                if (!StringUtils.isEmpty((String) entry.getKey())) {
                    urlConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            }
        }
    }

    private static void setHttpResponse(HttpURLConnection urlConnection, HttpResponse response) {
        if (response != null && urlConnection != null) {
            try {
                response.setResponseCode(urlConnection.getResponseCode());
            } catch (IOException e) {
                response.setResponseCode(-1);
            }
            response.setResponseHeader("expires", urlConnection.getHeaderField("Expires"));
            response.setResponseHeader(HttpConstants.CACHE_CONTROL, urlConnection.getHeaderField("Cache-Control"));
            response.setExpiredTime(response.getExpiresInMillis());
        }
    }
}
