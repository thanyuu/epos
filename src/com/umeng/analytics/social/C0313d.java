package com.umeng.analytics.social;

/* compiled from: UMResult */
public class C0313d {
    private int f114a = -1;
    private String f115b = "";
    private String f116c = "";
    private Exception f117d = null;

    public C0313d(int i) {
        this.f114a = i;
    }

    public C0313d(int i, Exception exception) {
        this.f114a = i;
        this.f117d = exception;
    }

    public Exception m121a() {
        return this.f117d;
    }

    public int m124b() {
        return this.f114a;
    }

    public void m122a(int i) {
        this.f114a = i;
    }

    public String m126c() {
        return this.f115b;
    }

    public void m123a(String str) {
        this.f115b = str;
    }

    public String m127d() {
        return this.f116c;
    }

    public void m125b(String str) {
        this.f116c = str;
    }

    public String toString() {
        return "status=" + this.f114a + "\r\n" + "msg:  " + this.f115b + "\r\n" + "data:  " + this.f116c;
    }
}
