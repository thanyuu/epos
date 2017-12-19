package p000u.aly;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import p000u.aly.ci.C0650a;

/* compiled from: TDeserializer */
public class bs {
    private final co f360a;
    private final db f361b;

    public bs() {
        this(new C0650a());
    }

    public bs(cq cqVar) {
        this.f361b = new db();
        this.f360a = cqVar.mo1761a(this.f361b);
    }

    public void m400a(bp bpVar, byte[] bArr) throws bv {
        try {
            this.f361b.m1355a(bArr);
            bpVar.mo1756a(this.f360a);
        } finally {
            this.f361b.m1361e();
            this.f360a.mo1803B();
        }
    }

    public void m399a(bp bpVar, String str, String str2) throws bv {
        try {
            m400a(bpVar, str.getBytes(str2));
            this.f360a.mo1803B();
        } catch (UnsupportedEncodingException e) {
            throw new bv("JVM DOES NOT SUPPORT ENCODING: " + str2);
        } catch (Throwable th) {
            this.f360a.mo1803B();
        }
    }

    public void m401a(bp bpVar, byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        try {
            if (m396j(bArr, bwVar, bwVarArr) != null) {
                bpVar.mo1756a(this.f360a);
            }
            this.f361b.m1361e();
            this.f360a.mo1803B();
        } catch (Throwable e) {
            throw new bv(e);
        } catch (Throwable th) {
            this.f361b.m1361e();
            this.f360a.mo1803B();
        }
    }

    public Boolean m397a(byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        return (Boolean) m395a((byte) 2, bArr, bwVar, bwVarArr);
    }

    public Byte m402b(byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        return (Byte) m395a((byte) 3, bArr, bwVar, bwVarArr);
    }

    public Double m403c(byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        return (Double) m395a((byte) 4, bArr, bwVar, bwVarArr);
    }

    public Short m404d(byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        return (Short) m395a((byte) 6, bArr, bwVar, bwVarArr);
    }

    public Integer m405e(byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        return (Integer) m395a((byte) 8, bArr, bwVar, bwVarArr);
    }

    public Long m406f(byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        return (Long) m395a((byte) 10, bArr, bwVar, bwVarArr);
    }

    public String m407g(byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        return (String) m395a((byte) 11, bArr, bwVar, bwVarArr);
    }

    public ByteBuffer m408h(byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        return (ByteBuffer) m395a((byte) 100, bArr, bwVar, bwVarArr);
    }

    public Short m409i(byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        try {
            if (m396j(bArr, bwVar, bwVarArr) != null) {
                this.f360a.mo1786j();
                Short valueOf = Short.valueOf(this.f360a.mo1788l().f394c);
                this.f361b.m1361e();
                this.f360a.mo1803B();
                return valueOf;
            }
            this.f361b.m1361e();
            this.f360a.mo1803B();
            return null;
        } catch (Throwable e) {
            throw new bv(e);
        } catch (Throwable th) {
            this.f361b.m1361e();
            this.f360a.mo1803B();
        }
    }

    private Object m395a(byte b, byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        try {
            cj j = m396j(bArr, bwVar, bwVarArr);
            if (j != null) {
                Object valueOf;
                switch (b) {
                    case (byte) 2:
                        if (j.f393b == (byte) 2) {
                            valueOf = Boolean.valueOf(this.f360a.mo1796t());
                            this.f361b.m1361e();
                            this.f360a.mo1803B();
                            return valueOf;
                        }
                        break;
                    case (byte) 3:
                        if (j.f393b == (byte) 3) {
                            valueOf = Byte.valueOf(this.f360a.mo1797u());
                            this.f361b.m1361e();
                            this.f360a.mo1803B();
                            return valueOf;
                        }
                        break;
                    case (byte) 4:
                        if (j.f393b == (byte) 4) {
                            valueOf = Double.valueOf(this.f360a.mo1801y());
                            this.f361b.m1361e();
                            this.f360a.mo1803B();
                            return valueOf;
                        }
                        break;
                    case (byte) 6:
                        if (j.f393b == (byte) 6) {
                            valueOf = Short.valueOf(this.f360a.mo1798v());
                            this.f361b.m1361e();
                            this.f360a.mo1803B();
                            return valueOf;
                        }
                        break;
                    case (byte) 8:
                        if (j.f393b == (byte) 8) {
                            valueOf = Integer.valueOf(this.f360a.mo1799w());
                            this.f361b.m1361e();
                            this.f360a.mo1803B();
                            return valueOf;
                        }
                        break;
                    case (byte) 10:
                        if (j.f393b == (byte) 10) {
                            valueOf = Long.valueOf(this.f360a.mo1800x());
                            this.f361b.m1361e();
                            this.f360a.mo1803B();
                            return valueOf;
                        }
                        break;
                    case (byte) 11:
                        if (j.f393b == (byte) 11) {
                            valueOf = this.f360a.mo1802z();
                            this.f361b.m1361e();
                            this.f360a.mo1803B();
                            return valueOf;
                        }
                        break;
                    case (byte) 100:
                        if (j.f393b == (byte) 11) {
                            valueOf = this.f360a.mo1762A();
                            this.f361b.m1361e();
                            this.f360a.mo1803B();
                            return valueOf;
                        }
                        break;
                }
            }
            this.f361b.m1361e();
            this.f360a.mo1803B();
            return null;
        } catch (Throwable e) {
            throw new bv(e);
        } catch (Throwable th) {
            this.f361b.m1361e();
            this.f360a.mo1803B();
        }
    }

    private cj m396j(byte[] bArr, bw bwVar, bw... bwVarArr) throws bv {
        int i = 0;
        this.f361b.m1355a(bArr);
        bw[] bwVarArr2 = new bw[(bwVarArr.length + 1)];
        bwVarArr2[0] = bwVar;
        for (int i2 = 0; i2 < bwVarArr.length; i2++) {
            bwVarArr2[i2 + 1] = bwVarArr[i2];
        }
        this.f360a.mo1786j();
        cj cjVar = null;
        while (i < bwVarArr2.length) {
            cjVar = this.f360a.mo1788l();
            if (cjVar.f393b == (byte) 0 || cjVar.f394c > bwVarArr2[i].mo1754a()) {
                return null;
            }
            if (cjVar.f394c != bwVarArr2[i].mo1754a()) {
                cr.m481a(this.f360a, cjVar.f393b);
                this.f360a.mo1789m();
            } else {
                i++;
                if (i < bwVarArr2.length) {
                    this.f360a.mo1786j();
                }
            }
        }
        return cjVar;
    }

    public void m398a(bp bpVar, String str) throws bv {
        m400a(bpVar, str.getBytes());
    }
}
