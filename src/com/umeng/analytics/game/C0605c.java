package com.umeng.analytics.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import com.umeng.analytics.C0293c;
import com.umeng.analytics.C0295f;
import com.umeng.analytics.C0296g;
import com.umeng.analytics.C0602d;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.game.C0299b.C0298a;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import p000u.aly.ap;
import p000u.aly.bl;

/* compiled from: InternalGameAgent */
class C0605c implements C0293c {
    private C0602d f617a = MobclickAgent.getAgent();
    private C0299b f618b = null;
    private final int f619c = 100;
    private final int f620d = 1;
    private final int f621e = 0;
    private final int f622f = -1;
    private final int f623g = 1;
    private final String f624h = "level";
    private final String f625i = "pay";
    private final String f626j = "buy";
    private final String f627k = "use";
    private final String f628l = "bonus";
    private final String f629m = "item";
    private final String f630n = "cash";
    private final String f631o = "coin";
    private final String f632p = "source";
    private final String f633q = "amount";
    private final String f634r = "user_level";
    private final String f635s = "bonus_source";
    private final String f636t = "level";
    private final String f637u = "status";
    private final String f638v = "duration";
    private final String f639w = "curtype";
    private final String f640x = "orderid";
    private final String f641y = "UMGameAgent.init(Context) should be called before any game api";
    private Context f642z;

    public C0605c() {
        C0297a.f64a = true;
    }

    void m777a(Context context) {
        if (context == null) {
            bl.m340e("Context is null, can't init GameAgent");
            return;
        }
        this.f642z = context.getApplicationContext();
        this.f617a.m746a((C0293c) this);
        this.f618b = new C0299b(this.f642z);
        this.f617a.m737a(context, 1);
    }

    void m781a(boolean z) {
        bl.m322b(String.format("Trace sleep time : %b", new Object[]{Boolean.valueOf(z)}));
        C0297a.f64a = z;
    }

    void m778a(String str) {
        this.f618b.f70b = str;
        SharedPreferences a = ap.m197a(this.f642z);
        if (a != null) {
            Editor edit = a.edit();
            edit.putString("userlevel", str);
            edit.commit();
        }
    }

