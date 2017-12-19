package p000u.aly;

/* compiled from: ShortStack */
public class bn {
    private short[] f357a;
    private int f358b = -1;

    public bn(int i) {
        this.f357a = new short[i];
    }

    public short m363a() {
        short[] sArr = this.f357a;
        int i = this.f358b;
        this.f358b = i - 1;
        return sArr[i];
    }

    public void m364a(short s) {
        if (this.f357a.length == this.f358b + 1) {
            m362d();
        }
        short[] sArr = this.f357a;
        int i = this.f358b + 1;
        this.f358b = i;
        sArr[i] = s;
    }

    private void m362d() {
        Object obj = new short[(this.f357a.length * 2)];
        System.arraycopy(this.f357a, 0, obj, 0, this.f357a.length);
        this.f357a = obj;
    }

    public short m365b() {
        return this.f357a[this.f358b];
    }

    public void m366c() {
        this.f358b = -1;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ShortStack vector:[");
        for (int i = 0; i < this.f357a.length; i++) {
            if (i != 0) {
                stringBuilder.append(" ");
            }
            if (i == this.f358b) {
                stringBuilder.append(">>");
            }
            stringBuilder.append(this.f357a[i]);
            if (i == this.f358b) {
                stringBuilder.append("<<");
            }
        }
        stringBuilder.append("]>");
        return stringBuilder.toString();
    }
}
