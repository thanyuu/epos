package p000u.aly;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: TIOStreamTransport */
public class da extends dc {
    protected InputStream f972a = null;
    protected OutputStream f973b = null;

    protected da() {
    }

    public da(InputStream inputStream) {
        this.f972a = inputStream;
    }

    public da(OutputStream outputStream) {
        this.f973b = outputStream;
    }

    public da(InputStream inputStream, OutputStream outputStream) {
        this.f972a = inputStream;
        this.f973b = outputStream;
    }

    public boolean mo1805a() {
        return true;
    }

    public void mo1806b() throws dd {
    }

    public void mo1808c() {
        if (this.f972a != null) {
            try {
                this.f972a.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.f972a = null;
        }
        if (this.f973b != null) {
            try {
                this.f973b.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.f973b = null;
        }
    }

    public int mo1804a(byte[] bArr, int i, int i2) throws dd {
        if (this.f972a == null) {
            throw new dd(1, "Cannot read from null inputStream");
        }
        try {
            int read = this.f972a.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new dd(4);
        } catch (Throwable e) {
            throw new dd(0, e);
        }
    }

    public void mo1807b(byte[] bArr, int i, int i2) throws dd {
        if (this.f973b == null) {
            throw new dd(1, "Cannot write to null outputStream");
        }
        try {
            this.f973b.write(bArr, i, i2);
        } catch (Throwable e) {
            throw new dd(0, e);
        }
    }

    public void mo1809d() throws dd {
        if (this.f973b == null) {
            throw new dd(1, "Cannot flush null outputStream");
        }
        try {
            this.f973b.flush();
        } catch (Throwable e) {
            throw new dd(0, e);
        }
    }
}
