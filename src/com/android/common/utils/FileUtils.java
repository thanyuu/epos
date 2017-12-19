package com.android.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static final String FILE_EXTENSION_SEPARATOR = ".";

    public static byte[] readFile(String filePath) {
        IOException e;
        Throwable th;
        File file = new File(filePath);
        if (file == null || !file.isFile()) {
            return null;
        }
        FileInputStream in = null;
        try {
            FileInputStream in2 = new FileInputStream(file);
            try {
                byte[] contentBuffer = new byte[in2.available()];
                in2.read(contentBuffer);
                if (in2 == null) {
                    return contentBuffer;
                }
                try {
                    in2.close();
                    return contentBuffer;
                } catch (IOException e2) {
                    throw new RuntimeException("IOException occurred. ", e2);
                }
            } catch (IOException e3) {
                e2 = e3;
                in = in2;
                try {
                    throw new RuntimeException("IOException occurred. ", e2);
                } catch (Throwable th2) {
                    th = th2;
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e22) {
                            throw new RuntimeException("IOException occurred. ", e22);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                in = in2;
                if (in != null) {
                    in.close();
                }
                throw th;
            }
        } catch (IOException e4) {
            e22 = e4;
            throw new RuntimeException("IOException occurred. ", e22);
        }
    }

    public static StringBuilder readFile(String filePath, String charsetName) {
        IOException e;
        Throwable th;
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (file == null || !file.isFile()) {
            return null;
        }
        BufferedReader bufferedReader = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
            while (true) {
                try {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (!fileContent.toString().equals("")) {
                        fileContent.append("\r\n");
                    }
                    fileContent.append(line);
                } catch (IOException e2) {
                    e = e2;
                    bufferedReader = reader;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = reader;
                }
            }
            reader.close();
            if (reader == null) {
                return fileContent;
            }
            try {
                reader.close();
                return fileContent;
            } catch (IOException e3) {
                throw new RuntimeException("IOException occurred. ", e3);
            }
        } catch (IOException e4) {
            e3 = e4;
            try {
                throw new RuntimeException("IOException occurred. ", e3);
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e32) {
                        throw new RuntimeException("IOException occurred. ", e32);
                    }
                }
                throw th;
            }
        }
    }

    public static List<String> getFileListName(String imagePath, String[] charsets) {
        if (!isDirectoryExist(imagePath)) {
            return null;
        }
        List<String> list = new ArrayList();
        for (String s : new File(imagePath).list()) {
            String[] strs = s.split("\\.");
            if (strs.length >= 2) {
                for (String charset : charsets) {
                    if (strs[1].toUpperCase().equals(charset)) {
                        list.add(s);
                        break;
                    }
                }
            }
        }
        return list;
    }

    public static boolean writeFile(String filePath, String content, boolean append) {
        IOException e;
        Throwable th;
        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            FileWriter fileWriter2 = new FileWriter(filePath, append);
            try {
                fileWriter2.write(content);
                fileWriter2.close();
                if (fileWriter2 != null) {
                    try {
                        fileWriter2.close();
                    } catch (IOException e2) {
                        throw new RuntimeException("IOException occurred. ", e2);
                    }
                }
                return true;
            } catch (IOException e3) {
                e2 = e3;
                fileWriter = fileWriter2;
                try {
                    throw new RuntimeException("IOException occurred. ", e2);
                } catch (Throwable th2) {
                    th = th2;
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (IOException e22) {
                            throw new RuntimeException("IOException occurred. ", e22);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileWriter = fileWriter2;
                if (fileWriter != null) {
                    fileWriter.close();
                }
                throw th;
            }
        } catch (IOException e4) {
            e22 = e4;
            throw new RuntimeException("IOException occurred. ", e22);
        }
    }

    public static boolean writeFile(String filePath, InputStream stream) {
        return writeFile(filePath, stream, false);
    }

    public static boolean writeFile(String filePath, InputStream stream, boolean append) {
        return writeFile(filePath != null ? new File(filePath) : null, stream, append);
    }

    public static boolean writeFile(File file, InputStream stream) {
        return writeFile(file, stream, false);
    }

    public static boolean writeFile(File file, InputStream stream, boolean append) {
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        OutputStream outputStream = null;
        try {
            makeDirs(file.getAbsolutePath());
            OutputStream o = new FileOutputStream(file, append);
            try {
                byte[] data = new byte[1024];
                while (true) {
                    int length = stream.read(data);
                    if (length == -1) {
                        break;
                    }
                    o.write(data, 0, length);
                }
                o.flush();
                if (o != null) {
                    try {
                        o.close();
                        stream.close();
                    } catch (IOException e3) {
                        throw new RuntimeException("IOException occurred. ", e3);
                    }
                }
                return true;
            } catch (FileNotFoundException e4) {
                e2 = e4;
                outputStream = o;
            } catch (IOException e5) {
                e3 = e5;
                outputStream = o;
            } catch (Throwable th2) {
                th = th2;
                outputStream = o;
            }
        } catch (FileNotFoundException e6) {
            e2 = e6;
            try {
                throw new RuntimeException("FileNotFoundException occurred. ", e2);
            } catch (Throwable th3) {
                th = th3;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                        stream.close();
                    } catch (IOException e32) {
                        throw new RuntimeException("IOException occurred. ", e32);
                    }
                }
                throw th;
            }
        } catch (IOException e7) {
            e32 = e7;
            throw new RuntimeException("IOException occurred. ", e32);
        }
    }

    public static boolean copyFile(String sourceFilePath, String destFilePath) {
        try {
            return writeFile(destFilePath, new FileInputStream(sourceFilePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        }
    }

    public static boolean copyByStream(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] bs = new byte[4096];
            while (true) {
                int len = inputStream.read(bs);
                if (len == -1) {
                    return true;
                }
                outputStream.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint({"WorldReadableFiles"})
    public static void copy2Local(String zipFilePath, Context host) {
        try {
            InputStream inputStream = host.getAssets().open(zipFilePath);
            FileOutputStream outputStream = host.openFileOutput(zipFilePath, 3);
            copyByStream(inputStream, outputStream);
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean copyDirs(String sourceDirsPath, String destDirsPath) {
        int i = 0;
        if (!isDirectoryExist(sourceDirsPath)) {
            return false;
        }
        File files = new File(sourceDirsPath);
        if (files.list() == null) {
            return false;
        }
        String[] list = files.list();
        int length = list.length;
        while (i < length) {
            String s = list[i];
            String[] strs = s.split("\\.");
            if (strs.length >= 2 && (strs[1].toUpperCase().equals("PNG") || strs[1].toUpperCase().equals("JPG"))) {
                copyFile(new StringBuilder(String.valueOf(sourceDirsPath)).append(s).toString(), new StringBuilder(String.valueOf(destDirsPath)).append(s).toString());
            }
            i++;
        }
        return true;
    }

    public static List<String> readFileToList(String filePath, String charsetName) {
        IOException e;
        Throwable th;
        File file = new File(filePath);
        List<String> fileContent = new ArrayList();
        if (file == null || !file.isFile()) {
            return null;
        }
        BufferedReader bufferedReader = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
            while (true) {
                try {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    fileContent.add(line);
                } catch (IOException e2) {
                    e = e2;
                    bufferedReader = reader;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = reader;
                }
            }
            reader.close();
            if (reader == null) {
                return fileContent;
            }
            try {
                reader.close();
                return fileContent;
            } catch (IOException e3) {
                throw new RuntimeException("IOException occurred. ", e3);
            }
        } catch (IOException e4) {
            e3 = e4;
            try {
                throw new RuntimeException("IOException occurred. ", e3);
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e32) {
                        throw new RuntimeException("IOException occurred. ", e32);
                    }
                }
                throw th;
            }
        }
    }

    public static String getFileNameWithoutExtension(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }
        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            if (extenPosi != -1) {
                return filePath.substring(0, extenPosi);
            }
            return filePath;
        } else if (extenPosi == -1) {
            return filePath.substring(filePosi + 1);
        } else {
            return filePosi < extenPosi ? filePath.substring(filePosi + 1, extenPosi) : filePath.substring(filePosi + 1);
        }
    }

    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }
        int filePosi = filePath.lastIndexOf(File.separator);
        return filePosi != -1 ? filePath.substring(filePosi + 1) : filePath;
    }

    public static String getFolderName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }
        int filePosi = filePath.lastIndexOf(File.separator);
        return filePosi == -1 ? "" : filePath.substring(0, filePosi);
    }

    public static String getFileExtension(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return filePath;
        }
        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (extenPosi == -1) {
            return "";
        }
        return filePosi >= extenPosi ? "" : filePath.substring(extenPosi + 1);
    }

    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (StringUtils.isEmpty(folderName)) {
            return false;
        }
        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

    public static boolean makeFolders(String filePath) {
        return makeDirs(filePath);
    }

    public static boolean isFileExist(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return false;
        }
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }

    public static boolean isDirectoryExist(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return false;
        }
        return new File(filePath).isDirectory();
    }

    public static boolean isFolderExist(String directoryPath) {
        if (StringUtils.isBlank(directoryPath)) {
            return false;
        }
        File dire = new File(directoryPath);
        if (dire.exists() && dire.isDirectory()) {
            return true;
        }
        return false;
    }

    public static boolean deleteFile(String path) {
        int i = 0;
        if (StringUtils.isBlank(path)) {
            return true;
        }
        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        int length = listFiles.length;
        while (i < length) {
            File f = listFiles[i];
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
            i++;
        }
        return file.delete();
    }

    public static long getFileSize(String path) {
        if (StringUtils.isBlank(path)) {
            return -1;
        }
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            return file.length();
        }
        return -1;
    }

    public static Reader getReaderFromFiles(Context host, String fileName) {
        Reader bufReader;
        Exception e;
        try {
            FileInputStream fin = host.openFileInput(fileName);
            if (fin == null) {
                return null;
            }
            int length = fin.available();
            byte[] buffer = new byte[length];
            if (fin.read(buffer) != length) {
                return null;
            }
            Reader bufReader2 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(buffer)));
            try {
                fin.close();
                bufReader = bufReader2;
            } catch (Exception e2) {
                e = e2;
                bufReader = bufReader2;
                e.printStackTrace();
                bufReader = null;
                return bufReader;
            }
            return bufReader;
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            bufReader = null;
            return bufReader;
        }
    }

    public static Reader getReaderFromAssets(Activity host, String settingName) throws Exception {
        return new BufferedReader(new InputStreamReader(host.getResources().getAssets().open(settingName)));
    }

    public static Reader getReaderFromPath(String path, String fileName) throws Exception {
        File favFile = new File(Environment.getRootDirectory().getAbsolutePath(), fileName);
        if (favFile.exists()) {
            return new FileReader(favFile);
        }
        return null;
    }

    public static Reader getReaderFromPath(String path) {
        File favFile = new File(path);
        if (!favFile.exists()) {
            return null;
        }
        try {
            return new FileReader(favFile);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
