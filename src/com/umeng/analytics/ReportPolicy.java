package com.umeng.analytics;

import android.content.Context;
import p000u.aly.ak;
import p000u.aly.as;
import p000u.aly.ax;
import p000u.aly.bj;

public class ReportPolicy {
    public static final int BATCH_AT_LAUNCH = 1;
    public static final int BATCH_BY_INTERVAL = 6;
    public static final int DAILY = 4;
    public static final int REALTIME = 0;
    public static final int SMART_POLICY = 8;
    public static final int WIFIONLY = 5;
    static final int f29a = 2;
    static final int f30b = 3;

    public static class C0290i {
        public boolean mo1731a(boolean z) {
            return true;
        }

        public boolean mo1732a() {
            return true;
        }
    }

    public static class C0587a extends C0290i {
        private final long f570a = 15000;
        private as f571b;

        public C0587a(as asVar) {
            this.f571b = asVar;
        }

        public boolean mo1731a(boolean z) {
            if (System.currentTimeMillis() - this.f571b.f686c >= 15000) {
                return true;
            }
            return false;
        }
    }

    public static class C0588b extends C0290i {
        private ax f572a;
        private as f573b;

        public C0588b(as asVar, ax axVar) {
            this.f573b = asVar;
            this.f572a = axVar;
        }

        public boolean mo1731a(boolean z) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f573b.f686c >= this.f572a.m868a()) {
                return true;
            }
            return false;
        }

        public boolean mo1732a() {
            return this.f572a.m874c();
        }
    }

    public static class C0589c extends C0290i {
        private long f574a;
        private long f575b = 0;

        public C0589c(int i) {
            this.f574a = (long) i;
            this.f575b = System.currentTimeMillis();
        }

        public boolean mo1731a(boolean z) {
            if (System.currentTimeMillis() - this.f575b >= this.f574a) {
                return true;
            }
            return false;
        }

        public boolean mo1732a() {
            return System.currentTimeMillis() - this.f575b < this.f574a;
        }
    }

    public static class C0590d extends C0290i {
        public boolean mo1731a(boolean z) {
            return z;
        }
    }

    public static class C0591e extends C0290i {
        private static long f576a = 90000;
        private static long f577b = C0291a.f41j;
        private long f578c;
        private as f579d;

        public C0591e(as asVar, long j) {
            this.f579d = asVar;
            m712a(j);
        }

        public boolean mo1731a(boolean z) {
            if (System.currentTimeMillis() - this.f579d.f686c >= this.f578c) {
                return true;
            }
            return false;
        }

        public void m712a(long j) {
            if (j < f576a || j > f577b) {
                this.f578c = f576a;
            } else {
                this.f578c = j;
            }
        }

        public long m714b() {
            return this.f578c;
        }

        public static boolean m711a(int i) {
            if (((long) i) < f576a) {
                return false;
            }
            return true;
        }
    }

    public static class C0592f extends C0290i {
        private final int f580a;
        private ak f581b;

        public C0592f(ak akVar, int i) {
            this.f580a = i;
            this.f581b = akVar;
        }

        public boolean mo1731a(boolean z) {
            return this.f581b.m183b() > this.f580a;
        }
    }

    public static class C0593g extends C0290i {
        private long f582a = C0291a.f41j;
        private as f583b;

        public C0593g(as asVar) {
            this.f583b = asVar;
        }

        public boolean mo1731a(boolean z) {
            if (System.currentTimeMillis() - this.f583b.f686c >= this.f582a) {
                return true;
            }
            return false;
        }
    }

    public static class C0594h extends C0290i {
        public boolean mo1731a(boolean z) {
            return true;
        }
    }

    public static class C0595j extends C0290i {
        private Context f584a = null;

        public C0595j(Context context) {
            this.f584a = context;
        }

        public boolean mo1731a(boolean z) {
            return bj.m286n(this.f584a);
        }
    }

    public static class C0596k extends C0290i {
        private final long f585a = 10800000;
        private as f586b;

        public C0596k(as asVar) {
            this.f586b = asVar;
        }

        public boolean mo1731a(boolean z) {
            if (System.currentTimeMillis() - this.f586b.f686c >= 10800000) {
                return true;
            }
            return false;
        }
    }

    public static boolean m31a(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                return true;
            default:
                return false;
        }
    }
}