    void m783b(final String str) {
        if (this.f642z == null) {
            bl.m340e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        this.f618b.f69a = str;
        C0295f.m47a(new C0296g(this) {
            final /* synthetic */ C0605c f613b;

            public void mo1733a() {
                this.f613b.f618b.m59a(str);
                HashMap hashMap = new HashMap();
                hashMap.put("level", str);
                hashMap.put("status", Integer.valueOf(0));
                if (this.f613b.f618b.f70b != null) {
                    hashMap.put("user_level", this.f613b.f618b.f70b);
                }
                this.f613b.f617a.m741a(this.f613b.f642z, "level", hashMap);
            }
        });
    }

    private void m769a(final String str, final int i) {
        if (this.f642z == null) {
            bl.m340e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            C0295f.m47a(new C0296g(this) {
                final /* synthetic */ C0605c f616c;

                public void mo1733a() {
                    C0298a b = this.f616c.f618b.m61b(str);
                    if (b != null) {
                        long e = b.m57e();
                        if (e <= 0) {
                            bl.m322b("level duration is 0");
                            return;
                        }
                        HashMap hashMap = new HashMap();
                        hashMap.put("level", str);
                        hashMap.put("status", Integer.valueOf(i));
                        hashMap.put("duration", Long.valueOf(e));
                        if (this.f616c.f618b.f70b != null) {
                            hashMap.put("user_level", this.f616c.f618b.f70b);
                        }
                        this.f616c.f617a.m741a(this.f616c.f642z, "level", hashMap);
                        return;
                    }
                    bl.m334d(String.format("finishLevel(or failLevel) called before startLevel", new Object[0]));
                }
            });
        }
    }

    void m785c(String str) {
        if (this.f642z == null) {
            bl.m340e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            m769a(str, 1);
        }
    }

    void m786d(String str) {
        if (this.f642z == null) {
            bl.m340e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            m769a(str, -1);
        }
    }

    void m773a(double d, double d2, int i) {
        if (this.f642z == null) {
            bl.m340e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("cash", Long.valueOf((long) (d * 100.0d)));
        hashMap.put("coin", Long.valueOf((long) (d2 * 100.0d)));
        hashMap.put("source", Integer.valueOf(i));
        if (this.f618b.f70b != null) {
            hashMap.put("user_level", this.f618b.f70b);
        }
        if (this.f618b.f69a != null) {
            hashMap.put("level", this.f618b.f69a);
        }
        this.f617a.m741a(this.f642z, "pay", hashMap);
    }

    void m776a(double d, String str, int i, double d2, int i2) {
        m773a(d, d2 * ((double) i), i2);
        m779a(str, i, d2);
    }

    void m779a(String str, int i, double d) {
        if (this.f642z == null) {
            bl.m340e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("item", str);
        hashMap.put("amount", Integer.valueOf(i));
        hashMap.put("coin", Long.valueOf((long) ((((double) i) * d) * 100.0d)));
        if (this.f618b.f70b != null) {
            hashMap.put("user_level", this.f618b.f70b);
        }
        if (this.f618b.f69a != null) {
            hashMap.put("level", this.f618b.f69a);
        }
        this.f617a.m741a(this.f642z, "buy", hashMap);
    }

    void m784b(String str, int i, double d) {
        if (this.f642z == null) {
            bl.m340e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("item", str);
        hashMap.put("amount", Integer.valueOf(i));
        hashMap.put("coin", Long.valueOf((long) ((((double) i) * d) * 100.0d)));
        if (this.f618b.f70b != null) {
            hashMap.put("user_level", this.f618b.f70b);
        }
        if (this.f618b.f69a != null) {
            hashMap.put("level", this.f618b.f69a);
        }
        this.f617a.m741a(this.f642z, "use", hashMap);
    }

    void m774a(double d, int i) {
        if (this.f642z == null) {
            bl.m340e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("coin", Long.valueOf((long) (100.0d * d)));
        hashMap.put("bonus_source", Integer.valueOf(i));
        if (this.f618b.f70b != null) {
            hashMap.put("user_level", this.f618b.f70b);
        }
        if (this.f618b.f69a != null) {
            hashMap.put("level", this.f618b.f69a);
        }
        this.f617a.m741a(this.f642z, "bonus", hashMap);
    }

    void m780a(String str, int i, double d, int i2) {
        m774a(((double) i) * d, i2);
        m779a(str, i, d);
    }

    public void mo1735a() {
        bl.m322b("App resume from background");
        if (this.f642z == null) {
            bl.m340e("UMGameAgent.init(Context) should be called before any game api");
        } else if (C0297a.f64a) {
            this.f618b.m62b();
        }
    }

    public void mo1736b() {
        if (this.f642z == null) {
            bl.m340e("UMGameAgent.init(Context) should be called before any game api");
        } else if (C0297a.f64a) {
            this.f618b.m60a();
        }
    }

    void m775a(double d, String str, double d2, int i, String str2) {
        if (this.f642z == null) {
            bl.m340e("UMGameAgent.init(Context) should be called before any game api");
        } else if (d >= 0.0d && d2 >= 0.0d) {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(str) && str.length() > 0 && str.length() <= 3) {
                hashMap.put("curtype", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                try {
                    int length = str2.getBytes(Key.STRING_CHARSET_NAME).length;
                    if (length > 0 && length <= 1024) {
                        hashMap.put("orderid", str2);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            hashMap.put("cash", Long.valueOf((long) (d * 100.0d)));
            hashMap.put("coin", Long.valueOf((long) (d2 * 100.0d)));
            hashMap.put("source", Integer.valueOf(i));
            if (this.f618b.f70b != null) {
                hashMap.put("user_level", this.f618b.f70b);
            }
            if (this.f618b.f69a != null) {
                hashMap.put("level", this.f618b.f69a);
            }
            this.f617a.m741a(this.f642z, "pay", hashMap);
        }
    }
}
