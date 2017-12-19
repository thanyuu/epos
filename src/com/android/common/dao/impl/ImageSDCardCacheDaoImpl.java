package com.android.common.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.android.common.constant.DbConstants;
import com.android.common.dao.ImageSDCardCacheDao;
import com.android.common.entity.CacheObject;
import com.android.common.service.impl.ImageSDCardCache;
import com.android.common.utils.SqliteUtils;
import com.android.common.utils.StringUtils;
import java.util.Map.Entry;

public class ImageSDCardCacheDaoImpl implements ImageSDCardCacheDao {
    private SqliteUtils sqliteUtils;

    public ImageSDCardCacheDaoImpl(SqliteUtils sqliteUtils) {
        this.sqliteUtils = sqliteUtils;
    }

    public boolean putIntoImageSDCardCache(ImageSDCardCache imageSDCardCache, String tag) {
        if (imageSDCardCache == null || StringUtils.isEmpty(tag)) {
            return false;
        }
        StringBuilder selection = new StringBuilder();
        selection.append(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TAG).append("=?");
        Cursor cursor = this.sqliteUtils.getRDb().query(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TABLE_NAME, null, selection.toString(), new String[]{tag}, null, null, null);
        if (cursor == null) {
            return true;
        }
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                CacheObject<String> value = new CacheObject();
                String imageUrl = cursor.getString(2);
                value.setData(cursor.getString(3));
                value.setUsedCount((long) cursor.getInt(6));
                value.setPriority(cursor.getInt(7));
                value.setExpired(cursor.getInt(8) == 1);
                value.setForever(cursor.getInt(9) == 1);
                imageSDCardCache.put((Object) imageUrl, (CacheObject) value);
                cursor.moveToNext();
            }
        }
        if (!(cursor == null || cursor.isClosed())) {
            cursor.close();
        }
        return true;
    }

    public boolean deleteAndInsertImageSDCardCache(ImageSDCardCache imageSDCardCache, String tag) {
        if (imageSDCardCache == null || StringUtils.isEmpty(tag)) {
            return false;
        }
        SQLiteDatabase db = this.sqliteUtils.getWDb();
        db.beginTransaction();
        try {
            StringBuilder whereClause = new StringBuilder();
            whereClause.append(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TAG).append("=?");
            db.delete(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TABLE_NAME, whereClause.toString(), new String[]{tag});
            for (Entry<String, CacheObject<String>> entry : imageSDCardCache.entrySet()) {
                if (entry != null) {
                    String key = (String) entry.getKey();
                    if (key != null) {
                        CacheObject<String> value = (CacheObject) entry.getValue();
                        if (value != null) {
                            db.insert(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TABLE_NAME, null, cacheObjectToCV(tag, key, value));
                        }
                    }
                }
            }
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.endTransaction();
        }
    }

    private static ContentValues cacheObjectToCV(String tag, String url, CacheObject<String> value) {
        int i;
        int i2 = 1;
        ContentValues values = new ContentValues();
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TAG, tag);
        values.put("url", url);
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_PATH, (String) value.getData());
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_ENTER_TIME, Long.valueOf(value.getEnterTime()));
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_LAST_USED_TIME, Long.valueOf(value.getLastUsedTime()));
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_USED_COUNT, Long.valueOf(value.getUsedCount()));
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_PRIORITY, Integer.valueOf(value.getPriority()));
        String str = DbConstants.IMAGE_SDCARD_CACHE_TABLE_IS_EXPIRED;
        if (value.isExpired()) {
            i = 1;
        } else {
            i = 0;
        }
        values.put(str, Integer.valueOf(i));
        String str2 = DbConstants.IMAGE_SDCARD_CACHE_TABLE_IS_FOREVER;
        if (!value.isForever()) {
            i2 = 0;
        }
        values.put(str2, Integer.valueOf(i2));
        return values;
    }
}
