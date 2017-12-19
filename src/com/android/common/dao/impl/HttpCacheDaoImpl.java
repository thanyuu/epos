package com.android.common.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import com.android.common.constant.DbConstants;
import com.android.common.dao.HttpCacheDao;
import com.android.common.entity.HttpResponse;
import com.android.common.utils.SqliteUtils;
import com.android.common.utils.StringUtils;
import com.android.common.utils.TimeUtils;
import java.util.HashMap;
import java.util.Map;

public class HttpCacheDaoImpl implements HttpCacheDao {
    private SqliteUtils sqliteUtils;

    public HttpCacheDaoImpl(SqliteUtils sqliteUtils) {
        this.sqliteUtils = sqliteUtils;
    }

    public long insertHttpResponse(HttpResponse httpResponse) {
        ContentValues contentValues = httpResponseToCV(httpResponse);
        if (contentValues == null) {
            return -1;
        }
        long replace;
        synchronized (HttpCacheDaoImpl.class) {
            replace = this.sqliteUtils.getWDb().replace(DbConstants.HTTP_CACHE_TABLE_TABLE_NAME, null, contentValues);
        }
        return replace;
    }

    public HttpResponse getHttpResponse(String url) {
        HttpResponse httpResponse = null;
        if (!StringUtils.isEmpty(url)) {
            StringBuilder appWhere = new StringBuilder();
            appWhere.append("url").append("=?");
            String[] appWhereArgs = new String[]{url};
            synchronized (HttpCacheDaoImpl.class) {
                Cursor cursor = this.sqliteUtils.getRDb().query(DbConstants.HTTP_CACHE_TABLE_TABLE_NAME, null, appWhere.toString(), appWhereArgs, null, null, null);
                if (cursor == null) {
                } else {
                    httpResponse = null;
                    if (cursor.moveToFirst()) {
                        httpResponse = cursorToHttpResponse(cursor, url);
                    }
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }
        }
        return httpResponse;
    }

    public Map<String, HttpResponse> getHttpResponsesByType(int type) {
        Map<String, HttpResponse> map = null;
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(DbConstants.HTTP_CACHE_TABLE_TYPE).append("=?");
        String[] whereClauseArgs = new String[]{Integer.toString(type)};
        synchronized (HttpCacheDaoImpl.class) {
            Cursor cursor = this.sqliteUtils.getRDb().query(DbConstants.HTTP_CACHE_TABLE_TABLE_NAME, null, whereClause.toString(), whereClauseArgs, null, null, null);
            if (cursor == null) {
            } else {
                map = new HashMap();
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        String url = cursor.getString(1);
                        if (!StringUtils.isEmpty(url)) {
                            HttpResponse httpResponse = cursorToHttpResponse(cursor, url);
                            if (httpResponse != null) {
                                map.put(url, httpResponse);
                            }
                        }
                        cursor.moveToNext();
                    }
                }
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
        return map;
    }

    public int deleteAllHttpResponse() {
        return this.sqliteUtils.getWDb().delete(DbConstants.HTTP_CACHE_TABLE_TYPE, null, null);
    }

    private HttpResponse cursorToHttpResponse(Cursor cursor, String url) {
        if (cursor == null) {
            return null;
        }
        if (url == null) {
            url = cursor.getString(1);
        }
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        HttpResponse httpResponse = new HttpResponse(url);
        httpResponse.setResponseBody(cursor.getString(2));
        httpResponse.setExpiredTime(cursor.getLong(3));
        httpResponse.setType(cursor.getInt(5));
        return httpResponse;
    }

    private static ContentValues httpResponseToCV(HttpResponse httpResponse) {
        if (httpResponse == null || StringUtils.isEmpty(httpResponse.getUrl())) {
            return null;
        }
        ContentValues values = new ContentValues();
        values.put("url", httpResponse.getUrl());
        values.put(DbConstants.HTTP_CACHE_TABLE_RESPONSE, httpResponse.getResponseBody());
        values.put("expires", Long.valueOf(httpResponse.getExpiredTime()));
        values.put(DbConstants.HTTP_CACHE_TABLE_CREATE_TIME, TimeUtils.getCurrentTimeInString());
        values.put(DbConstants.HTTP_CACHE_TABLE_TYPE, Integer.valueOf(httpResponse.getType()));
        return values;
    }
}
