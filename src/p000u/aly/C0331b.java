package p000u.aly;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: DatabaseManager */
public class C0331b {
    private static C0331b f336c;
    private static SQLiteOpenHelper f337d;
    private AtomicInteger f338a = new AtomicInteger();
    private AtomicInteger f339b = new AtomicInteger();
    private SQLiteDatabase f340e;

    private static synchronized void m230b(Context context) {
        synchronized (C0331b.class) {
            if (f336c == null) {
                f336c = new C0331b();
                f337d = C0348c.m419a(context);
            }
        }
    }

    public static synchronized C0331b m229a(Context context) {
        C0331b c0331b;
        synchronized (C0331b.class) {
            if (f336c == null) {
                C0331b.m230b(context);
            }
            c0331b = f336c;
        }
        return c0331b;
    }

    public synchronized SQLiteDatabase m231a() {
        if (this.f338a.incrementAndGet() == 1) {
            this.f340e = f337d.getReadableDatabase();
        }
        return this.f340e;
    }

    public synchronized SQLiteDatabase m232b() {
        if (this.f338a.incrementAndGet() == 1) {
            this.f340e = f337d.getWritableDatabase();
        }
        return this.f340e;
    }

    public synchronized void m233c() {
        if (this.f338a.decrementAndGet() == 0) {
            this.f340e.close();
        }
        if (this.f339b.decrementAndGet() == 0) {
            this.f340e.close();
        }
    }
}
