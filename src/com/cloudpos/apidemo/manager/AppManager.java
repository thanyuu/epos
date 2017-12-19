package com.cloudpos.apidemo.manager;

import android.app.Application;
import android.util.Log;
import com.cloudpos.mvc.base.ActionManager;

public class AppManager extends Application {
    public static String model = "";

    public void onCreate() {
        super.onCreate();
        Log.e("===================", "onCreate: ");
        model = "WIZARPOS";
        ActionManager.initActionContainer(new ActionContainerImpl(this, model));
    }
}
