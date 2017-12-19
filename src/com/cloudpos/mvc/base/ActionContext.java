package com.cloudpos.mvc.base;

import android.content.Context;
import com.android.common.utils.HttpUtils;
import com.cloudpos.mvc.common.Logger;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ActionContext implements Runnable {
    private AbstractAction action;
    private String actionUrl;
    private ActionCallback callback;
    private Context context;
    private boolean hasReturn = false;
    private String methodName;
    private Map<String, Object> param;
    private Object result = null;
    private Condition resultCondition = this.resultLock.newCondition();
    private ReentrantLock resultLock = new ReentrantLock();

    public void run() {
        if (this.action == null) {
            Logger.error("Not found action! Please initional ActionContain and register your Action Class");
            return;
        }
        if (this.callback == null) {
            Logger.warn("No call back");
        }
        try {
            this.resultLock.lock();
            this.action.setContext(this.context);
            this.action.doBefore(this.param, this.callback);
            invoke();
            this.action.doAfter(this.param, this.callback);
            this.hasReturn = true;
            this.resultCondition.signalAll();
            this.resultLock.unlock();
        } catch (Exception e) {
            String errorMsg = "Invoke method error: " + this.action.getClass().getName() + "#" + this.methodName;
            if (e.getCause() == null) {
                Logger.error(errorMsg, e);
            } else if (e.getCause() instanceof UnknownHostException) {
                Logger.error(errorMsg);
                Logger.error(getStackTraceString(e.getCause()));
            } else {
                Logger.error(errorMsg, e.getCause());
            }
            this.hasReturn = true;
            this.resultCondition.signalAll();
            this.resultLock.unlock();
        } catch (Throwable th) {
            this.hasReturn = true;
            this.resultCondition.signalAll();
            this.resultLock.unlock();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getResult() {
        /*
        r3 = this;
        r1 = r3.resultLock;
        r1.lock();
        r1 = r3.hasReturn;	 Catch:{ InterruptedException -> 0x0016 }
        if (r1 != 0) goto L_0x000e;
    L_0x0009:
        r1 = r3.resultCondition;	 Catch:{ InterruptedException -> 0x0016 }
        r1.await();	 Catch:{ InterruptedException -> 0x0016 }
    L_0x000e:
        r1 = r3.resultLock;
        r1.unlock();
    L_0x0013:
        r1 = r3.result;
        return r1;
    L_0x0016:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0020 }
        r1 = r3.resultLock;
        r1.unlock();
        goto L_0x0013;
    L_0x0020:
        r1 = move-exception;
        r2 = r3.resultLock;
        r2.unlock();
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cloudpos.mvc.base.ActionContext.getResult():java.lang.Object");
    }

    private void invoke() throws Exception {
        parseActionUrl();
        Class<?> callbackParam = ActionCallback.class;
        if (this.callback != null) {
            callbackParam = this.callback.getClass().getSuperclass();
        }
        this.result = new BeanHelper(this.action).getMethod(this.methodName, Map.class, callbackParam).invoke(this.action, new Object[]{this.param, this.callback});
    }

    public static String parseActionId(String actionUrl) {
        int index = actionUrl.indexOf(HttpUtils.PATHS_SEPARATOR);
        return index == -1 ? actionUrl : actionUrl.substring(0, index);
    }

    private void parseActionUrl() {
        int index = this.actionUrl.indexOf(HttpUtils.PATHS_SEPARATOR);
        if (index == -1) {
            this.methodName = "execute";
        } else {
            this.methodName = this.actionUrl.substring(index + 1);
        }
    }

    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        tr.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public void setCallback(ActionCallback callback) {
        this.callback = callback;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setAction(AbstractAction action) {
        this.action = action;
    }
}
