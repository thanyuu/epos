package com.android.common.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AssetDatabaseOpenHelper {
    private Context context;
    private String databaseName;

    public AssetDatabaseOpenHelper(Context context, String databaseName) {
        this.context = context;
        this.databaseName = databaseName;
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        File dbFile;
        dbFile = this.context.getDatabasePath(this.databaseName);
        if (!(dbFile == null || dbFile.exists())) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, 0);
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        File dbFile;
        dbFile = this.context.getDatabasePath(this.databaseName);
        if (!(dbFile == null || dbFile.exists())) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, 1);
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    private void copyDatabase(File dbFile) throws IOException {
        InputStream stream = this.context.getAssets().open(this.databaseName);
        FileUtils.writeFile(dbFile, stream);
        stream.close();
    }
}
