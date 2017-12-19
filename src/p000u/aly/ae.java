package p000u.aly;

import android.content.Context;
import com.umeng.analytics.C0295f;
import com.umeng.analytics.C0296g;

/* compiled from: CacheService */
public final class ae implements ah {
    private static ae f672c;
    private ah f673a = new ad(this.f674b);
    private Context f674b;

    /* compiled from: CacheService */
    class C06182 extends C0296g {
        final /* synthetic */ ae f669a;

        C06182(ae aeVar) {
            this.f669a = aeVar;
        }

        public void mo1733a() {
            this.f669a.f673a.mo1738a();
        }
    }

    /* compiled from: CacheService */
    class C06193 extends C0296g {
        final /* synthetic */ ae f670a;

        C06193(ae aeVar) {
            this.f670a = aeVar;
        }

        public void mo1733a() {
            this.f670a.f673a.mo1741b();
        }
    }

    /* compiled from: CacheService */
    class C06204 extends C0296g {
        final /* synthetic */ ae f671a;

        C06204(ae aeVar) {
            this.f671a = aeVar;
        }

        public void mo1733a() {
            this.f671a.f673a.mo1743c();
        }
    }

    private ae(Context context) {
        this.f674b = context.getApplicationContext();
    }

    public static synchronized ae m823a(Context context) {
        ae aeVar;
        synchronized (ae.class) {
            if (f672c == null && context != null) {
                f672c = new ae(context);
            }
            aeVar = f672c;
        }
        return aeVar;
    }

    public void m826a(ah ahVar) {
        this.f673a = ahVar;
    }

    public void mo1739a(final ai aiVar) {
        C0295f.m49b(new C0296g(this) {
            final /* synthetic */ ae f668b;

            public void mo1733a() {
                this.f668b.f673a.mo1739a(aiVar);
            }
        });
    }

    public void mo1742b(ai aiVar) {
        this.f673a.mo1742b(aiVar);
    }

    public void mo1738a() {
        C0295f.m49b(new C06182(this));
    }

    public void mo1741b() {
        C0295f.m49b(new C06193(this));
    }

    public void mo1743c() {
        C0295f.m50c(new C06204(this));
    }
}
