package com.android.common.utils;

public class SystemUtils {
    public static final int DEFAULT_THREAD_POOL_SIZE = getDefaultThreadPoolSize();

    public static int getDefaultThreadPoolSize() {
        return getDefaultThreadPoolSize(8);
    }

    public static int getDefaultThreadPoolSize(int max) {
        int availableProcessors = (Runtime.getRuntime().availableProcessors() * 2) + 1;
        return availableProcessors > max ? max : availableProcessors;
    }
}
