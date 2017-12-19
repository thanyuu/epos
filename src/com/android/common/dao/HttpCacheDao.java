package com.android.common.dao;

import com.android.common.entity.HttpResponse;
import java.util.Map;

public interface HttpCacheDao {
    int deleteAllHttpResponse();

    HttpResponse getHttpResponse(String str);

    Map<String, HttpResponse> getHttpResponsesByType(int i);

    long insertHttpResponse(HttpResponse httpResponse);
}
