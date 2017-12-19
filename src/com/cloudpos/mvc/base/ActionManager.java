package com.cloudpos.mvc.base;

import android.content.Context;
import com.android.common.utils.HttpUtils;
import com.cloudpos.mvc.common.Logger;
import com.cloudpos.mvc.common.MVCException;
import java.util.HashMap;
import java.util.Map;

public final class ActionManager {
    private static ActionManager actionManager = new ActionManager();
    private ActionScheduler actionScheduler = ActionScheduler.getInstance();
    private boolean isStart = false;
    protected Map<String, Object> mActionContainer = new HashMap();

    private static ActionManager getInstance() {
        if (!actionManager.isStart) {
            actionManager.start();
            actionManager.isStart = true;
        }
        return actionManager;
    }

    private void start() {
        this.actionScheduler.start();
    }

    public static void initActionContainer(ActionContainer actions) {
        actions.initActions();
        getInstance().mActionContainer.putAll(actions.getActions());
    }

    public static void doSubmit(String actionUrl, Map<String, Object> param, ActionCallback callback) {
        doSubmit(actionUrl, null, (Map) param, callback);
    }

    public static void doSubmit(Class<? extends AbstractAction> clazz, String methodName, Map<String, Object> param, ActionCallback callback) {
        doSubmit(clazz.getName() + HttpUtils.PATHS_SEPARATOR + methodName, param, callback);
    }

    public static void doSubmit(String actionUrl, Context context, Map<String, Object> param, ActionCallback callback) {
        getInstance().newActionContext(actionUrl, context, param, callback);
    }

    public static void doSubmit(Class<? extends AbstractAction> clazz, String methodName, Context context, Map<String, Object> param, ActionCallback callback) {
        doSubmit(clazz.getName() + HttpUtils.PATHS_SEPARATOR + methodName, context, (Map) param, callback);
    }

    public static <T> T doSubmitForResult(String actionUrl, Map<String, Object> param, ActionCallback callback) {
        return doSubmitForResult(actionUrl, null, (Map) param, callback);
    }

    public static <T> T doSubmitForResult(Class<? extends AbstractAction> clazz, String methodName, Map<String, Object> param, ActionCallback callback) {
        return doSubmitForResult(clazz.getName() + HttpUtils.PATHS_SEPARATOR + methodName, param, callback);
    }

    public static <T> T doSubmitForResult(String actionUrl, Context context, Map<String, Object> param, ActionCallback callback) {
        return getInstance().newActionContext(actionUrl, context, param, callback).getResult();
    }

    public static <T> T doSubmitForResult(Class<? extends AbstractAction> clazz, String methodName, Context context, Map<String, Object> param, ActionCallback callback) {
        return doSubmitForResult(clazz.getName() + HttpUtils.PATHS_SEPARATOR + methodName, context, (Map) param, callback);
    }

    private ActionContext newActionContext(String actionUrl, Context context, Map<String, Object> param, ActionCallback callback) {
        ActionContext acontext = new ActionContext();
        acontext.setActionUrl(actionUrl);
        acontext.setParam(param);
        acontext.setCallback(callback);
        if (acontext != null) {
            acontext.setContext(context);
        }
        setAction(actionUrl, acontext);
        this.actionScheduler.setActionContext(acontext);
        return acontext;
    }

    private void setAction(String actionUrl, ActionContext context) {
        String actionId = ActionContext.parseActionId(actionUrl);
        Object obj = this.mActionContainer.get(actionId);
        if (obj == null) {
            throw new MVCException("Not found actionId in ActionContainer. The actionId is [" + actionId + "].");
        } else if (Class.class.isInstance(obj)) {
            try {
                context.setAction((AbstractAction) ((Class) Class.class.cast(obj)).newInstance());
            } catch (Exception e) {
                Logger.error("build instance error:", e);
            }
        } else {
            context.setAction((AbstractAction) obj);
        }
    }
}
