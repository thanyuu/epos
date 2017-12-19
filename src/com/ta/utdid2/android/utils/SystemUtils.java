package com.ta.utdid2.android.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SystemUtils {
    public static String getCpuInfo() {
        BufferedReader localBufferedReader;
        IOException localIOException;
        FileReader fileReader;
        FileNotFoundException localFileNotFoundException;
        BufferedReader bufferedReader;
        String str = null;
        try {
            FileReader localFileReader = new FileReader("/proc/cpuinfo");
            if (localFileReader != null) {
                try {
                    localBufferedReader = new BufferedReader(localFileReader, 1024);
                } catch (IOException e) {
                    localIOException = e;
                    try {
                        Log.e("Could not read from file /proc/cpuinfo", localIOException.toString());
                        fileReader = localFileReader;
                    } catch (FileNotFoundException e2) {
                        localFileNotFoundException = e2;
                        fileReader = localFileReader;
                        Log.e("BaseParameter-Could not open file /proc/cpuinfo", localFileNotFoundException.toString());
                        if (str == null) {
                            return "";
                        }
                        return str.substring(str.indexOf(58) + 1).trim();
                    }
                    if (str == null) {
                        return str.substring(str.indexOf(58) + 1).trim();
                    }
                    return "";
                }
                try {
                    str = localBufferedReader.readLine();
                    localBufferedReader.close();
                    localFileReader.close();
                    bufferedReader = localBufferedReader;
                } catch (IOException e3) {
                    localIOException = e3;
                    bufferedReader = localBufferedReader;
                    Log.e("Could not read from file /proc/cpuinfo", localIOException.toString());
                    fileReader = localFileReader;
                    if (str == null) {
                        return "";
                    }
                    return str.substring(str.indexOf(58) + 1).trim();
                } catch (FileNotFoundException e4) {
                    localFileNotFoundException = e4;
                    bufferedReader = localBufferedReader;
                    fileReader = localFileReader;
                    Log.e("BaseParameter-Could not open file /proc/cpuinfo", localFileNotFoundException.toString());
                    if (str == null) {
                        return str.substring(str.indexOf(58) + 1).trim();
                    }
                    return "";
                }
            }
            fileReader = localFileReader;
        } catch (FileNotFoundException e5) {
            localFileNotFoundException = e5;
            Log.e("BaseParameter-Could not open file /proc/cpuinfo", localFileNotFoundException.toString());
            if (str == null) {
                return "";
            }
            return str.substring(str.indexOf(58) + 1).trim();
        }
        if (str == null) {
            return str.substring(str.indexOf(58) + 1).trim();
        }
        return "";
    }

    public static int getSystemVersion() {
        try {
            return VERSION.class.getField("SDK_INT").getInt(null);
        } catch (Exception e) {
            try {
                return Integer.parseInt((String) VERSION.class.getField("SDK").get(null));
            } catch (Exception e2) {
                e2.printStackTrace();
                return 2;
            }
        }
    }

    public static File getRootFolder(String folderName) {
        if (Environment.getExternalStorageDirectory() == null) {
            return null;
        }
        File rootFolder = new File(String.format("%s%s%s", new Object[]{Environment.getExternalStorageDirectory().getAbsolutePath(), File.separator, folderName}));
        if (rootFolder == null || rootFolder.exists()) {
            return rootFolder;
        }
        rootFolder.mkdirs();
        return rootFolder;
    }

    public static String getAppLabel(Context pContext) {
        try {
            PackageManager lPM = pContext.getPackageManager();
            String lPackageName = pContext.getPackageName();
            if (!(lPM == null || lPackageName == null)) {
                return lPM.getApplicationLabel(lPM.getPackageInfo(lPackageName, 1).applicationInfo).toString();
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
