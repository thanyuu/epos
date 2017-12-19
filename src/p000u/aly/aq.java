package p000u.aly;

import android.content.Context;
import android.util.Base64;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C0292b;
import com.umeng.analytics.C0306h;
import com.umeng.analytics.C0306h.C0305b;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import p000u.aly.ch.C0649a;

/* compiled from: Sender */
public class aq {
    private static final int f162a = 1;
    private static final int f163b = 2;
    private static final int f164c = 3;
    private static Context f165g;
    private C0382v f166d;
    private C0384x f167e;
    private final int f168f = 1;
    private as f169h;
    private al f170i;
    private av f171j;
    private boolean f172k = false;
    private boolean f173l;

    /* compiled from: Sender */
    class C06221 implements C0305b {
        final /* synthetic */ aq f677a;

        C06221(aq aqVar) {
            this.f677a = aqVar;
        }

        public void mo1744a(File file) {
        }

        public boolean mo1745b(File file) {
            Throwable th;
            InputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] b = bk.m311b(fileInputStream);
                    try {
                        int i;
                        bk.m313c(fileInputStream);
                        byte[] a = this.f677a.f170i.m190a(b);
                        if (a == null) {
                            i = 1;
                        } else {
                            i = this.f677a.m200a(a);
                        }
                        if (i == 2 && this.f677a.f169h.m849m()) {
                            this.f677a.f169h.m848l();
                        }
                        if (!this.f677a.f173l && i == 1) {
                            return false;
                        }
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bk.m313c(fileInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                bk.m313c(fileInputStream);
                throw th;
            }
        }

        public void mo1746c(File file) {
            this.f677a.f169h.m847k();
        }
    }

    public aq(Context context, as asVar) {
        this.f166d = C0382v.m659a(context);
        this.f167e = C0384x.m688a(context);
        f165g = context;
        this.f169h = asVar;
        this.f170i = new al(context);
        this.f170i.m189a(this.f169h);
    }

    public void m208a(av avVar) {
        this.f171j = avVar;
    }

    public void m209a(boolean z) {
        this.f172k = z;
    }

    public void m210b(boolean z) {
        this.f173l = z;
    }

    public void m207a(ao aoVar) {
        this.f167e.m694a(aoVar);
    }

    public void m206a() {
        if (this.f171j != null) {
            m204c();
        } else {
            m203b();
        }
    }

    private void m203b() {
        C0306h.m71a(f165g).m98j().m63a(new C06221(this));
    }

    private void m204c() {
        byte[] a;
        this.f166d.m663a();
        av avVar = this.f171j;
        try {
            a = new by().m415a(this.f166d.m666b());
            avVar.f334a.f302O = Base64.encodeToString(a, 0);
        } catch (Throwable e) {
            bl.m344e(e);
        }
        a = C0306h.m71a(f165g).m89b(avVar);
        if (!C0292b.m35a(f165g, a)) {
            if (a == null) {
                bl.m340e("message is null");
                return;
            }
            C0380t b;
            int i;
            if (this.f172k) {
                b = C0380t.m644b(f165g, AnalyticsConfig.getAppkey(f165g), a);
            } else {
                b = C0380t.m642a(f165g, AnalyticsConfig.getAppkey(f165g), a);
            }
            byte[] c = b.m653c();
            C0306h.m71a(f165g).m96h();
            a = this.f170i.m190a(c);
            if (a == null) {
                i = 1;
            } else {
                i = m200a(a);
            }
            switch (i) {
                case 1:
                    if (!this.f173l) {
                        C0306h.m71a(f165g).m85a(c);
                        return;
                    }
                    return;
                case 2:
                    if (this.f169h.m849m()) {
                        this.f169h.m848l();
                    }
                    this.f166d.m668d();
                    this.f169h.m847k();
                    av.f332c = 0;
                    return;
                case 3:
                    this.f169h.m847k();
                    return;
                default:
                    return;
            }
        }
    }

    private int m200a(byte[] bArr) {
        bp bfVar = new bf();
        try {
            new bs(new C0649a()).m400a(bfVar, bArr);
            if (bfVar.f858a == 1) {
                this.f167e.m697b(bfVar.m1118i());
                this.f167e.m699d();
            }
            bl.m328c("send log:" + bfVar.m1115f());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bfVar.f858a == 1) {
            return 2;
        }
        return 3;
    }
}
