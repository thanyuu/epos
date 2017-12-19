package com.android.common.entity;

public class FailedReason {
    private Throwable cause;
    private FailedType failedType;

    public enum FailedType {
        ERROR_IO,
        ERROR_OUT_OF_MEMORY,
        ERROR_UNKNOWN
    }

    public FailedReason(FailedType failedType, String cause) {
        this.failedType = failedType;
        this.cause = new Throwable(cause);
    }

    public FailedReason(FailedType failedType, Throwable cause) {
        this.failedType = failedType;
        this.cause = cause;
    }

    public FailedType getFailedType() {
        return this.failedType;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
