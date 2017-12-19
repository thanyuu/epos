package com.epos.pospay.common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.epos.pospay.model.Error;
import com.epos.pospay.model.ErrorResult;
import com.google.gson.Gson;

public class VolleyErrorHelper {
    private static final int INTERSERVERERROR = 10001;
    private static final int NONETWORK = 10003;
    private static final int PARSEERROR = 10005;
    private static final int TIMEOUT = 10002;
    private static final int UNKNOWNERROR = 10004;

    public static String getMessage(Object error, Context context) {
        Error err = new Error();
        Object result = new ErrorResult();
        if (error instanceof TimeoutError) {
            err.setCode(TIMEOUT);
            err.setDescription("网络不给力");
            result.setError(err);
            return new Gson().toJson(result);
        } else if (isServerProblem(error)) {
            return handleServerError(error, context);
        } else {
            if (isNetworkProblem(error)) {
                err.setCode(NONETWORK);
                err.setDescription("网络错误，请检查您的网络");
                result.setError(err);
                return new Gson().toJson(result);
            } else if (isParseProblem(error)) {
                err.setCode(PARSEERROR);
                err.setDescription("数据异常,请稍后重试");
                result.setError(err);
                return new Gson().toJson(result);
            } else {
                err.setCode(UNKNOWNERROR);
                err.setDescription("未知错误，请重新尝试");
                result.setError(err);
                return new Gson().toJson(result);
            }
        }
    }

    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
    }

    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    private static boolean isParseProblem(Object error) {
        return error instanceof ParseError;
    }

    private static String handleServerError(Object error, Context context) {
        NetworkResponse response = ((VolleyError) error).networkResponse;
        Error err = new Error();
        Object result = new ErrorResult();
        if (response != null) {
            if (response.statusCode >= 400 && response.statusCode < 500) {
                return "出错了，稍后重试";
            }
            if (response.statusCode >= 500) {
                err.setCode(INTERSERVERERROR);
                err.setDescription("服务器出错了,请稍后重试");
                result.setError(err);
                return new Gson().toJson(result);
            }
        }
        err.setCode(UNKNOWNERROR);
        err.setDescription("未知错误，请重新尝试");
        result.setError(err);
        return new Gson().toJson(result);
    }

    public static void showErrorMessage(Context context, VolleyError error) {
        Log.e("========", "showErrorMessage: " + error);
        try {
            Toast.makeText(context, ((ErrorResult) new Gson().fromJson(getMessage(error, context), ErrorResult.class)).getError().getDescription(), 0).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNetError(VolleyError error) {
        return (error instanceof TimeoutError) || isNetworkProblem(error) || isServerProblem(error) || isParseProblem(error);
    }
}
