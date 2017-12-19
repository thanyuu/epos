package com.android.common.utils;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import java.lang.reflect.Method;

@SuppressLint({"NewApi"})
public class DownloadManagerPro {
    public static final String COLUMN_LOCAL_FILENAME = "local_filename";
    public static final String COLUMN_LOCAL_URI = "local_uri";
    public static final Uri CONTENT_URI = Uri.parse("content://downloads/my_downloads");
    public static final String METHOD_NAME_PAUSE_DOWNLOAD = "pauseDownload";
    public static final String METHOD_NAME_RESUME_DOWNLOAD = "resumeDownload";
    private static boolean isInitPauseDownload = false;
    private static boolean isInitResumeDownload = false;
    private static Method pauseDownload = null;
    private static Method resumeDownload = null;
    private DownloadManager downloadManager;

    public static class RequestPro extends Request {
        public static final String METHOD_NAME_SET_NOTI_CLASS = "setNotiClass";
        public static final String METHOD_NAME_SET_NOTI_EXTRAS = "setNotiExtras";
        private static boolean isInitNotiClass = false;
        private static boolean isInitNotiExtras = false;
        private static Method setNotiClass = null;
        private static Method setNotiExtras = null;

        public RequestPro(Uri uri) {
            super(uri);
        }

        public void setNotiClass(String className) {
            synchronized (this) {
                if (!isInitNotiClass) {
                    isInitNotiClass = true;
                    try {
                        setNotiClass = Request.class.getMethod(METHOD_NAME_SET_NOTI_CLASS, new Class[]{CharSequence.class});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (setNotiClass != null) {
                try {
                    setNotiClass.invoke(this, new Object[]{className});
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        public void setNotiExtras(String extras) {
            synchronized (this) {
                if (!isInitNotiExtras) {
                    isInitNotiExtras = true;
                    try {
                        setNotiExtras = Request.class.getMethod(METHOD_NAME_SET_NOTI_EXTRAS, new Class[]{CharSequence.class});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (setNotiExtras != null) {
                try {
                    setNotiExtras.invoke(this, new Object[]{extras});
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public DownloadManagerPro(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }

    public int getStatusById(long downloadId) {
        return getInt(downloadId, "status");
    }

    public int[] getDownloadBytes(long downloadId) {
        int[] bytesAndStatus = getBytesAndStatus(downloadId);
        return new int[]{bytesAndStatus[0], bytesAndStatus[1]};
    }

    public int[] getBytesAndStatus(long downloadId) {
        int[] bytesAndStatus = new int[3];
        bytesAndStatus[0] = -1;
        bytesAndStatus[1] = -1;
        Cursor c = null;
        try {
            c = this.downloadManager.query(new Query().setFilterById(new long[]{downloadId}));
            if (c != null && c.moveToFirst()) {
                bytesAndStatus[0] = c.getInt(c.getColumnIndexOrThrow("bytes_so_far"));
                bytesAndStatus[1] = c.getInt(c.getColumnIndexOrThrow("total_size"));
                bytesAndStatus[2] = c.getInt(c.getColumnIndex("status"));
            }
            if (c != null) {
                c.close();
            }
            return bytesAndStatus;
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
        }
    }

    public int pauseDownload(long... ids) {
        initPauseMethod();
        if (pauseDownload == null) {
            return -1;
        }
        try {
            return ((Integer) pauseDownload.invoke(this.downloadManager, new Object[]{ids})).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int resumeDownload(long... ids) {
        initResumeMethod();
        if (resumeDownload == null) {
            return -1;
        }
        try {
            return ((Integer) resumeDownload.invoke(this.downloadManager, new Object[]{ids})).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean isExistPauseAndResumeMethod() {
        initPauseMethod();
        initResumeMethod();
        return (pauseDownload == null || resumeDownload == null) ? false : true;
    }

    private static void initPauseMethod() {
        if (!isInitPauseDownload) {
            isInitPauseDownload = true;
            try {
                pauseDownload = DownloadManager.class.getMethod(METHOD_NAME_PAUSE_DOWNLOAD, new Class[]{long[].class});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void initResumeMethod() {
        if (!isInitResumeDownload) {
            isInitResumeDownload = true;
            try {
                resumeDownload = DownloadManager.class.getMethod(METHOD_NAME_RESUME_DOWNLOAD, new Class[]{long[].class});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileName(long downloadId) {
        return getString(downloadId, VERSION.SDK_INT < 11 ? COLUMN_LOCAL_URI : COLUMN_LOCAL_FILENAME);
    }

    public String getUri(long downloadId) {
        return getString(downloadId, "uri");
    }

    public int getReason(long downloadId) {
        return getInt(downloadId, "reason");
    }

    public int getPausedReason(long downloadId) {
        return getInt(downloadId, "reason");
    }

    public int getErrorCode(long downloadId) {
        return getInt(downloadId, "reason");
    }

    private String getString(long downloadId, String columnName) {
        String result = null;
        Cursor c = null;
        try {
            c = this.downloadManager.query(new Query().setFilterById(new long[]{downloadId}));
            if (c != null && c.moveToFirst()) {
                result = c.getString(c.getColumnIndex(columnName));
            }
            if (c != null) {
                c.close();
            }
            return result;
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
        }
    }

    private int getInt(long downloadId, String columnName) {
        int result = -1;
        Cursor c = null;
        try {
            c = this.downloadManager.query(new Query().setFilterById(new long[]{downloadId}));
            if (c != null && c.moveToFirst()) {
                result = c.getInt(c.getColumnIndex(columnName));
            }
            if (c != null) {
                c.close();
            }
            return result;
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
        }
    }
}
