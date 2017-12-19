package com.android.common.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SqliteUtils {
    private static volatile SqliteUtils instance;
    private DbHelper dbHelper;
    private SQLiteDatabase rDb = this.dbHelper.getReadableDatabase();
    private SQLiteDatabase wDb = this.dbHelper.getWritableDatabase();

    private SqliteUtils(Context context) {
        this.dbHelper = new DbHelper(context);
    }

    public static SqliteUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (SqliteUtils.class) {
                if (instance == null) {
                    instance = new SqliteUtils(context);
                }
            }
        }
        return instance;
    }

    public SQLiteDatabase getWDb() {
        return this.wDb;
    }

    public SQLiteDatabase getRDb() {
        return this.rDb;
    }
}
