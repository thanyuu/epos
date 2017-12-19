package p000u.aly;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: AdvertisingId */
public class bh {

    /* compiled from: AdvertisingId */
    private static final class C0340a {
        private final String f341a;
        private final boolean f342b;

        C0340a(String str, boolean z) {
            this.f341a = str;
            this.f342b = z;
        }

        private String m235b() {
            return this.f341a;
        }

        public boolean m236a() {
            return this.f342b;
        }
    }

    /* compiled from: AdvertisingId */
    private static final class C0341b implements ServiceConnection {
        boolean f343a;
        private final LinkedBlockingQueue<IBinder> f344b;

        private C0341b() {
            this.f343a = false;
            this.f344b = new LinkedBlockingQueue(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f344b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder m237a() throws InterruptedException {
            if (this.f343a) {
                throw new IllegalStateException();
            }
            this.f343a = true;
            return (IBinder) this.f344b.take();
        }
    }

    /* compiled from: AdvertisingId */
    private static final class C0342c implements IInterface {
        private IBinder f345a;

        public C0342c(IBinder iBinder) {
            this.f345a = iBinder;
        }

        public IBinder asBinder() {
            return this.f345a;
        }

        public String m238a() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f345a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean m239a(boolean z) throws RemoteException {
            boolean z2 = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(z ? 1 : 0);
                this.f345a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z2 = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z2;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public static String m240a(Context context) {
        String str = null;
        try {
            C0340a b = bh.m241b(context);
            if (b != null) {
                str = b.m235b();
            }
        } catch (Exception e) {
        }
        return str;
    }

    private static C0340a m241b(Context context) throws Exception {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            ServiceConnection c0341b = new C0341b();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            if (context.bindService(intent, c0341b, 1)) {
                try {
                    C0342c c0342c = new C0342c(c0341b.m237a());
                    C0340a c0340a = new C0340a(c0342c.m238a(), c0342c.m239a(true));
                    context.unbindService(c0341b);
                    return c0340a;
                } catch (Exception e) {
                    throw e;
                } catch (Throwable th) {
                    context.unbindService(c0341b);
                }
            } else {
                throw new IOException("Google Play connection failed");
            }
        } catch (Exception e2) {
            throw e2;
        }
    }
}
