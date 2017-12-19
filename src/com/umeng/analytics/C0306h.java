package com.umeng.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;
import p000u.aly.C0370m;
import p000u.aly.C0652f;
import p000u.aly.ap;
import p000u.aly.au;
import p000u.aly.av;
import p000u.aly.av.C0322e;
import p000u.aly.av.C0323f;
import p000u.aly.av.C0325h;
import p000u.aly.av.C0327l;
import p000u.aly.av.C0623i;
import p000u.aly.av.C0624j;
import p000u.aly.av.C0625o;
import p000u.aly.bj;
import p000u.aly.bk;
import p000u.aly.bl;

/* compiled from: StoreHelper */
public final class C0306h {
    private static C0306h f89a = null;
    private static Context f90b = null;
    private static String f91c = null;
    private static long f92e = 1209600000;
    private static long f93f = 2097152;
    private static final String f94g = "mobclick_agent_user_";
    private static final String f95h = "mobclick_agent_header_";
    private static final String f96i = "mobclick_agent_update_";
    private static final String f97j = "mobclick_agent_state_";
    private static final String f98k = "mobclick_agent_cached_";
    private C0304a f99d;

    /* compiled from: StoreHelper */
    public static class C0304a {
        private final int f86a;
        private File f87b;
        private FilenameFilter f88c;

        /* compiled from: StoreHelper */
        class C03032 implements FilenameFilter {
            final /* synthetic */ C0304a f85a;

            C03032(C0304a c0304a) {
                this.f85a = c0304a;
            }

            public boolean accept(File file, String str) {
                return str.startsWith("um");
            }
        }

        public C0304a(Context context) {
            this(context, ".um");
        }

        public C0304a(Context context, String str) {
            this.f86a = 10;
            this.f88c = new C03032(this);
            this.f87b = new File(context.getFilesDir(), str);
            if (!this.f87b.exists() || !this.f87b.isDirectory()) {
                this.f87b.mkdir();
            }
        }

        public boolean m65a() {
            File[] listFiles = this.f87b.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return false;
            }
            return true;
        }

        public void m63a(C0305b c0305b) {
            int i;
            int i2 = 0;
            File[] listFiles = this.f87b.listFiles(this.f88c);
            if (listFiles != null && listFiles.length >= 10) {
                Arrays.sort(listFiles);
                final int length = listFiles.length - 10;
                C0295f.m49b(new Runnable(this) {
                    final /* synthetic */ C0304a f84b;

                    public void run() {
                        if (length > 0) {
                            C0370m.m556a(C0306h.f90b).m581a((long) length, System.currentTimeMillis(), C0291a.f51t);
                        }
                    }
                });
                for (i = 0; i < length; i++) {
                    listFiles[i].delete();
                }
            }
            if (listFiles != null && listFiles.length > 0) {
                c0305b.mo1744a(this.f87b);
                i = listFiles.length;
                while (i2 < i) {
                    try {
                        if (c0305b.mo1745b(listFiles[i2])) {
                            listFiles[i2].delete();
                        }
                    } catch (Throwable th) {
                        listFiles[i2].delete();
                    }
                    i2++;
                }
                c0305b.mo1746c(this.f87b);
            }
        }

        public void m64a(byte[] bArr) {
            if (bArr != null && bArr.length != 0) {
                try {
                    bk.m307a(new File(this.f87b, String.format(Locale.US, "um_cache_%d.env", new Object[]{Long.valueOf(System.currentTimeMillis())})), bArr);
                } catch (Exception e) {
                }
            }
        }

        public void m66b() {
            File[] listFiles = this.f87b.listFiles(this.f88c);
            if (listFiles != null && listFiles.length > 0) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }

