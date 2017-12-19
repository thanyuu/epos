package p000u.aly;

/* compiled from: TMessage */
public final class cm {
    public final String f400a;
    public final byte f401b;
    public final int f402c;

    public cm() {
        this("", (byte) 0, 0);
    }

    public cm(String str, byte b, int i) {
        this.f400a = str;
        this.f401b = b;
        this.f402c = i;
    }

    public String toString() {
        return "<TMessage name:'" + this.f400a + "' type: " + this.f401b + " seqid:" + this.f402c + ">";
    }

    public boolean equals(Object obj) {
        if (obj instanceof cm) {
            return m433a((cm) obj);
        }
        return false;
    }

    public boolean m433a(cm cmVar) {
        return this.f400a.equals(cmVar.f400a) && this.f401b == cmVar.f401b && this.f402c == cmVar.f402c;
    }
}
