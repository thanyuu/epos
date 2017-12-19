package com.cloudpos.apidemo.common;

import android.content.Context;
import android.util.Log;
import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.activity.ConstantActivity;

public class Common {
    public static byte[] createMasterKey(int length) {
        byte[] array = new byte[length];
        for (int i = 0; i < length; i++) {
            array[i] = (byte) 56;
        }
        return array;
    }

    public static int transferErrorCode(int errorCode) {
        return -((-errorCode) & 255);
    }

    public static String getModel() {
        String model = "";
        model = SystemProperties.getsystemPropertie("ro.product.model").trim().replace(" ", "_").toUpperCase();
        Log.e("model", model);
        if (model.equals("WIZARHAND_Q1") || model.equals("MSM8610") || model.equals("WIZARHAND_Q0") || model.equals("FARS72_W_KK") || model.equals("WIZARHAND_M0")) {
            ConstantActivity.isHand = true;
            if (model.equals("WIZARHAND_Q1") || model.equals("MSM8610") || model.equals("WIZARHAND_Q0")) {
                ConstantActivity.isQ1 = true;
            }
        }
        return model;
    }

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[5].getMethodName();
    }

    public static boolean getActionField(Context context, String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(context.getResources().getString(C0223R.string.action_package_name) + className);
            return clazz.getField(fieldName).getBoolean(clazz.getInterfaces());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return false;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }
}
