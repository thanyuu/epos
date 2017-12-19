package com.cloudpos.mvc.base;

import com.cloudpos.mvc.common.Logger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ActionScheduler extends Thread {
    private static ActionScheduler actionScheduler = new ActionScheduler();
    private LinkedBlockingQueue<ActionContext> mActionQueue = new LinkedBlockingQueue(20);
    private ExecutorService service = Executors.newFixedThreadPool(30);

    public static ActionScheduler getInstance() {
        return actionScheduler;
    }

    public void run() {
        while (true) {
            try {
                this.service.submit((ActionContext) this.mActionQueue.take());
            } catch (Exception e) {
                Logger.error("调度器发生错误", e);
            }
        }
    }

    public void setActionContext(ActionContext context) {
        if (context != null) {
            try {
                this.mActionQueue.put(context);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
