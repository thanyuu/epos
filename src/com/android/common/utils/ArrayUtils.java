package com.android.common.utils;

public class ArrayUtils {
    public static <V> boolean isEmpty(V[] sourceArray) {
        return sourceArray == null || sourceArray.length == 0;
    }

    public static <V> V getLast(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
        if (isEmpty(sourceArray)) {
            return defaultValue;
        }
        int currentPosition = -1;
        for (int i = 0; i < sourceArray.length; i++) {
            if (ObjectUtils.isEquals(value, sourceArray[i])) {
                currentPosition = i;
                break;
            }
        }
        if (currentPosition == -1) {
            return defaultValue;
        }
        if (currentPosition != 0) {
            return sourceArray[currentPosition - 1];
        }
        if (isCircle) {
            return sourceArray[sourceArray.length - 1];
        }
        return defaultValue;
    }

    public static <V> V getNext(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
        if (isEmpty(sourceArray)) {
            return defaultValue;
        }
        int currentPosition = -1;
        for (int i = 0; i < sourceArray.length; i++) {
            if (ObjectUtils.isEquals(value, sourceArray[i])) {
                currentPosition = i;
                break;
            }
        }
        if (currentPosition == -1) {
            return defaultValue;
        }
        if (currentPosition != sourceArray.length - 1) {
            return sourceArray[currentPosition + 1];
        }
        if (isCircle) {
            return sourceArray[0];
        }
        return defaultValue;
    }

    public static <V> V getLast(V[] sourceArray, V value, boolean isCircle) {
        return getLast((Object[]) sourceArray, (Object) value, null, isCircle);
    }

    public static <V> V getNext(V[] sourceArray, V value, boolean isCircle) {
        return getNext((Object[]) sourceArray, (Object) value, null, isCircle);
    }

    public static long getLast(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
        if (sourceArray.length != 0) {
            return ((Long) getLast(ObjectUtils.transformLongArray(sourceArray), Long.valueOf(value), Long.valueOf(defaultValue), isCircle)).longValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0.");
    }

    public static long getNext(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
        if (sourceArray.length != 0) {
            return ((Long) getNext(ObjectUtils.transformLongArray(sourceArray), Long.valueOf(value), Long.valueOf(defaultValue), isCircle)).longValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0.");
    }

    public static int getLast(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
        if (sourceArray.length != 0) {
            return ((Integer) getLast(ObjectUtils.transformIntArray(sourceArray), Integer.valueOf(value), Integer.valueOf(defaultValue), isCircle)).intValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0.");
    }

    public static int getNext(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
        if (sourceArray.length != 0) {
            return ((Integer) getNext(ObjectUtils.transformIntArray(sourceArray), Integer.valueOf(value), Integer.valueOf(defaultValue), isCircle)).intValue();
        }
        throw new IllegalArgumentException("The length of source array must be greater than 0.");
    }
}
