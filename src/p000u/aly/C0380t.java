package p000u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.C0292b;
import java.io.File;
import org.json.JSONObject;

/* compiled from: Envelope */
public class C0380t {
    private final byte[] f520a = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
    private final int f521b = 1;
    private final int f522c = 0;
    private String f523d = "1.0";
    private String f524e = null;
    private byte[] f525f = null;
    private byte[] f526g = null;
    private byte[] f527h = null;
    private int f528i = 0;
    private int f529j = 0;
    private int f530k = 0;
    private byte[] f531l = null;
    private byte[] f532m = null;
    private boolean f533n = false;

    private C0380t(byte[] bArr, String str, byte[] bArr2) throws Exception {
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.f524e = str;
        this.f530k = bArr.length;
        this.f531l = bi.m244a(bArr);
        this.f529j = (int) (System.currentTimeMillis() / 1000);
        this.f532m = bArr2;
    }

    public static String m641a(Context context) {
        SharedPreferences a = ap.m197a(context);
        if (a == null) {
            return null;
        }
        return a.getString("signature", null);
    }

    public void m649a(String str) {
        this.f525f = C0292b.m36a(str);
    }

    public String m647a() {
        return C0292b.m34a(this.f525f);
    }

    public void m648a(int i) {
        this.f528i = i;
    }

    public void m650a(boolean z) {
        this.f533n = z;
    }

    public static C0380t m642a(Context context, String str, byte[] bArr) {
        try {
            String u = bj.m293u(context);
            String f = bj.m277f(context);
            SharedPreferences a = ap.m197a(context);
            String string = a.getString("signature", null);
            int i = a.getInt("serial", 1);
            C0380t c0380t = new C0380t(bArr, str, (f + u).getBytes());
            c0380t.m649a(string);
            c0380t.m648a(i);
            c0380t.m651b();
            a.edit().putInt("serial", i + 1).putString("signature", c0380t.m647a()).commit();
            c0380t.m652b(context);
            return c0380t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static C0380t m644b(Context context, String str, byte[] bArr) {
        try {
            String u = bj.m293u(context);
            String f = bj.m277f(context);
            SharedPreferences a = ap.m197a(context);
            String string = a.getString("signature", null);
            int i = a.getInt("serial", 1);
            C0380t c0380t = new C0380t(bArr, str, (f + u).getBytes());
            c0380t.m650a(true);
            c0380t.m649a(string);
            c0380t.m648a(i);
            c0380t.m651b();
            a.edit().putInt("serial", i + 1).putString("signature", c0380t.m647a()).commit();
            c0380t.m652b(context);
            return c0380t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void m651b() {
        if (this.f525f == null) {
            this.f525f = m645d();
        }
        if (this.f533n) {
            byte[] bArr = new byte[16];
            try {
                System.arraycopy(this.f525f, 1, bArr, 0, 16);
                this.f531l = C0292b.m37a(this.f531l, bArr);
            } catch (Exception e) {
            }
        }
        this.f526g = m643a(this.f525f, this.f529j);
        this.f527h = m646e();
    }

    private byte[] m643a(byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        byte[] b = C0292b.m39b(this.f532m);
        byte[] b2 = C0292b.m39b(this.f531l);
        int length = b.length;
        byte[] bArr2 = new byte[(length * 2)];
        for (i2 = 0; i2 < length; i2++) {
            bArr2[i2 * 2] = b2[i2];
            bArr2[(i2 * 2) + 1] = b[i2];
        }
        for (i2 = 0; i2 < 2; i2++) {
            bArr2[i2] = bArr[i2];
            bArr2[(bArr2.length - i2) - 1] = bArr[(bArr.length - i2) - 1];
        }
        byte[] bArr3 = new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
        while (i3 < bArr2.length) {
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr3[i3 % 4]);
            i3++;
        }
        return bArr2;
    }

    private byte[] m645d() {
        return m643a(this.f520a, (int) (System.currentTimeMillis() / 1000));
    }

    private byte[] m646e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(C0292b.m34a(this.f525f));
        stringBuilder.append(this.f528i);
        stringBuilder.append(this.f529j);
        stringBuilder.append(this.f530k);
        stringBuilder.append(C0292b.m34a(this.f526g));
        return C0292b.m39b(stringBuilder.toString().getBytes());
    }

    public byte[] m653c() {
        bp bgVar = new bg();
        bgVar.m1156a(this.f523d);
        bgVar.m1161b(this.f524e);
        bgVar.m1168c(C0292b.m34a(this.f525f));
        bgVar.m1155a(this.f528i);
        bgVar.m1167c(this.f529j);
        bgVar.m1170d(this.f530k);
        bgVar.m1158a(this.f531l);
        bgVar.m1174e(this.f533n ? 1 : 0);
        bgVar.m1171d(C0292b.m34a(this.f526g));
        bgVar.m1175e(C0292b.m34a(this.f527h));
        try {
            return new by().m415a(bgVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void m652b(Context context) {
        String str = this.f524e;
        String e = C0384x.m688a(context).m696b().m686e(null);
        String a = C0292b.m34a(this.f525f);
        byte[] bArr = new byte[16];
        System.arraycopy(this.f525f, 2, bArr, 0, 16);
        String a2 = C0292b.m34a(C0292b.m39b(bArr));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(au.f213a, str);
            if (e != null) {
                jSONObject.put("umid", e);
            }
            jSONObject.put("signature", a);
            jSONObject.put("checksum", a2);
            str = jSONObject.toString();
            File file = new File(context.getFilesDir(), ".umeng");
            if (!file.exists()) {
                file.mkdir();
            }
            bk.m306a(new File(file, "exchangeIdentity.json"), str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String toString() {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("version : %s\n", new Object[]{this.f523d}));
        stringBuilder.append(String.format("address : %s\n", new Object[]{this.f524e}));
        stringBuilder.append(String.format("signature : %s\n", new Object[]{C0292b.m34a(this.f525f)}));
        stringBuilder.append(String.format("serial : %s\n", new Object[]{Integer.valueOf(this.f528i)}));
        stringBuilder.append(String.format("timestamp : %d\n", new Object[]{Integer.valueOf(this.f529j)}));
        stringBuilder.append(String.format("length : %d\n", new Object[]{Integer.valueOf(this.f530k)}));
        stringBuilder.append(String.format("guid : %s\n", new Object[]{C0292b.m34a(this.f526g)}));
        stringBuilder.append(String.format("checksum : %s ", new Object[]{C0292b.m34a(this.f527h)}));
        String str = "codex : %d";
        Object[] objArr = new Object[1];
        if (!this.f533n) {
            i = 0;
        }
        objArr[0] = Integer.valueOf(i);
        stringBuilder.append(String.format(str, objArr));
        return stringBuilder.toString();
    }
}
