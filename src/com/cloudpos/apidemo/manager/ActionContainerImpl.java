package com.cloudpos.apidemo.manager;

import android.content.Context;
import android.util.Log;
import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.function.ActionReflect;
import com.cloudpos.mvc.base.ActionContainer;
import java.util.List;

public class ActionContainerImpl extends ActionContainer {
    private static final String TAG = "ActionContainerImpl";
    private Context context;
    private String model;

    public ActionContainerImpl(Context context, String model) {
        this.context = context;
        this.model = model;
    }

    public void initActions() {
        Log.e(TAG, "Model = " + this.model);
        List<String> classItems = ActionReflect.getArraysXml(this.context, this.model);
        if (classItems == null) {
            classItems = ActionReflect.getArraysXml(this.context, "WIZARPOS");
        }
        for (int i = 0; i < classItems.size(); i++) {
            try {
                addAction((String) classItems.get(i), Class.forName(this.context.getResources().getString(C0223R.string.action_package_name) + ((String) classItems.get(i))), true);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "Can't find this action");
            }
        }
    }
}
