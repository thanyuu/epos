package com.cloudpos.apidemo.function;

import android.content.Context;
import com.cloudpos.apidemo.activity.C0223R.array;
import com.cloudpos.apidemo.activity.C0223R.string;
import com.cloudpos.apidemo.util.StringUtility;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ActionReflect {
    public static int getFields(Object obj, String model) {
        int stringId = 0;
        Field[] fields = obj.getClass().getFields();
        int length = fields.length;
        int i = 0;
        while (i < length) {
            Field field = fields[i];
            if (model.equals(field.getName())) {
                try {
                    return field.getInt(obj);
                } catch (IllegalAccessException e) {
                } catch (IllegalArgumentException e2) {
                }
            } else {
                i++;
            }
        }
        return stringId;
    }

    public static List<String> getStringsXml(Context con, String name) {
        int stringId = getFields(new string(), name);
        List<String> array = new ArrayList();
        try {
            for (String string : StringUtility.spiltStrings(con.getString(stringId), ",")) {
                array.add(string);
            }
            return array;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> getArraysXml(Context con, String name) {
        int stringId = getFields(new array(), name);
        List<String> array = new ArrayList();
        try {
            for (String classItem : con.getResources().getStringArray(stringId)) {
                array.add(classItem);
            }
            return array;
        } catch (Exception e) {
            return null;
        }
    }
}
