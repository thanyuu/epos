package p000u.aly;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.C0291a;
import com.umeng.analytics.C0292b;
import com.umeng.analytics.ReportPolicy;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/* compiled from: ImprintHandler */
public class C0384x {
    private static final String f557a = ".imprint";
    private static final byte[] f558b = "pbl0".getBytes();
    private static C0384x f559f;
    private ao f560c;
    private C0383a f561d = new C0383a();
    private bd f562e = null;
    private Context f563g;

    /* compiled from: ImprintHandler */
    public static class C0383a {
        private int f544a = -1;
        private int f545b = -1;
        private int f546c = -1;
        private int f547d = -1;
        private int f548e = -1;
        private String f549f = null;
        private int f550g = -1;
        private String f551h = null;
        private int f552i = -1;
        private int f553j = -1;
        private String f554k = null;
        private String f555l = null;
        private String f556m = null;

        C0383a() {
        }

        C0383a(bd bdVar) {
            m676a(bdVar);
        }

        public void m676a(bd bdVar) {
            if (bdVar != null) {
                this.f544a = m671a(bdVar, "defcon");
                this.f545b = m671a(bdVar, au.ah);
                this.f546c = m671a(bdVar, "codex");
                this.f547d = m671a(bdVar, "report_policy");
                this.f548e = m671a(bdVar, "report_interval");
                this.f549f = m672b(bdVar, "client_test");
                this.f550g = m671a(bdVar, "test_report_interval");
                this.f551h = m672b(bdVar, "umid");
                this.f552i = m671a(bdVar, "integrated_test");
                this.f553j = m671a(bdVar, "latent_hours");
                this.f554k = m672b(bdVar, au.f193G);
                this.f555l = m672b(bdVar, "domain_p");
                this.f556m = m672b(bdVar, "domain_s");
            }
        }

        public String m675a(String str) {
            if (this.f556m != null) {
                return this.f556m;
            }
            return str;
        }

        public String m680b(String str) {
            if (this.f555l != null) {
                return this.f555l;
            }
            return str;
        }

        public String m683c(String str) {
            if (this.f554k != null) {
                return this.f554k;
            }
            return str;
        }

        public int m673a(int i) {
            return (this.f544a != -1 && this.f544a <= 3 && this.f544a >= 0) ? this.f544a : i;
        }

        public int m679b(int i) {
            return (this.f545b != -1 && this.f545b >= 0 && this.f545b <= 1800) ? this.f545b * 1000 : i;
        }

        public int m682c(int i) {
            if (this.f546c == 0 || this.f546c == 1 || this.f546c == -1) {
                return this.f546c;
            }
            return i;
        }

        public int[] m678a(int i, int i2) {
            if (this.f547d == -1 || !ReportPolicy.m31a(this.f547d)) {
                return new int[]{i, i2};
            }
            if (this.f548e == -1 || this.f548e < 90 || this.f548e > 86400) {
                this.f548e = 90;
            }
            return new int[]{this.f547d, this.f548e * 1000};
        }

        public String m685d(String str) {
            return (this.f549f == null || !aw.m856a(this.f549f)) ? str : this.f549f;
        }

        public int m684d(int i) {
            return (this.f550g == -1 || this.f550g < 90 || this.f550g > 86400) ? i : this.f550g * 1000;
        }

        public boolean m677a() {
            return this.f550g != -1;
        }

        public String m686e(String str) {
            return this.f551h;
        }

        public boolean m681b() {
            return this.f552i == 1;
        }

        public long m674a(long j) {
            return (this.f553j != -1 && this.f553j >= 48) ? C0291a.f42k * ((long) this.f553j) : j;
        }

        private int m671a(bd bdVar, String str) {
            if (bdVar == null || !bdVar.m1038f()) {
                return -1;
            }
            be beVar = (be) bdVar.m1036d().get(str);
            if (beVar == null || TextUtils.isEmpty(beVar.m1073c())) {
                return -1;
            }
            try {
                return Integer.parseInt(beVar.m1073c().trim());
            } catch (Exception e) {
                return -1;
            }
        }

        private String m672b(bd bdVar, String str) {
            if (bdVar == null || !bdVar.m1038f()) {
                return null;
            }
            be beVar = (be) bdVar.m1036d().get(str);
            if (beVar == null || TextUtils.isEmpty(beVar.m1073c())) {
                return null;
            }
            return beVar.m1073c();
        }
    }

    C0384x(Context context) {
        this.f563g = context;
    }

    public static synchronized C0384x m688a(Context context) {
        C0384x c0384x;
        synchronized (C0384x.class) {
            if (f559f == null) {
                f559f = new C0384x(context);
                f559f.m698c();
            }
            c0384x = f559f;
        }
        return c0384x;
    }

