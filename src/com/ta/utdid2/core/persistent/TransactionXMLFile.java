package com.ta.utdid2.core.persistent;

import com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor;
import com.ta.utdid2.core.persistent.MySharedPreferences.OnSharedPreferenceChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

public class TransactionXMLFile {
    private static final Object GLOBAL_COMMIT_LOCK = new Object();
    public static final int MODE_PRIVATE = 0;
    public static final int MODE_WORLD_READABLE = 1;
    public static final int MODE_WORLD_WRITEABLE = 2;
    private File mPreferencesDir;
    private final Object mSync = new Object();
    private HashMap<File, MySharedPreferencesImpl> sSharedPrefs = new HashMap();

    private static final class MySharedPreferencesImpl implements MySharedPreferences {
        private static final Object mContent = new Object();
        private boolean hasChange = false;
        private final File mBackupFile;
        private final File mFile;
        private WeakHashMap<OnSharedPreferenceChangeListener, Object> mListeners;
        private Map mMap;
        private final int mMode;

        public final class EditorImpl implements MyEditor {
            private boolean mClear = false;
            private final Map<String, Object> mModified = new HashMap();

            public MyEditor putString(String key, String value) {
                synchronized (this) {
                    this.mModified.put(key, value);
                }
                return this;
            }

            public MyEditor putInt(String key, int value) {
                synchronized (this) {
                    this.mModified.put(key, Integer.valueOf(value));
                }
                return this;
            }

            public MyEditor putLong(String key, long value) {
                synchronized (this) {
                    this.mModified.put(key, Long.valueOf(value));
                }
                return this;
            }

            public MyEditor putFloat(String key, float value) {
                synchronized (this) {
                    this.mModified.put(key, Float.valueOf(value));
                }
                return this;
            }

            public MyEditor putBoolean(String key, boolean value) {
                synchronized (this) {
                    this.mModified.put(key, Boolean.valueOf(value));
                }
                return this;
            }

            public MyEditor remove(String key) {
                synchronized (this) {
                    this.mModified.put(key, this);
                }
                return this;
            }

