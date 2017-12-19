package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.analytics.MobclickAgent.UMAnalyticsConfig;
import com.umeng.analytics.social.C0314e;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import p000u.aly.C0370m;
import p000u.aly.C0652f;
import p000u.aly.ae;
import p000u.aly.af;
import p000u.aly.ag;
import p000u.aly.ai;
import p000u.aly.an;
import p000u.aly.ap;
import p000u.aly.ar;
import p000u.aly.at;
import p000u.aly.av.C0623i;
import p000u.aly.bj;
import p000u.aly.bl;

/* compiled from: InternalAgent */
public class C0602d implements an {
    private Context f596a = null;
    private C0293c f597b;
    private af f598c = new af();
    private at f599d = new at();
    private ar f600e = new ar();
    private ag f601f;
    private ae f602g;
    private C0370m f603h = null;
    private boolean f604i = false;
    private boolean f605j = false;

    /* compiled from: InternalAgent */
    class C05971 extends C0296g {
        final /* synthetic */ C0602d f587a;

        /* compiled from: InternalAgent */
        class C06811 extends C0652f {
            final /* synthetic */ C05971 f998a;

            C06811(C05971 c05971) {
                this.f998a = c05971;
            }

            public void mo1814a(Object obj, boolean z) {
                this.f998a.f587a.f605j = true;
            }
        }

        C05971(C0602d c0602d) {
            this.f587a = c0602d;
        }

        public void mo1733a() {
            this.f587a.f603h.m583a(new C06811(this));
        }
    }

    /* compiled from: InternalAgent */
    class C06015 extends C0296g {
        final /* synthetic */ C0602d f595a;

        C06015(C0602d c0602d) {
            this.f595a = c0602d;
        }

        public void mo1733a() {
            String[] a = C0294e.m44a(this.f595a.f596a);
            if (a != null && !TextUtils.isEmpty(a[0]) && !TextUtils.isEmpty(a[1])) {
                boolean e = this.f595a.m733a().m220e(this.f595a.f596a);
                ae.m823a(this.f595a.f596a).mo1743c();
                if (e) {
                    this.f595a.m733a().m221f(this.f595a.f596a);
                }
                C0294e.m45b(this.f595a.f596a);
            }
        }
    }

    C0602d() {
        this.f598c.m159a((an) this);
    }

    private void m730e(Context context) {
        if (!this.f604i) {
            this.f596a = context.getApplicationContext();
            this.f601f = new ag(this.f596a);
            this.f602g = ae.m823a(this.f596a);
            this.f604i = true;
            if (this.f603h == null) {
                this.f603h = C0370m.m556a(this.f596a);
            }
            if (!this.f605j) {
                C0295f.m49b(new C05971(this));
            }
        }
    }

