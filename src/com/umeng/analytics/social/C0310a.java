package com.umeng.analytics.social;

/* compiled from: UMException */
public class C0310a extends RuntimeException {
    private static final long f111b = -4656673116019167471L;
    protected int f112a = 5000;
    private String f113c = "";

    public int m107a() {
        return this.f112a;
    }

    public C0310a(int i, String str) {
        super(str);
        this.f112a = i;
        this.f113c = str;
    }

    public C0310a(String str, Throwable th) {
        super(str, th);
        this.f113c = str;
    }

    public C0310a(String str) {
        super(str);
        this.f113c = str;
    }

    public String getMessage() {
        return this.f113c;
    }
}
