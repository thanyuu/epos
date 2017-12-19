package com.android.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtils {
    public static Object deserialization(String filePath) {
        IOException e;
        FileNotFoundException e2;
        ClassNotFoundException e3;
        Throwable th;
        ObjectInputStream in = null;
        try {
            ObjectInputStream in2 = new ObjectInputStream(new FileInputStream(filePath));
            try {
                Object o = in2.readObject();
                in2.close();
                if (in2 != null) {
                    try {
                        in2.close();
                    } catch (IOException e4) {
                        throw new RuntimeException("IOException occurred. ", e4);
                    }
                }
                return o;
            } catch (FileNotFoundException e5) {
                e2 = e5;
                in = in2;
                throw new RuntimeException("FileNotFoundException occurred. ", e2);
            } catch (ClassNotFoundException e6) {
                e3 = e6;
                in = in2;
                throw new RuntimeException("ClassNotFoundException occurred. ", e3);
            } catch (IOException e7) {
                e4 = e7;
                in = in2;
                throw new RuntimeException("IOException occurred. ", e4);
            } catch (Throwable th2) {
                th = th2;
                in = in2;
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e42) {
                        throw new RuntimeException("IOException occurred. ", e42);
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e8) {
            e2 = e8;
            throw new RuntimeException("FileNotFoundException occurred. ", e2);
        } catch (ClassNotFoundException e9) {
            e3 = e9;
            throw new RuntimeException("ClassNotFoundException occurred. ", e3);
        } catch (IOException e10) {
            e42 = e10;
            throw new RuntimeException("IOException occurred. ", e42);
        } catch (Throwable th3) {
            th = th3;
            if (in != null) {
                in.close();
            }
            throw th;
        }
    }

    public static void serialization(String filePath, Object obj) {
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        ObjectOutputStream objectOutputStream = null;
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
            try {
                out.writeObject(obj);
                out.close();
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e3) {
                        throw new RuntimeException("IOException occurred. ", e3);
                    }
                }
            } catch (FileNotFoundException e4) {
                e2 = e4;
                objectOutputStream = out;
                try {
                    throw new RuntimeException("FileNotFoundException occurred. ", e2);
                } catch (Throwable th2) {
                    th = th2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e32) {
                            throw new RuntimeException("IOException occurred. ", e32);
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e32 = e5;
                objectOutputStream = out;
                throw new RuntimeException("IOException occurred. ", e32);
            } catch (Throwable th3) {
                th = th3;
                objectOutputStream = out;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e6) {
            e2 = e6;
            throw new RuntimeException("FileNotFoundException occurred. ", e2);
        } catch (IOException e7) {
            e32 = e7;
            throw new RuntimeException("IOException occurred. ", e32);
        }
    }
}
