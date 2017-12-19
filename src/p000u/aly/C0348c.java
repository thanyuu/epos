package p000u.aly;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import p000u.aly.C0359d.C0352a;
import p000u.aly.C0359d.C0355b;
import p000u.aly.C0359d.C0358c;

/* compiled from: UMCCDBHelper */
class C0348c extends SQLiteOpenHelper {
    private static Context f370b;
    private String f371a;

    /* compiled from: UMCCDBHelper */
    private static class C0347a {
        private static final C0348c f369a = new C0348c(C0348c.f370b, C0359d.m486a(C0348c.f370b), C0359d.f454c, null, 1);

        private C0347a() {
        }
    }

    public static synchronized C0348c m419a(Context context) {
        C0348c a;
        synchronized (C0348c.class) {
            f370b = context;
            a = C0347a.f369a;
        }
        return a;
    }

    private C0348c(Context context, String str, String str2, CursorFactory cursorFactory, int i) {
        this(new C0360e(context, str), str2, cursorFactory, i);
    }

    private C0348c(Context context, String str, CursorFactory cursorFactory, int i) {
        if (str == null || str.equals("")) {
            str = C0359d.f454c;
        }
        super(context, str, cursorFactory, i);
        m421b();
    }

    private void m421b() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (!(m424a(C0352a.f438a, writableDatabase) && m424a(C0352a.f439b, writableDatabase))) {
                m423c(writableDatabase);
            }
            if (!m424a(C0358c.f451a, writableDatabase)) {
                m422b(writableDatabase);
            }
            if (!m424a(C0355b.f442a, writableDatabase)) {
                m420a(writableDatabase);
            }
        } catch (Exception e) {
        }
    }

    public boolean m424a(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        boolean z = false;
        if (str != null) {
            try {
                cursor = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type ='table' and name ='" + str.trim() + "' ", null);
                if (cursor.moveToNext() && cursor.getInt(0) > 0) {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return z;
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            m423c(sQLiteDatabase);
            m422b(sQLiteDatabase);
            m420a(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private boolean m420a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f371a = "create table if not exists limitedck(Id INTEGER primary key autoincrement, ck TEXT unique)";
            sQLiteDatabase.execSQL(this.f371a);
            return true;
        } catch (SQLException e) {
            bl.m340e("create reference table error!");
            return false;
        }
    }

    private boolean m422b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f371a = "create table if not exists system(Id INTEGER primary key autoincrement, key TEXT, timeStamp INTEGER, count INTEGER)";
            sQLiteDatabase.execSQL(this.f371a);
            return true;
        } catch (SQLException e) {
            bl.m340e("create system table error!");
            return false;
        }
    }

    private boolean m423c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f371a = "create table if not exists aggregated_cache(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            sQLiteDatabase.execSQL(this.f371a);
            this.f371a = "create table if not exists aggregated(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            sQLiteDatabase.execSQL(this.f371a);
            return true;
        } catch (SQLException e) {
            bl.m340e("create aggregated table error!");
            return false;
        }
    }
}
