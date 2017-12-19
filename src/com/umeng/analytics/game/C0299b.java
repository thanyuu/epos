package com.umeng.analytics.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.io.Serializable;
import p000u.aly.am;
import p000u.aly.ap;

/* compiled from: GameState */
public class C0299b {
    public String f69a;
    public String f70b;
    private Context f71c;
    private final String f72d = "um_g_cache";
    private final String f73e = "single_level";
    private final String f74f = "stat_player_level";
    private final String f75g = "stat_game_level";
    private C0298a f76h = null;

    /* compiled from: GameState */
    static class C0298a implements Serializable {
        private static final long f65a = 20140327;
        private String f66b;
        private long f67c;
        private long f68d;

        public C0298a(String str) {
            this.f66b = str;
        }

        public boolean m53a(String str) {
            return this.f66b.equals(str);
        }

        public void m52a() {
            this.f68d = System.currentTimeMillis();
        }

        public void m54b() {
            this.f67c += System.currentTimeMillis() - this.f68d;
            this.f68d = 0;
        }

        public void m55c() {
            m52a();
        }

        public void m56d() {
            m54b();
        }

        public long m57e() {
            return this.f67c;
        }

        public String m58f() {
            return this.f66b;
        }
    }

    public C0299b(Context context) {
        this.f71c = context;
    }

    public C0298a m59a(String str) {
        this.f76h = new C0298a(str);
        this.f76h.m52a();
        return this.f76h;
    }

    public void m60a() {
        if (this.f76h != null) {
            this.f76h.m54b();
            Editor edit = this.f71c.getSharedPreferences("um_g_cache", 0).edit();
            edit.putString("single_level", am.m192a(this.f76h));
            edit.putString("stat_player_level", this.f70b);
            edit.putString("stat_game_level", this.f69a);
            edit.commit();
        }
    }

    public void m62b() {
        SharedPreferences a = ap.m198a(this.f71c, "um_g_cache");
        String string = a.getString("single_level", null);
        if (string != null) {
            this.f76h = (C0298a) am.m191a(string);
            if (this.f76h != null) {
                this.f76h.m55c();
            }
        }
        if (this.f70b == null) {
            this.f70b = a.getString("stat_player_level", null);
            if (this.f70b == null) {
                SharedPreferences a2 = ap.m197a(this.f71c);
                if (a2 != null) {
                    this.f70b = a2.getString("userlevel", null);
                } else {
                    return;
                }
            }
        }
        if (this.f69a == null) {
            this.f69a = a.getString("stat_game_level", null);
        }
    }

    public C0298a m61b(String str) {
        if (this.f76h != null) {
            this.f76h.m56d();
            if (this.f76h.m53a(str)) {
                C0298a c0298a = this.f76h;
                this.f76h = null;
                return c0298a;
            }
        }
        return null;
    }
}