            public MyEditor clear() {
                synchronized (this) {
                    this.mClear = true;
                }
                return this;
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public boolean commit() {
                /*
                r17 = this;
                r7 = 0;
                r10 = 0;
                r15 = com.ta.utdid2.core.persistent.TransactionXMLFile.GLOBAL_COMMIT_LOCK;
                monitor-enter(r15);
                r0 = r17;
                r14 = com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl.this;	 Catch:{ all -> 0x0081 }
                r14 = r14.mListeners;	 Catch:{ all -> 0x0081 }
                r14 = r14.size();	 Catch:{ all -> 0x0081 }
                if (r14 <= 0) goto L_0x0084;
            L_0x0015:
                r2 = 1;
            L_0x0016:
                if (r2 == 0) goto L_0x0030;
            L_0x0018:
                r8 = new java.util.ArrayList;	 Catch:{ all -> 0x0081 }
                r8.<init>();	 Catch:{ all -> 0x0081 }
                r11 = new java.util.HashSet;	 Catch:{ all -> 0x00de }
                r0 = r17;
                r14 = com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl.this;	 Catch:{ all -> 0x00de }
                r14 = r14.mListeners;	 Catch:{ all -> 0x00de }
                r14 = r14.keySet();	 Catch:{ all -> 0x00de }
                r11.<init>(r14);	 Catch:{ all -> 0x00de }
                r10 = r11;
                r7 = r8;
            L_0x0030:
                monitor-enter(r17);	 Catch:{ all -> 0x0081 }
                r0 = r17;
                r14 = r0.mClear;	 Catch:{ all -> 0x007e }
                if (r14 == 0) goto L_0x0047;
            L_0x0037:
                r0 = r17;
                r14 = com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl.this;	 Catch:{ all -> 0x007e }
                r14 = r14.mMap;	 Catch:{ all -> 0x007e }
                r14.clear();	 Catch:{ all -> 0x007e }
                r14 = 0;
                r0 = r17;
                r0.mClear = r14;	 Catch:{ all -> 0x007e }
            L_0x0047:
                r0 = r17;
                r14 = r0.mModified;	 Catch:{ all -> 0x007e }
                r14 = r14.entrySet();	 Catch:{ all -> 0x007e }
                r4 = r14.iterator();	 Catch:{ all -> 0x007e }
            L_0x0053:
                r14 = r4.hasNext();	 Catch:{ all -> 0x007e }
                if (r14 == 0) goto L_0x0092;
            L_0x0059:
                r1 = r4.next();	 Catch:{ all -> 0x007e }
                r1 = (java.util.Map.Entry) r1;	 Catch:{ all -> 0x007e }
                r5 = r1.getKey();	 Catch:{ all -> 0x007e }
                r5 = (java.lang.String) r5;	 Catch:{ all -> 0x007e }
                r13 = r1.getValue();	 Catch:{ all -> 0x007e }
                r0 = r17;
                if (r13 != r0) goto L_0x0086;
            L_0x006d:
                r0 = r17;
                r14 = com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl.this;	 Catch:{ all -> 0x007e }
                r14 = r14.mMap;	 Catch:{ all -> 0x007e }
                r14.remove(r5);	 Catch:{ all -> 0x007e }
            L_0x0078:
                if (r2 == 0) goto L_0x0053;
            L_0x007a:
                r7.add(r5);	 Catch:{ all -> 0x007e }
                goto L_0x0053;
            L_0x007e:
                r14 = move-exception;
                monitor-exit(r17);	 Catch:{ all -> 0x007e }
                throw r14;	 Catch:{ all -> 0x0081 }
            L_0x0081:
                r14 = move-exception;
            L_0x0082:
                monitor-exit(r15);	 Catch:{ all -> 0x0081 }
                throw r14;
            L_0x0084:
                r2 = 0;
                goto L_0x0016;
            L_0x0086:
                r0 = r17;
                r14 = com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl.this;	 Catch:{ all -> 0x007e }
                r14 = r14.mMap;	 Catch:{ all -> 0x007e }
                r14.put(r5, r13);	 Catch:{ all -> 0x007e }
                goto L_0x0078;
            L_0x0092:
                r0 = r17;
                r14 = r0.mModified;	 Catch:{ all -> 0x007e }
                r14.clear();	 Catch:{ all -> 0x007e }
                monitor-exit(r17);	 Catch:{ all -> 0x007e }
                r0 = r17;
                r14 = com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl.this;	 Catch:{ all -> 0x0081 }
                r12 = r14.writeFileLocked();	 Catch:{ all -> 0x0081 }
                if (r12 == 0) goto L_0x00af;
            L_0x00a4:
                r0 = r17;
                r14 = com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl.this;	 Catch:{ all -> 0x0081 }
                r16 = 1;
                r0 = r16;
                r14.setHasChange(r0);	 Catch:{ all -> 0x0081 }
            L_0x00af:
                monitor-exit(r15);	 Catch:{ all -> 0x0081 }
                if (r2 == 0) goto L_0x00dd;
            L_0x00b2:
                r14 = r7.size();
                r3 = r14 + -1;
            L_0x00b8:
                if (r3 < 0) goto L_0x00dd;
            L_0x00ba:
                r6 = r7.get(r3);
                r6 = (java.lang.String) r6;
                r4 = r10.iterator();
            L_0x00c4:
                r14 = r4.hasNext();
                if (r14 == 0) goto L_0x00da;
            L_0x00ca:
                r9 = r4.next();
                r9 = (com.ta.utdid2.core.persistent.MySharedPreferences.OnSharedPreferenceChangeListener) r9;
                if (r9 == 0) goto L_0x00c4;
            L_0x00d2:
                r0 = r17;
                r14 = com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl.this;
                r9.onSharedPreferenceChanged(r14, r6);
                goto L_0x00c4;
            L_0x00da:
                r3 = r3 + -1;
                goto L_0x00b8;
            L_0x00dd:
                return r12;
            L_0x00de:
                r14 = move-exception;
                r7 = r8;
                goto L_0x0082;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl.EditorImpl.commit():boolean");
            }
        }

        MySharedPreferencesImpl(File file, int mode, Map initialContents) {
            this.mFile = file;
            this.mBackupFile = TransactionXMLFile.makeBackupFile(file);
            this.mMode = mode;
            if (initialContents == null) {
                initialContents = new HashMap();
            }
            this.mMap = initialContents;
            this.mListeners = new WeakHashMap();
        }

        public boolean checkFile() {
            if (this.mFile == null || !new File(this.mFile.getAbsolutePath()).exists()) {
                return false;
            }
            return true;
        }

        public void setHasChange(boolean hasChange) {
            synchronized (this) {
                this.hasChange = hasChange;
            }
        }

        public boolean hasFileChanged() {
            boolean z;
            synchronized (this) {
                z = this.hasChange;
            }
            return z;
        }

        public void replace(Map newContents) {
            if (newContents != null) {
                synchronized (this) {
                    this.mMap = newContents;
                }
            }
        }

        public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
            synchronized (this) {
                this.mListeners.put(listener, mContent);
            }
        }

        public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
            synchronized (this) {
                this.mListeners.remove(listener);
            }
        }

        public Map<String, ?> getAll() {
            Map hashMap;
            synchronized (this) {
                hashMap = new HashMap(this.mMap);
            }
            return hashMap;
        }

        public String getString(String key, String defValue) {
            String v;
            synchronized (this) {
                v = (String) this.mMap.get(key);
                if (v == null) {
                    v = defValue;
                }
            }
            return v;
        }

        public int getInt(String key, int defValue) {
            synchronized (this) {
                Integer v = (Integer) this.mMap.get(key);
                if (v != null) {
                    defValue = v.intValue();
                }
            }
            return defValue;
        }

        public long getLong(String key, long defValue) {
            synchronized (this) {
                Long v = (Long) this.mMap.get(key);
                if (v != null) {
                    defValue = v.longValue();
                }
            }
            return defValue;
        }

        public float getFloat(String key, float defValue) {
            synchronized (this) {
                Float v = (Float) this.mMap.get(key);
                if (v != null) {
                    defValue = v.floatValue();
                }
            }
            return defValue;
        }

        public boolean getBoolean(String key, boolean defValue) {
            synchronized (this) {
                Boolean v = (Boolean) this.mMap.get(key);
                if (v != null) {
                    defValue = v.booleanValue();
                }
            }
            return defValue;
        }

        public boolean contains(String key) {
            boolean containsKey;
            synchronized (this) {
                containsKey = this.mMap.containsKey(key);
            }
            return containsKey;
        }

        public MyEditor edit() {
            return new EditorImpl();
        }

        private FileOutputStream createFileOutputStream(File file) {
            FileOutputStream str = null;
            try {
                str = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                if (!file.getParentFile().mkdir()) {
                    return null;
                }
                try {
                    str = new FileOutputStream(file);
                } catch (FileNotFoundException e2) {
                }
            }
            return str;
        }

        private boolean writeFileLocked() {
            if (this.mFile.exists()) {
                if (this.mBackupFile.exists()) {
                    this.mFile.delete();
                } else if (!this.mFile.renameTo(this.mBackupFile)) {
                    return false;
                }
            }
            try {
                FileOutputStream str = createFileOutputStream(this.mFile);
                if (str == null) {
                    return false;
                }
                XmlUtils.writeMapXml(this.mMap, str);
                str.close();
                this.mBackupFile.delete();
                return true;
            } catch (XmlPullParserException e) {
                return (!this.mFile.exists() || this.mFile.delete()) ? false : false;
            } catch (IOException e2) {
                if (!this.mFile.exists()) {
                    return false;
                }
            }
        }
    }

    public TransactionXMLFile(String dir) {
        if (dir == null || dir.length() <= 0) {
            throw new RuntimeException("Directory can not be empty");
        }
        this.mPreferencesDir = new File(dir);
    }

    private File makeFilename(File base, String name) {
        if (name.indexOf(File.separatorChar) < 0) {
            return new File(base, name);
        }
        throw new IllegalArgumentException("File " + name + " contains a path separator");
    }

    private File getPreferencesDir() {
        File file;
        synchronized (this.mSync) {
            file = this.mPreferencesDir;
        }
        return file;
    }

    private File getSharedPrefsFile(String name) {
        return makeFilename(getPreferencesDir(), name + ".xml");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ta.utdid2.core.persistent.MySharedPreferences getMySharedPreferences(java.lang.String r17, int r18) {
        /*
        r16 = this;
        r6 = r16.getSharedPrefsFile(r17);
        r13 = GLOBAL_COMMIT_LOCK;
        monitor-enter(r13);
        r0 = r16;
        r12 = r0.sSharedPrefs;	 Catch:{ all -> 0x005f }
        r8 = r12.get(r6);	 Catch:{ all -> 0x005f }
        r8 = (com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl) r8;	 Catch:{ all -> 0x005f }
        if (r8 == 0) goto L_0x001c;
    L_0x0013:
        r12 = r8.hasFileChanged();	 Catch:{ all -> 0x005f }
        if (r12 != 0) goto L_0x001c;
    L_0x0019:
        monitor-exit(r13);	 Catch:{ all -> 0x005f }
        r9 = r8;
    L_0x001b:
        return r9;
    L_0x001c:
        monitor-exit(r13);	 Catch:{ all -> 0x005f }
        r10 = 0;
        r1 = makeBackupFile(r6);
        r12 = r1.exists();
        if (r12 == 0) goto L_0x002e;
    L_0x0028:
        r6.delete();
        r1.renameTo(r6);
    L_0x002e:
        r12 = r6.exists();
        if (r12 == 0) goto L_0x003a;
    L_0x0034:
        r12 = r6.canRead();
        if (r12 != 0) goto L_0x003a;
    L_0x003a:
        r7 = 0;
        r12 = r6.exists();
        if (r12 == 0) goto L_0x0054;
    L_0x0041:
        r12 = r6.canRead();
        if (r12 == 0) goto L_0x0054;
    L_0x0047:
        r11 = new java.io.FileInputStream;	 Catch:{ XmlPullParserException -> 0x0062, FileNotFoundException -> 0x00b0, IOException -> 0x00ab }
        r11.<init>(r6);	 Catch:{ XmlPullParserException -> 0x0062, FileNotFoundException -> 0x00b0, IOException -> 0x00ab }
        r7 = com.ta.utdid2.core.persistent.XmlUtils.readMapXml(r11);	 Catch:{ XmlPullParserException -> 0x00ba, FileNotFoundException -> 0x00b2, IOException -> 0x00ad }
        r11.close();	 Catch:{ XmlPullParserException -> 0x00ba, FileNotFoundException -> 0x00b2, IOException -> 0x00ad }
        r10 = r11;
    L_0x0054:
        r13 = GLOBAL_COMMIT_LOCK;
        monitor-enter(r13);
        if (r8 == 0) goto L_0x0087;
    L_0x0059:
        r8.replace(r7);	 Catch:{ all -> 0x00a5 }
    L_0x005c:
        monitor-exit(r13);	 Catch:{ all -> 0x00a5 }
        r9 = r8;
        goto L_0x001b;
    L_0x005f:
        r12 = move-exception;
        monitor-exit(r13);	 Catch:{ all -> 0x005f }
        throw r12;
    L_0x0062:
        r3 = move-exception;
        r11 = r10;
    L_0x0064:
        r10 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x0081 }
        r10.<init>(r6);	 Catch:{ FileNotFoundException -> 0x00b7, IOException -> 0x0081 }
        r12 = r10.available();	 Catch:{ FileNotFoundException -> 0x007c, IOException -> 0x00b5 }
        r2 = new byte[r12];	 Catch:{ FileNotFoundException -> 0x007c, IOException -> 0x00b5 }
        r10.read(r2);	 Catch:{ FileNotFoundException -> 0x007c, IOException -> 0x00b5 }
        r12 = new java.lang.String;	 Catch:{ FileNotFoundException -> 0x007c, IOException -> 0x00b5 }
        r13 = 0;
        r14 = r2.length;	 Catch:{ FileNotFoundException -> 0x007c, IOException -> 0x00b5 }
        r15 = "UTF-8";
        r12.<init>(r2, r13, r14, r15);	 Catch:{ FileNotFoundException -> 0x007c, IOException -> 0x00b5 }
        goto L_0x0054;
    L_0x007c:
        r4 = move-exception;
    L_0x007d:
        r4.printStackTrace();
        goto L_0x0054;
    L_0x0081:
        r5 = move-exception;
        r10 = r11;
    L_0x0083:
        r5.printStackTrace();
        goto L_0x0054;
    L_0x0087:
        r0 = r16;
        r12 = r0.sSharedPrefs;	 Catch:{ all -> 0x00a5 }
        r12 = r12.get(r6);	 Catch:{ all -> 0x00a5 }
        r0 = r12;
        r0 = (com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl) r0;	 Catch:{ all -> 0x00a5 }
        r8 = r0;
        if (r8 != 0) goto L_0x005c;
    L_0x0095:
        r9 = new com.ta.utdid2.core.persistent.TransactionXMLFile$MySharedPreferencesImpl;	 Catch:{ all -> 0x00a5 }
        r0 = r18;
        r9.<init>(r6, r0, r7);	 Catch:{ all -> 0x00a5 }
        r0 = r16;
        r12 = r0.sSharedPrefs;	 Catch:{ all -> 0x00a8 }
        r12.put(r6, r9);	 Catch:{ all -> 0x00a8 }
        r8 = r9;
        goto L_0x005c;
    L_0x00a5:
        r12 = move-exception;
    L_0x00a6:
        monitor-exit(r13);	 Catch:{ all -> 0x00a5 }
        throw r12;
    L_0x00a8:
        r12 = move-exception;
        r8 = r9;
        goto L_0x00a6;
    L_0x00ab:
        r12 = move-exception;
        goto L_0x0054;
    L_0x00ad:
        r12 = move-exception;
        r10 = r11;
        goto L_0x0054;
    L_0x00b0:
        r12 = move-exception;
        goto L_0x0054;
    L_0x00b2:
        r12 = move-exception;
        r10 = r11;
        goto L_0x0054;
    L_0x00b5:
        r5 = move-exception;
        goto L_0x0083;
    L_0x00b7:
        r4 = move-exception;
        r10 = r11;
        goto L_0x007d;
    L_0x00ba:
        r3 = move-exception;
        goto L_0x0064;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.core.persistent.TransactionXMLFile.getMySharedPreferences(java.lang.String, int):com.ta.utdid2.core.persistent.MySharedPreferences");
    }

    private static File makeBackupFile(File prefsFile) {
        return new File(prefsFile.getPath() + ".bak");
    }
}