        public int m67c() {
            File[] listFiles = this.f87b.listFiles(this.f88c);
            if (listFiles == null || listFiles.length <= 0) {
                return 0;
            }
            return listFiles.length;
        }
    }

    /* compiled from: StoreHelper */
    public interface C0305b {
        void mo1744a(File file);

        boolean mo1745b(File file);

        void mo1746c(File file);
    }

    /* compiled from: StoreHelper */
    class C06821 extends C0652f {
        final /* synthetic */ C0306h f999a;

        C06821(C0306h c0306h) {
            this.f999a = c0306h;
        }

        public void mo1814a(Object obj, boolean z) {
            if (!obj.equals("success")) {
            }
        }
    }

    public C0306h(Context context) {
        this.f99d = new C0304a(context);
    }

    public static synchronized C0306h m71a(Context context) {
        C0306h c0306h;
        synchronized (C0306h.class) {
            f90b = context.getApplicationContext();
            f91c = context.getPackageName();
            if (f89a == null) {
                f89a = new C0306h(context);
            }
            c0306h = f89a;
        }
        return c0306h;
    }

    private static boolean m74a(File file) {
        long length = file.length();
        if (!file.exists() || length <= f93f) {
            return false;
        }
        C0370m.m556a(f90b).m581a(length, System.currentTimeMillis(), C0291a.f50s);
        return true;
    }

    public void m83a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Editor edit = m78o().edit();
            edit.putString("au_p", str);
            edit.putString("au_u", str2);
            edit.commit();
        }
    }

    public String[] m86a() {
        SharedPreferences o = m78o();
        String string = o.getString("au_p", null);
        String string2 = o.getString("au_u", null);
        if (string == null || string2 == null) {
            return null;
        }
        return new String[]{string, string2};
    }

    public void m87b() {
        m78o().edit().remove("au_p").remove("au_u").commit();
    }

    String m90c() {
        SharedPreferences a = ap.m197a(f90b);
        if (a != null) {
            return a.getString(au.f213a, null);
        }
        return null;
    }

    void m82a(String str) {
        SharedPreferences a = ap.m197a(f90b);
        if (a != null) {
            a.edit().putString(au.f213a, str).commit();
        }
    }

    String m92d() {
        SharedPreferences a = ap.m197a(f90b);
        if (a != null) {
            return a.getString(au.f214b, null);
        }
        return null;
    }

    void m88b(String str) {
        SharedPreferences a = ap.m197a(f90b);
        if (a != null) {
            a.edit().putString(au.f214b, str).commit();
        }
    }

    String m93e() {
        SharedPreferences a = ap.m197a(f90b);
        if (a != null) {
            return a.getString("st", null);
        }
        return null;
    }

    void m91c(String str) {
        SharedPreferences a = ap.m197a(f90b);
        if (a != null) {
            a.edit().putString("st", str).commit();
        }
    }

    void m81a(int i) {
        SharedPreferences a = ap.m197a(f90b);
        if (a != null) {
            a.edit().putInt("vt", i).commit();
        }
    }

    int m94f() {
        SharedPreferences a = ap.m197a(f90b);
        if (a != null) {
            return a.getInt("vt", 0);
        }
        return 0;
    }

    public av m95g() {
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        Exception e;
        av avVar;
        Throwable th;
        Throwable e2;
        try {
            File file = new File(f90b.getApplicationContext().getFilesDir().getAbsolutePath(), m80q());
            if (C0306h.m74a(file)) {
                file.delete();
                return null;
            } else if (!file.exists()) {
                return null;
            } else {
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        objectInputStream = new ObjectInputStream(fileInputStream);
                    } catch (Exception e3) {
                        e = e3;
                        objectInputStream = null;
                        try {
                            e.printStackTrace();
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                    avVar = null;
                                } catch (IOException e42) {
                                    e42.printStackTrace();
                                    avVar = null;
                                }
                            } else {
                                avVar = null;
                            }
                            return avVar;
                        } catch (Throwable th2) {
                            th = th2;
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e52) {
                                    e52.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        objectInputStream = null;
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                    try {
                        avVar = (av) objectInputStream.readObject();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e6) {
                                try {
                                    e6.printStackTrace();
                                } catch (Exception e7) {
                                    e2 = e7;
                                    if (bl.f355a) {
                                        bl.m344e(e2);
                                    }
                                    return avVar;
                                }
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e62) {
                                e62.printStackTrace();
                            }
                        }
                    } catch (Exception e8) {
                        e = e8;
                        e.printStackTrace();
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        if (fileInputStream != null) {
                            avVar = null;
                        } else {
                            fileInputStream.close();
                            avVar = null;
                        }
                        return avVar;
                    }
                } catch (Exception e9) {
                    e = e9;
                    objectInputStream = null;
                    fileInputStream = null;
                    e.printStackTrace();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                        avVar = null;
                    } else {
                        avVar = null;
                    }
                    return avVar;
                } catch (Throwable th4) {
                    th = th4;
                    objectInputStream = null;
                    fileInputStream = null;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
                return avVar;
            }
        } catch (Throwable th5) {
            Throwable th6 = th5;
            avVar = null;
            e2 = th6;
            if (bl.f355a) {
                bl.m344e(e2);
            }
            return avVar;
        }
    }

    public void m84a(av avVar) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        Throwable e;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(new File(f90b.getApplicationContext().getFilesDir().getAbsolutePath(), m80q()));
            try {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            } catch (Exception e2) {
                e = e2;
                objectOutputStream = null;
                fileOutputStream2 = fileOutputStream;
                try {
                    bl.m344e(e);
                    e.printStackTrace();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (fileOutputStream2 == null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                            return;
                        }
                    }
                } catch (Throwable th) {
                    e = th;
                    fileOutputStream = fileOutputStream2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                objectOutputStream = null;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw e;
            }
            try {
                objectOutputStream.writeObject(avVar);
                objectOutputStream.flush();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3222) {
                        e3222.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream2 = fileOutputStream;
                bl.m344e(e);
                e.printStackTrace();
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream2 == null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th3) {
                e = th3;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw e;
            }
        } catch (Exception e6) {
            e = e6;
            objectOutputStream = null;
            bl.m344e(e);
            e.printStackTrace();
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (fileOutputStream2 == null) {
                fileOutputStream2.close();
            }
        } catch (Throwable th4) {
            e = th4;
            objectOutputStream = null;
            fileOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw e;
        }
    }

    public void m96h() {
        f90b.deleteFile(m79p());
        f90b.deleteFile(m80q());
        C0370m.m556a(f90b).m591d(new C06821(this));
    }

    public void m85a(byte[] bArr) {
        this.f99d.m64a(bArr);
    }

    public boolean m97i() {
        return this.f99d.m65a();
    }

    public C0304a m98j() {
        return this.f99d;
    }

    private SharedPreferences m78o() {
        return f90b.getSharedPreferences(f94g + f91c, 0);
    }

    public SharedPreferences m99k() {
        return f90b.getSharedPreferences(f95h + f91c, 0);
    }

    public SharedPreferences m100l() {
        return f90b.getSharedPreferences(f96i + f91c, 0);
    }

    public SharedPreferences m101m() {
        return f90b.getSharedPreferences(f97j + f91c, 0);
    }

    private String m79p() {
        return f95h + f91c;
    }

    private String m80q() {
        SharedPreferences a = ap.m197a(f90b);
        if (a == null) {
            return f98k + f91c + bj.m272c(f90b);
        }
        int i = a.getInt(C0291a.f56y, 0);
        int parseInt = Integer.parseInt(bj.m272c(f90b));
        if (i == 0 || parseInt == i) {
            return f98k + f91c + bj.m272c(f90b);
        }
        return f98k + f91c + i;
    }

    public byte[] m89b(final av avVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            final StringBuilder stringBuilder = new StringBuilder();
            jSONObject.put(C0291a.f55x, new JSONObject(this) {
                final /* synthetic */ C0306h f79c;
            });
            JSONObject c03013 = new JSONObject(this) {
                final /* synthetic */ C0306h f82c;
            };
            if (c03013.length() > 0) {
                jSONObject.put(C0291a.f54w, c03013);
            }
            bl.m322b("serialize entry:" + String.valueOf(stringBuilder));
            return String.valueOf(jSONObject).getBytes();
        } catch (Throwable e) {
            bl.m342e("Fail to serialize log ...", e);
            return null;
        }
    }

    private void m73a(av avVar, JSONObject jSONObject, StringBuilder stringBuilder) throws Exception {
        jSONObject.put(au.f213a, avVar.f334a.f303a);
        jSONObject.put(au.f214b, avVar.f334a.f304b);
        if (avVar.f334a.f305c != null) {
            jSONObject.put(au.f215c, avVar.f334a.f305c);
        }
        jSONObject.put(au.f216d, avVar.f334a.f306d);
        jSONObject.put(au.f219g, avVar.f334a.f309g);
        jSONObject.put(au.f217e, avVar.f334a.f307e);
        jSONObject.put(au.f218f, avVar.f334a.f308f);
        jSONObject.put(au.f220h, avVar.f334a.f310h);
        jSONObject.put(au.f221i, avVar.f334a.f311i);
        jSONObject.put(au.f222j, avVar.f334a.f312j);
        jSONObject.put(au.f223k, avVar.f334a.f313k);
        jSONObject.put(au.f224l, avVar.f334a.f314l);
        jSONObject.put(au.f225m, avVar.f334a.f315m);
        jSONObject.put(au.f226n, avVar.f334a.f316n);
        jSONObject.put(au.f227o, avVar.f334a.f317o);
        jSONObject.put(au.f228p, avVar.f334a.f318p);
        jSONObject.put(au.f229q, avVar.f334a.f319q);
        jSONObject.put(au.f230r, avVar.f334a.f320r);
        jSONObject.put(au.f231s, avVar.f334a.f321s);
        jSONObject.put(au.f233u, avVar.f334a.f322t);
        jSONObject.put(au.f234v, avVar.f334a.f323u);
        jSONObject.put(au.f235w, avVar.f334a.f324v);
        jSONObject.put(au.f236x, avVar.f334a.f325w);
        jSONObject.put(au.f237y, avVar.f334a.f326x);
        jSONObject.put(au.f238z, avVar.f334a.f327y);
        jSONObject.put(au.f187A, avVar.f334a.f328z);
        jSONObject.put(au.f188B, avVar.f334a.f288A);
        if (avVar.f334a.f289B != null) {
            jSONObject.put(au.f189C, avVar.f334a.f289B);
        }
        if (avVar.f334a.f290C != null) {
            jSONObject.put(au.f190D, avVar.f334a.f290C);
        }
        jSONObject.put(au.f191E, avVar.f334a.f291D);
        jSONObject.put(au.f192F, avVar.f334a.f292E);
        jSONObject.put(au.f193G, avVar.f334a.f293F);
        jSONObject.put(au.f194H, avVar.f334a.f294G);
        jSONObject.put(au.f195I, avVar.f334a.f295H);
        jSONObject.put(au.f196J, avVar.f334a.f296I);
        jSONObject.put(au.f232t, avVar.f334a.f297J == null ? "" : avVar.f334a.f297J);
        jSONObject.put(au.f197K, avVar.f334a.f298K);
        jSONObject.put(au.f198L, avVar.f334a.f299L);
        jSONObject.put(au.f199M, avVar.f334a.f300M);
        jSONObject.put(au.f200N, avVar.f334a.f301N);
        jSONObject.put(au.f201O, avVar.f334a.f302O);
        stringBuilder.append("sdk_version:").append(avVar.f334a.f314l).append(";device_id:").append(avVar.f334a.f322t).append(";device_manufacturer:").append(avVar.f334a.f327y).append(";device_board:").append(avVar.f334a.f324v).append(";device_brand:").append(avVar.f334a.f325w).append(";os_version:").append(avVar.f334a.f319q);
    }

    private void m76b(av avVar, JSONObject jSONObject, StringBuilder stringBuilder) throws Exception {
        JSONObject jSONObject2;
        List list;
        JSONArray jSONArray;
        int i;
        JSONArray jSONArray2;
        C0325h c0325h;
        JSONArray jSONArray3;
        int i2;
        JSONObject jSONObject3;
        Object value;
        JSONObject jSONObject4;
        JSONObject jSONObject5 = new JSONObject();
        if (!(avVar.f335b.f284h == null || avVar.f335b.f284h.f248a == null || avVar.f335b.f284h.f248a.size() <= 0)) {
            jSONObject2 = new JSONObject();
            for (Entry entry : avVar.f335b.f284h.f248a.entrySet()) {
                String str = (String) entry.getKey();
                list = (List) entry.getValue();
                jSONArray = new JSONArray();
                for (i = 0; i < list.size(); i++) {
                    C0322e c0322e = (C0322e) list.get(i);
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject6.put(au.ar, c0322e.f251a);
                    jSONObject6.put(au.as, c0322e.f252b);
                    jSONObject6.put(au.at, c0322e.f253c);
                    jSONObject6.put("count", c0322e.f254d);
                    jSONObject6.put(au.av, new JSONArray(c0322e.f255e));
                    jSONArray.put(jSONObject6);
                }
                jSONObject2.put(str, jSONArray);
            }
            jSONObject5.put(au.aq, jSONObject2);
        }
        if (!(avVar.f335b.f284h == null || avVar.f335b.f284h.f249b == null || avVar.f335b.f284h.f249b.size() <= 0)) {
            jSONObject2 = new JSONObject();
            for (Entry entry2 : avVar.f335b.f284h.f249b.entrySet()) {
                str = (String) entry2.getKey();
                list = (List) entry2.getValue();
                jSONArray = new JSONArray();
                for (i = 0; i < list.size(); i++) {
                    C0323f c0323f = (C0323f) list.get(i);
                    jSONObject6 = new JSONObject();
                    jSONObject6.put("value", c0323f.f257a);
                    jSONObject6.put("ts", c0323f.f258b);
                    jSONObject6.put("label", c0323f.f259c);
                    jSONArray.put(jSONObject6);
                }
                jSONObject2.put(str, jSONArray);
            }
            jSONObject5.put(au.aw, jSONObject2);
        }
        if (jSONObject5 != null && jSONObject5.length() > 0) {
            jSONObject.put(au.ap, jSONObject5);
            stringBuilder.append("; cc: ").append(jSONObject5.toString());
        }
        if (avVar.f335b.f277a != null && avVar.f335b.f277a.size() > 0) {
            jSONArray2 = new JSONArray();
            for (i = 0; i < avVar.f335b.f277a.size(); i++) {
                c0325h = (C0325h) avVar.f335b.f277a.get(i);
                jSONArray3 = new JSONArray();
                for (i2 = 0; i2 < c0325h.f266b.size(); i2++) {
                    jSONObject3 = new JSONObject();
                    C0624j c0624j = (C0624j) c0325h.f266b.get(i2);
                    jSONObject3.put("id", c0624j.f699c);
                    jSONObject3.put("ts", c0624j.f700d);
                    jSONObject3.put(au.aI, c0624j.f701e);
                    for (Entry entry3 : c0624j.f702f.entrySet()) {
                        value = entry3.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject3.put((String) entry3.getKey(), entry3.getValue());
                        }
                    }
                    jSONArray3.put(jSONObject3);
                }
                if (!(c0325h.f265a == null || jSONArray3 == null || jSONArray3.length() <= 0)) {
                    JSONObject jSONObject7 = new JSONObject();
                    jSONObject7.put(c0325h.f265a, jSONArray3);
                    jSONArray2.put(jSONObject7);
                }
            }
            if (jSONArray2 != null && jSONArray2.length() > 0) {
                jSONObject.put(au.aE, jSONArray2);
                stringBuilder.append(";ekv:").append(jSONArray2.toString());
            }
        }
        if (avVar.f335b.f278b != null && avVar.f335b.f278b.size() > 0) {
            jSONArray2 = new JSONArray();
            for (i = 0; i < avVar.f335b.f278b.size(); i++) {
                c0325h = (C0325h) avVar.f335b.f278b.get(i);
                jSONArray3 = new JSONArray();
                for (i2 = 0; i2 < c0325h.f266b.size(); i2++) {
                    c0624j = (C0624j) c0325h.f266b.get(i2);
                    jSONObject3 = new JSONObject();
                    jSONObject3.put("id", c0624j.f699c);
                    jSONObject3.put("ts", c0624j.f700d);
                    jSONObject3.put(au.aI, c0624j.f701e);
                    for (Entry entry32 : c0624j.f702f.entrySet()) {
                        value = entry32.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject3.put((String) entry32.getKey(), entry32.getValue());
                        }
                    }
                    jSONArray3.put(jSONObject3);
                }
                if (!(c0325h.f265a == null || jSONArray3 == null || jSONArray3.length() <= 0)) {
                    jSONObject7 = new JSONObject();
                    jSONObject7.put(c0325h.f265a, jSONArray3);
                    jSONArray2.put(jSONObject7);
                }
            }
            if (jSONArray2 != null && jSONArray2.length() > 0) {
                jSONObject.put(au.aF, jSONArray2);
                stringBuilder.append("; gkv:").append(jSONArray2.toString());
            }
        }
        if (avVar.f335b.f285i != null && avVar.f335b.f285i.size() > 0) {
            JSONArray jSONArray4 = new JSONArray();
            for (int i3 = 0; i3 < avVar.f335b.f285i.size(); i3++) {
                C0623i c0623i = (C0623i) avVar.f335b.f285i.get(i3);
                JSONObject jSONObject8 = new JSONObject();
                jSONObject8.put("ts", c0623i.f693a);
                jSONObject8.put(au.aC, c0623i.f694b);
                jSONObject8.put(au.aD, c0623i.f695c);
                jSONArray4.put(jSONObject8);
            }
            jSONObject.put(au.aA, jSONArray4);
        }
        if (avVar.f335b.f279c != null && avVar.f335b.f279c.size() > 0) {
            JSONArray jSONArray5 = new JSONArray();
            for (int i4 = 0; i4 < avVar.f335b.f279c.size(); i4++) {
                C0625o c0625o = (C0625o) avVar.f335b.f279c.get(i4);
                jSONObject5 = new JSONObject();
                jSONObject5.put("id", c0625o.f706b);
                jSONObject5.put(au.f204R, c0625o.f707c);
                jSONObject5.put(au.f205S, c0625o.f708d);
                jSONObject5.put("duration", c0625o.f709e);
                if (!(c0625o.f712i.f330a == 0 && c0625o.f712i.f331b == 0)) {
                    jSONObject7 = new JSONObject();
                    jSONObject7.put(au.ad, c0625o.f712i.f330a);
                    jSONObject7.put(au.ac, c0625o.f712i.f331b);
                    jSONObject5.put(au.ab, jSONObject7);
                }
                if (c0625o.f711h.size() > 0) {
                    jSONArray2 = new JSONArray();
                    for (C0327l c0327l : c0625o.f711h) {
                        jSONObject3 = new JSONObject();
                        jSONObject3.put(au.f208V, c0327l.f272a);
                        jSONObject3.put("duration", c0327l.f273b);
                        jSONArray2.put(jSONObject3);
                    }
                    jSONObject5.put(au.f207U, jSONArray2);
                }
                if (c0625o.f713j.f270c != 0) {
                    JSONArray jSONArray6 = new JSONArray();
                    jSONObject2 = new JSONObject();
                    jSONObject2.put(au.f211Y, c0625o.f713j.f268a);
                    jSONObject2.put(au.f212Z, c0625o.f713j.f269b);
                    jSONObject2.put("ts", c0625o.f713j.f270c);
                    jSONArray6.put(jSONObject2);
                    jSONObject5.put(au.f210X, jSONArray6);
                }
                jSONArray5.put(jSONObject5);
            }
            if (jSONArray5 != null && jSONArray5.length() > 0) {
                jSONObject.put(au.f202P, jSONArray5);
                stringBuilder.append("; sessions:").append(jSONArray5.toString());
            }
        }
        if (avVar.f335b.f280d.f243a != 0) {
            jSONObject4 = new JSONObject();
            jSONObject4.put("ts", avVar.f335b.f280d.f243a);
            if (jSONObject4.length() > 0) {
                jSONObject.put(au.ae, jSONObject4);
                stringBuilder.append("; active_msg: ").append(jSONObject4.toString());
            }
        }
        if (avVar.f335b.f281e.f263c) {
            jSONObject4 = new JSONObject();
            jSONObject7 = new JSONObject();
            jSONObject7.put(au.aj, avVar.f335b.f281e.f262b);
            jSONObject7.put(au.ai, avVar.f335b.f281e.f261a);
            jSONObject4.put(au.ah, jSONObject7);
            if (jSONObject4.length() > 0) {
                jSONObject.put(au.ag, jSONObject4);
                stringBuilder.append("; control_policy: ").append(jSONObject4.toString());
            }
        }
        if (avVar.f335b.f282f.size() > 0) {
            JSONObject jSONObject9 = new JSONObject();
            for (Entry entry22 : avVar.f335b.f282f.entrySet()) {
                jSONObject9.put((String) entry22.getKey(), entry22.getValue());
            }
            jSONObject.put(au.ak, jSONObject9);
        }
        if (!(avVar.f335b.f283g.f245a == null && avVar.f335b.f283g.f246b == null)) {
            jSONObject4 = new JSONObject();
            jSONObject4.put(au.an, avVar.f335b.f283g.f245a);
            jSONObject4.put(au.ao, avVar.f335b.f283g.f246b);
            if (jSONObject4.length() > 0) {
                jSONObject.put(au.am, jSONObject4);
                stringBuilder.append("; active_user: ").append(jSONObject4.toString());
            }
        }
        if (avVar.f335b.f286j != null) {
            jSONObject.put("userlevel", avVar.f335b.f286j);
        }
    }
}
