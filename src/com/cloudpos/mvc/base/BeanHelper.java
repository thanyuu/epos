package com.cloudpos.mvc.base;

import com.cloudpos.mvc.common.Logger;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BeanHelper {
    private Method[] declaredMethods;
    private Class<?> mClass;
    private Object mObject;

    public BeanHelper(Class<?> clazz) {
        this.mClass = clazz;
    }

    public BeanHelper(Object obj) {
        this.mObject = obj;
        this.mClass = this.mObject.getClass();
    }

    public Method getMethod(String methodName, Class<?>... classes) throws NoSuchMethodException {
        this.declaredMethods = this.mClass.getDeclaredMethods();
        Method result = null;
        int matchLevel = -1;
        boolean isFirst = true;
        for (Method method : this.declaredMethods) {
            if (method.getName().equals(methodName)) {
                Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length == classes.length) {
                    int tempMatchLevel = matchLevel(paramTypes, classes);
                    if (tempMatchLevel >= 0) {
                        if (isFirst && matchLevel < tempMatchLevel) {
                            isFirst = false;
                            matchLevel = tempMatchLevel;
                        } else if (matchLevel < tempMatchLevel) {
                            matchLevel = tempMatchLevel;
                        }
                        result = method;
                    }
                }
            }
        }
        if (result != null) {
            return result;
        }
        throw new NoSuchMethodException(methodName + " " + Arrays.asList(classes).toString() + "");
    }

    public Class<?> getClosestClass(Class<?> clazz) {
        return clazz.getSuperclass();
    }

    public int matchLevel(Class<?>[] paramTypes, Class<?>[] transferParamTypes) {
        int matchLevel = -1;
        for (int m = 0; m < paramTypes.length; m++) {
            Class<?> paramType = paramTypes[m];
            Class<?> tParamType = transferParamTypes[m];
            if (!paramType.equals(tParamType)) {
                List<Class<?>> superClasses = getAllSuperClass(tParamType);
                for (int n = 1; n <= superClasses.size(); n++) {
                    Class<?> superClass = (Class) superClasses.get(n - 1);
                    if (superClass != null && !superClass.equals(paramType)) {
                        break;
                    }
                    matchLevel += n;
                }
            } else {
                matchLevel++;
            }
        }
        return matchLevel;
    }

    public static List<Class<?>> getAllSuperClass(Class<?> clazz) {
        List<Class<?>> classes = new ArrayList();
        Class<?> cla = clazz;
        do {
            cla = cla.getSuperclass();
            Logger.debug("class: " + clazz + ", super class: " + cla);
            if (cla != null) {
                classes.add(cla);
            }
            if (cla != null && cla.equals(Object.class)) {
                break;
            }
        } while (cla != null);
        return classes;
    }
}