    void m747a(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.f599d.m225a(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void m756b(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.f599d.m226b(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void m746a(C0293c c0293c) {
        this.f597b = c0293c;
    }

    public void m737a(Context context, int i) {
        AnalyticsConfig.m23a(context, i);
    }

    public void m748a(String str, String str2) {
        AnalyticsConfig.mWrapperType = str;
        AnalyticsConfig.mWrapperVersion = str2;
    }

    void m736a(final Context context) {
        if (context == null) {
            bl.m340e("unexpected null context in onResume");
            return;
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            this.f599d.m225a(context.getClass().getName());
        }
        try {
            if (!this.f604i) {
                m730e(context);
            }
            C0295f.m47a(new C0296g(this) {
                final /* synthetic */ C0602d f589b;

                public void mo1733a() {
                    this.f589b.m731f(context.getApplicationContext());
                }
            });
        } catch (Throwable e) {
            bl.m342e("Exception occurred in Mobclick.onResume(). ", e);
        }
    }

    void m754b(final Context context) {
        if (context == null) {
            bl.m340e("unexpected null context in onPause");
            return;
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            this.f599d.m226b(context.getClass().getName());
        }
        try {
            if (!this.f604i) {
                m730e(context);
            }
            C0295f.m47a(new C0296g(this) {
                final /* synthetic */ C0602d f591b;

                public void mo1733a() {
                    this.f591b.m732g(context.getApplicationContext());
                    this.f591b.f603h.m590d();
                }
            });
        } catch (Throwable e) {
            bl.m342e("Exception occurred in Mobclick.onRause(). ", e);
        }
    }

    public ar m733a() {
        return this.f600e;
    }

    public void m741a(Context context, String str, HashMap<String, Object> hashMap) {
        try {
            if (!this.f604i) {
                m730e(context);
            }
            this.f601f.m165a(str, hashMap);
        } catch (Throwable e) {
            bl.m344e(e);
        }
    }

    void m739a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (context == null) {
                bl.m340e("unexpected null context in reportError");
                return;
            }
            try {
                if (!this.f604i) {
                    m730e(context);
                }
                ai c0623i = new C0623i();
                c0623i.f693a = System.currentTimeMillis();
                c0623i.f694b = 2;
                c0623i.f695c = str;
                this.f602g.mo1739a(c0623i);
            } catch (Throwable e) {
                bl.m344e(e);
            }
        }
    }

    void m743a(Context context, Throwable th) {
        if (context != null && th != null) {
            try {
                m739a(context, C0292b.m33a(th));
            } catch (Throwable e) {
                bl.m344e(e);
            }
        }
    }

    private void m731f(Context context) {
        this.f600e.m218c(context);
        if (this.f597b != null) {
            this.f597b.mo1735a();
        }
    }

    private void m732g(Context context) {
        this.f600e.m219d(context);
        this.f599d.m224a(context);
        if (this.f597b != null) {
            this.f597b.mo1736b();
        }
        this.f602g.mo1741b();
    }

    void m759c(Context context) {
        try {
            if (!this.f604i) {
                m730e(context);
            }
            this.f602g.mo1738a();
        } catch (Throwable e) {
            bl.m344e(e);
        }
    }

    public void m744a(Context context, List<String> list, int i, String str) {
        try {
            if (!this.f604i) {
                m730e(context);
            }
            this.f601f.m167a((List) list, i, str);
        } catch (Throwable e) {
            bl.m344e(e);
        }
    }

    public void m740a(Context context, String str, String str2, long j, int i) {
        try {
            if (!this.f604i) {
                m730e(context);
            }
            this.f601f.m164a(str, str2, j, i);
        } catch (Throwable e) {
            bl.m344e(e);
        }
    }

    void m742a(Context context, String str, Map<String, Object> map, long j) {
        try {
            if (!this.f604i) {
                m730e(context);
            }
            this.f601f.m166a(str, (Map) map, j);
        } catch (Throwable e) {
            bl.m344e(e);
        }
    }

    void m761d(Context context) {
        try {
            this.f599d.m223a();
            m732g(context);
            ap.m197a(context).edit().commit();
            this.f603h.m587b();
            C0295f.m46a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mo1734a(Throwable th) {
        try {
            this.f599d.m223a();
            if (this.f596a != null) {
                if (!(th == null || this.f602g == null)) {
                    ai c0623i = new C0623i();
                    c0623i.f693a = System.currentTimeMillis();
                    c0623i.f694b = 1;
                    c0623i.f695c = C0292b.m33a(th);
                    this.f602g.mo1739a(c0623i);
                }
                this.f603h.m589c();
                m732g(this.f596a);
                ap.m197a(this.f596a).edit().commit();
            }
            C0295f.m46a();
        } catch (Throwable e) {
            bl.m342e("Exception in onAppCrash", e);
        }
    }

    void m757b(final String str, final String str2) {
        try {
            C0295f.m47a(new C0296g(this) {
                final /* synthetic */ C0602d f594c;

                public void mo1733a() {
                    String[] a = C0294e.m44a(this.f594c.f596a);
                    if (a == null || !str.equals(a[0]) || !str2.equals(a[1])) {
                        boolean e = this.f594c.m733a().m220e(this.f594c.f596a);
                        ae.m823a(this.f594c.f596a).mo1743c();
                        if (e) {
                            this.f594c.m733a().m221f(this.f594c.f596a);
                        }
                        C0294e.m43a(this.f594c.f596a, str, str2);
                    }
                }
            });
        } catch (Throwable e) {
            bl.m342e(" Excepthon  in  onProfileSignIn", e);
        }
    }

    void m752b() {
        try {
            C0295f.m47a(new C06015(this));
        } catch (Throwable e) {
            bl.m342e(" Excepthon  in  onProfileSignOff", e);
        }
    }

    void m751a(boolean z) {
        AnalyticsConfig.CATCH_EXCEPTION = z;
    }

    void m758b(boolean z) {
        AnalyticsConfig.ENABLE_MEMORY_BUFFER = z;
    }

    void m750a(GL10 gl10) {
        String[] a = bj.m266a(gl10);
        if (a.length == 2) {
            AnalyticsConfig.GPU_VENDER = a[0];
            AnalyticsConfig.GPU_RENDERER = a[1];
        }
    }

    void m760c(boolean z) {
        AnalyticsConfig.ACTIVITY_DURATION_OPEN = z;
    }

    void m762d(boolean z) {
        C0291a.f36e = z;
    }

    void m763e(boolean z) {
        bl.f355a = z;
        C0314e.f139v = z;
    }

    void m764f(boolean z) {
        AnalyticsConfig.m26a(z);
    }

    void m734a(double d, double d2) {
        if (AnalyticsConfig.f13a == null) {
            AnalyticsConfig.f13a = new double[2];
        }
        AnalyticsConfig.f13a[0] = d;
        AnalyticsConfig.f13a[1] = d2;
    }

    void m735a(long j) {
        AnalyticsConfig.sLatentWindow = ((int) j) * 1000;
    }

    void m738a(Context context, EScenarioType eScenarioType) {
        if (context != null) {
            this.f596a = context.getApplicationContext();
        }
        if (eScenarioType != null) {
            m737a(context, eScenarioType.toValue());
        }
    }

    void m755b(Context context, String str) {
        if (context != null) {
            this.f596a = context.getApplicationContext();
        }
        AnalyticsConfig.m27b(context, str);
    }

    void m745a(UMAnalyticsConfig uMAnalyticsConfig) {
        if (uMAnalyticsConfig.mContext != null) {
            this.f596a = uMAnalyticsConfig.mContext.getApplicationContext();
        }
        if (TextUtils.isEmpty(uMAnalyticsConfig.mAppkey)) {
            bl.m340e("the appkey is null!");
            return;
        }
        AnalyticsConfig.m24a(uMAnalyticsConfig.mContext, uMAnalyticsConfig.mAppkey);
        if (!TextUtils.isEmpty(uMAnalyticsConfig.mChannelId)) {
            AnalyticsConfig.m25a(uMAnalyticsConfig.mChannelId);
        }
        AnalyticsConfig.CATCH_EXCEPTION = uMAnalyticsConfig.mIsCrashEnable;
        m738a(this.f596a, uMAnalyticsConfig.mType);
    }

    void m753b(long j) {
        AnalyticsConfig.kContinueSessionMillis = j;
    }
}
