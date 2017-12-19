package p000u.aly;

/* compiled from: TField */
public class cj {
    public final String f392a;
    public final byte f393b;
    public final short f394c;

    public cj() {
        this("", (byte) 0, (short) 0);
    }

    public cj(String str, byte b, short s) {
        this.f392a = str;
        this.f393b = b;
        this.f394c = s;
    }

    public String toString() {
        return "<TField name:'" + this.f392a + "' type:" + this.f393b + " field-id:" + this.f394c + ">";
    }

    public boolean m432a(cj cjVar) {
        return this.f393b == cjVar.f393b && this.f394c == cjVar.f394c;
    }
}
