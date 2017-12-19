package p000u.aly;

import android.content.Context;

/* compiled from: IDFATracker */
public class C0655u extends C0379r {
    private static final String f989a = "idfa";
    private Context f990b;

    public C0655u(Context context) {
        super(f989a);
        this.f990b = context;
    }

    public String mo1737f() {
        String a = bh.m240a(this.f990b);
        return a == null ? "" : a;
    }
}
