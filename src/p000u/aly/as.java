package p000u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.C0306h;

/* compiled from: StatTracer */
public class as implements aj {
    private static final String f678h = "successful_request";
    private static final String f679i = "failed_requests ";
    private static final String f680j = "last_request_spent_ms";
    private static final String f681k = "last_request_time";
    private static final String f682l = "first_activate_time";
    private static final String f683m = "last_req";
    public int f684a;
    public int f685b;
    public long f686c;
    private final int f687d = 3600000;
    private int f688e;
    private long f689f = 0;
    private long f690g = 0;
    private Context f691n;

    public as(Context context) {
        m835a(context);
    }

    private void m835a(Context context) {
        this.f691n = context.getApplicationContext();
        SharedPreferences a = ap.m197a(context);
        this.f684a = a.getInt(f678h, 0);
        this.f685b = a.getInt(f679i, 0);
        this.f688e = a.getInt(f680j, 0);
        this.f686c = a.getLong(f681k, 0);
        this.f689f = a.getLong(f683m, 0);
    }

    public int m841e() {
        return this.f688e > 3600000 ? 3600000 : this.f688e;
    }

    public boolean m842f() {
        boolean z;
        if (this.f686c == 0) {
            z = true;
        } else {
            z = false;
        }
        boolean z2;
        if (C0306h.m71a(this.f691n).m97i()) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z && r3) {
            return true;
        }
        return false;
    }

    public void m843g() {
        this.f684a++;
        this.f686c = this.f689f;
    }

    public void m844h() {
        this.f685b++;
    }

    public void m845i() {
        this.f689f = System.currentTimeMillis();
    }

    public void m846j() {
        this.f688e = (int) (System.currentTimeMillis() - this.f689f);
    }

    public void m847k() {
        ap.m197a(this.f691n).edit().putInt(f678h, this.f684a).putInt(f679i, this.f685b).putInt(f680j, this.f688e).putLong(f681k, this.f686c).putLong(f683m, this.f689f).commit();
    }

    public void m848l() {
        ap.m197a(this.f691n).edit().putLong(f682l, System.currentTimeMillis()).commit();
    }

    public boolean m849m() {
        if (this.f690g == 0) {
            this.f690g = ap.m197a(this.f691n).getLong(f682l, 0);
        }
        return this.f690g == 0;
    }

    public long m850n() {
        return m849m() ? System.currentTimeMillis() : this.f690g;
    }

    public long m851o() {
        return this.f689f;
    }

    public static void m836a(Context context, av avVar) {
        SharedPreferences a = ap.m197a(context);
        avVar.f334a.f299L = (long) a.getInt(f679i, 0);
        avVar.f334a.f298K = (long) a.getInt(f678h, 0);
        avVar.f334a.f300M = (long) a.getInt(f680j, 0);
    }

    public void mo1747a() {
        m845i();
    }

    public void mo1748b() {
        m846j();
    }

    public void mo1749c() {
        m843g();
    }

    public void mo1750d() {
        m844h();
    }
}