    public void m694a(ao aoVar) {
        this.f560c = aoVar;
    }

    public String m692a(bd bdVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : new TreeMap(bdVar.m1036d()).entrySet()) {
            stringBuilder.append((String) entry.getKey());
            if (((be) entry.getValue()).m1076e()) {
                stringBuilder.append(((be) entry.getValue()).m1073c());
            }
            stringBuilder.append(((be) entry.getValue()).m1077f());
            stringBuilder.append(((be) entry.getValue()).m1080i());
        }
        stringBuilder.append(bdVar.f822b);
        return bk.m303a(stringBuilder.toString()).toLowerCase(Locale.US);
    }

    private boolean m690c(bd bdVar) {
        if (!bdVar.m1042j().equals(m692a(bdVar))) {
            return false;
        }
        for (be beVar : bdVar.m1036d().values()) {
            byte[] a = C0292b.m36a(beVar.m1080i());
            byte[] a2 = m695a(beVar);
            for (int i = 0; i < 4; i++) {
                if (a[i] != a2[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public byte[] m695a(be beVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(beVar.m1077f());
        byte[] array = allocate.array();
        byte[] bArr = f558b;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    public void m697b(bd bdVar) {
        String str = null;
        if (bdVar != null && m690c(bdVar)) {
            Object obj = null;
            synchronized (this) {
                bd d;
                bd bdVar2 = this.f562e;
                String j = bdVar2 == null ? null : bdVar2.m1042j();
                if (bdVar2 == null) {
                    d = m691d(bdVar);
                } else {
                    d = m687a(bdVar2, bdVar);
                }
                this.f562e = d;
                if (d != null) {
                    str = d.m1042j();
                }
                if (!m689a(j, str)) {
                    obj = 1;
                }
            }
            if (this.f562e != null && r0 != null) {
                this.f561d.m676a(this.f562e);
                if (this.f560c != null) {
                    this.f560c.mo1740a(this.f561d);
                }
            }
        }
    }

    private boolean m689a(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 != null) {
            return false;
        }
        return true;
    }

    private bd m687a(bd bdVar, bd bdVar2) {
        if (bdVar2 != null) {
            Map d = bdVar.m1036d();
            for (Entry entry : bdVar2.m1036d().entrySet()) {
                if (((be) entry.getValue()).m1076e()) {
                    d.put(entry.getKey(), entry.getValue());
                } else {
                    d.remove(entry.getKey());
                }
            }
            bdVar.m1023a(bdVar2.m1039g());
            bdVar.m1024a(m692a(bdVar));
        }
        return bdVar;
    }

    private bd m691d(bd bdVar) {
        Map d = bdVar.m1036d();
        List<String> arrayList = new ArrayList(d.size() / 2);
        for (Entry entry : d.entrySet()) {
            if (!((be) entry.getValue()).m1076e()) {
                arrayList.add(entry.getKey());
            }
        }
        for (String remove : arrayList) {
            d.remove(remove);
        }
        return bdVar;
    }

    public synchronized bd m693a() {
        return this.f562e;
    }

    public C0383a m696b() {
        return this.f561d;
    }

    public void m698c() {
        InputStream openFileInput;
        Exception e;
        bd bdVar;
        Throwable th;
        InputStream inputStream = null;
        if (new File(this.f563g.getFilesDir(), f557a).exists()) {
            byte[] b;
            try {
                openFileInput = this.f563g.openFileInput(f557a);
                try {
                    b = bk.m311b(openFileInput);
                    bk.m313c(openFileInput);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        bk.m313c(openFileInput);
                        if (b == null) {
                            try {
                                bdVar = new bd();
                                new bs().m400a((bp) bdVar, b);
                                this.f562e = bdVar;
                                this.f561d.m676a(bdVar);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = openFileInput;
                        bk.m313c(inputStream);
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e3 = e4;
                openFileInput = inputStream;
                e3.printStackTrace();
                bk.m313c(openFileInput);
                if (b == null) {
                    bdVar = new bd();
                    new bs().m400a((bp) bdVar, b);
                    this.f562e = bdVar;
                    this.f561d.m676a(bdVar);
                }
            } catch (Throwable th3) {
                th = th3;
                bk.m313c(inputStream);
                throw th;
            }
            if (b == null) {
                bdVar = new bd();
                new bs().m400a((bp) bdVar, b);
                this.f562e = bdVar;
                this.f561d.m676a(bdVar);
            }
        }
    }

    public void m699d() {
        if (this.f562e != null) {
            try {
                bk.m307a(new File(this.f563g.getFilesDir(), f557a), new by().m415a(this.f562e));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean m700e() {
        return new File(this.f563g.getFilesDir(), f557a).delete();
    }
}
