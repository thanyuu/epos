package com.android.common.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.common.constant.DbConstants;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, DbConstants.DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(DbConstants.CREATE_IMAGE_SDCARD_CACHE_TABLE_SQL.toString());
            db.execSQL(DbConstants.CREATE_IMAGE_SDCARD_CACHE_TABLE_INDEX_SQL.toString());
            db.execSQL(DbConstants.CREATE_HTTP_CACHE_TABLE_SQL.toString());
            db.execSQL(DbConstants.CREATE_HTTP_CACHE_TABLE_INDEX_SQL.toString());
            db.execSQL(DbConstants.CREATE_HTTP_CACHE_TABLE_UNIQUE_INDEX.toString());
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
