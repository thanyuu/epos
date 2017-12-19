package p000u.aly;

import android.content.Context;
import android.text.TextUtils;
import p000u.aly.C0384x.C0383a;

/* compiled from: ABTest */
public class aw implements ao {
    private static aw f714h = null;
    private boolean f715a = false;
    private int f716b = -1;
    private int f717c = -1;
    private int f718d = -1;
    private float f719e = 0.0f;
    private float f720f = 0.0f;
    private Context f721g = null;

    public static synchronized aw m855a(Context context) {
        aw awVar;
        synchronized (aw.class) {
            if (f714h == null) {
                C0383a b = C0384x.m688a(context).m696b();
                f714h = new aw(context, b.m685d(null), b.m684d(0));
            }
            awVar = f714h;
        }
        return awVar;
    }

    private aw(Context context, String str, int i) {
        this.f721g = context;
        m860a(str, i);
    }

    private float m857b(String str, int i) {
        int i2 = i * 2;
        if (str == null) {
            return 0.0f;
        }
        return ((float) Integer.valueOf(str.substring(i2, i2 + 5), 16).intValue()) / 1048576.0f;
    }

    public void m860a(String str, int i) {
        this.f717c = i;
        String a = C0380t.m641a(this.f721g);
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(str)) {
            this.f715a = false;
            return;
        }
        try {
            this.f719e = m857b(a, 12);
            this.f720f = m857b(a, 6);
            if (str.startsWith("SIG7")) {
                m858b(str);
            } else if (str.startsWith("FIXED")) {
                m859c(str);
            }
        } catch (Throwable e) {
            this.f715a = false;
            bl.m342e("v:" + str, e);
        }
    }

    public static boolean m856a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\|");
        if (split.length != 6) {
            return false;
        }
        if (split[0].startsWith("SIG7") && split[1].split(",").length == split[5].split(",").length) {
            return true;
        }
        if (!split[0].startsWith("FIXED")) {
            return false;
        }
        int length = split[5].split(",").length;
        int parseInt = Integer.parseInt(split[1]);
        if (length < parseInt || parseInt < 1) {
            return false;
        }
        return true;
    }

    private void m858b(String str) {
        if (str != null) {
            float floatValue;
            String[] split = str.split("\\|");
            if (split[2].equals("SIG13")) {
                floatValue = Float.valueOf(split[3]).floatValue();
            } else {
                floatValue = 0.0f;
            }
            if (this.f719e > floatValue) {
                this.f715a = false;
                return;
            }
            String[] split2;
            float[] fArr = null;
            if (split[0].equals("SIG7")) {
                split2 = split[1].split(",");
                float[] fArr2 = new float[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    fArr2[i] = Float.valueOf(split2[i]).floatValue();
                }
                fArr = fArr2;
            }
            int[] iArr = null;
            if (split[4].equals("RPT")) {
                split2 = split[5].split(",");
                int[] iArr2 = new int[split2.length];
                for (int i2 = 0; i2 < split2.length; i2++) {
                    iArr2[i2] = Integer.valueOf(split2[i2]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                if (bj.m289q(this.f721g)) {
                    this.f715a = false;
                    return;
                }
                try {
                    split2 = split[5].split(",");
                    iArr = new int[split2.length];
                    for (int i3 = 0; i3 < split2.length; i3++) {
                        iArr[i3] = Integer.valueOf(split2[i3]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            float f = 0.0f;
            int i4 = 0;
            while (i4 < fArr.length) {
                f += fArr[i4];
                if (this.f720f < f) {
                    break;
                }
                i4++;
            }
            i4 = -1;
            if (i4 != -1) {
                this.f715a = true;
                this.f718d = i4 + 1;
                if (iArr != null) {
                    this.f716b = iArr[i4];
                    return;
                }
                return;
            }
            this.f715a = false;
        }
    }

    private void m859c(String str) {
        if (str != null) {
            String[] split = str.split("\\|");
            float f = 0.0f;
            if (split[2].equals("SIG13")) {
                f = Float.valueOf(split[3]).floatValue();
            }
            if (this.f719e > f) {
                this.f715a = false;
                return;
            }
            int intValue;
            if (split[0].equals("FIXED")) {
                intValue = Integer.valueOf(split[1]).intValue();
            } else {
                intValue = -1;
            }
            int[] iArr = null;
            String[] split2;
            if (split[4].equals("RPT")) {
                split2 = split[5].split(",");
                int[] iArr2 = new int[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    iArr2[i] = Integer.valueOf(split2[i]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                if (bj.m289q(this.f721g)) {
                    this.f715a = false;
                    return;
                }
                try {
                    split2 = split[5].split(",");
                    iArr = new int[split2.length];
                    for (int i2 = 0; i2 < split2.length; i2++) {
                        iArr[i2] = Integer.valueOf(split2[i2]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            if (intValue != -1) {
                this.f715a = true;
                this.f718d = intValue;
                if (iArr != null) {
                    this.f716b = iArr[intValue - 1];
                    return;
                }
                return;
            }
            this.f715a = false;
        }
    }

    public boolean m863a() {
        return this.f715a;
    }

    public int m864b() {
        return this.f716b;
    }

    public int m865c() {
        return this.f717c;
    }

    public int m866d() {
        return this.f718d;
    }

    public void m861a(av avVar) {
        if (this.f715a) {
            avVar.f335b.f282f.put("client_test", Integer.valueOf(this.f718d));
        }
    }

    public void mo1740a(C0383a c0383a) {
        m860a(c0383a.m685d(null), c0383a.m684d(0));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" p13:");
        stringBuilder.append(this.f719e);
        stringBuilder.append(" p07:");
        stringBuilder.append(this.f720f);
        stringBuilder.append(" policy:");
        stringBuilder.append(this.f716b);
        stringBuilder.append(" interval:");
        stringBuilder.append(this.f717c);
        return stringBuilder.toString();
    }
}
