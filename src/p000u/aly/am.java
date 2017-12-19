package p000u.aly;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/* compiled from: ObjectSerializer */
public class am {
    public static String m192a(Serializable serializable) {
        if (serializable == null) {
            return "";
        }
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            return am.m193a(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object m191a(String str) {
        Object obj = null;
        if (!(str == null || str.length() == 0)) {
            try {
                obj = new ObjectInputStream(new ByteArrayInputStream(am.m194b(str))).readObject();
            } catch (Exception e) {
            }
        }
        return obj;
    }

    public static String m193a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append((char) (((bArr[i] >> 4) & 15) + 97));
            stringBuffer.append((char) ((bArr[i] & 15) + 97));
        }
        return stringBuffer.toString();
    }

    public static byte[] m194b(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length(); i += 2) {
            bArr[i / 2] = (byte) ((str.charAt(i) - 97) << 4);
            int i2 = i / 2;
            bArr[i2] = (byte) ((str.charAt(i + 1) - 97) + bArr[i2]);
        }
        return bArr;
    }
}
