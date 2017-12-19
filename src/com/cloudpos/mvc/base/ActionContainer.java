package com.cloudpos.mvc.base;

import com.cloudpos.mvc.common.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ActionContainer {
    protected Map<String, Object> actions = new HashMap();

    public abstract void initActions();

    public Map<String, Object> getActions() {
        return this.actions;
    }

    private Object searchInstance(Class<? extends AbstractAction> clazz) throws Exception {
        for (Entry<String, Object> entry : this.actions.entrySet()) {
            Object value = entry.getValue();
            if (value != null && value.getClass().equals(clazz)) {
                return value;
            }
        }
        return clazz.newInstance();
    }

    protected boolean addAction(String actionId, Class<? extends AbstractAction> clazz, boolean singleton) {
        if (singleton) {
            try {
                this.actions.put(actionId, searchInstance(clazz));
            } catch (Exception e) {
                Logger.error("build singleton instance occur an error:", e);
                return false;
            }
        }
        this.actions.put(actionId, clazz);
        return true;
    }

    protected boolean addAction(String actionId, Class<? extends AbstractAction> clazz) {
        return addAction(actionId, clazz, false);
    }

    protected boolean addAction(Class<? extends AbstractAction> clazz, boolean singleton) {
        return addAction(clazz.getName(), clazz, singleton);
    }

    protected boolean addAction(Class<? extends AbstractAction> clazz) {
        return addAction((Class) clazz, false);
    }
}
