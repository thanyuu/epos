package com.android.common.utils;

import android.support.v4.internal.view.SupportMenu;

public class UTF8Util {
    private static byte[] outputByte = new byte[3];

    private static int bytes2charsUTF8(byte[] buf, int bufOffset, int bufLength, char[] cbuf, boolean bigEndian) {
        int cpos = 0;
        int pos = bufOffset;
        while (pos < bufOffset + bufLength) {
            byte b1;
            byte b2;
            if ((buf[pos] & 128) == 0) {
                b1 = (byte) 0;
                b2 = buf[pos];
                pos++;
            } else if ((buf[pos] & 224) == 192) {
                if ((buf[pos + 1] & 128) == 128) {
                    b1 = (byte) (((buf[pos] & 31) >> 2) & 255);
                    b2 = (byte) (((buf[pos] & 3) << 6) | ((buf[pos + 1] & 63) & 255));
                    pos += 2;
                } else {
                    b1 = (byte) 0;
                    b2 = (byte) 63;
                    pos++;
                }
            } else if ((buf[pos] & 240) != 224) {
                pos++;
            } else if ((buf[pos + 1] & 128) == 128 && (buf[pos + 2] & 128) == 128) {
                b1 = (byte) ((((buf[pos] & 15) << 4) | ((buf[pos + 1] & 63) >> 2)) & 255);
                b2 = (byte) (((buf[pos + 1] & 3) << 6) | ((buf[pos + 2] & 63) & 255));
                pos += 3;
            } else if ((buf[pos + 1] & 128) == 128) {
                b1 = (byte) 0;
                b2 = (byte) 63;
                pos += 2;
            } else {
                b1 = (byte) 0;
                b2 = (byte) 63;
                pos++;
            }
            if (bigEndian) {
                cbuf[cpos] = (char) ((((b1 & 255) << 8) | (b2 & 255)) & SupportMenu.USER_MASK);
            } else {
                cbuf[cpos] = (char) ((((b2 & 255) << 8) | (b1 & 255)) & SupportMenu.USER_MASK);
            }
            cpos++;
        }
        return cpos;
    }

    private static int bytesUTF8len(byte[] buf, int bufOffset, int bufLength) {
        int len = 0;
        int i = bufOffset;
        while (i < bufOffset + bufLength) {
            if ((buf[i] & 128) == 0 || (buf[i] & 192) == 192) {
                len++;
            }
            i++;
        }
        return len;
    }

    public static String bytes2StringUTF8(byte[] buf, int bufOffset, int bufLength, boolean bigEndian) {
        char[] cbuf = new char[bytesUTF8len(buf, bufOffset, bufLength)];
        return new String(cbuf, 0, bytes2charsUTF8(buf, bufOffset, bufLength, cbuf, bigEndian));
    }

    public static String bytes2StringUTF8(byte[] buf) {
        return bytes2StringUTF8(buf, 0, buf.length, true);
    }

    public static byte[] string2BytesUTF8(String str) {
        byte[] bufByte = new byte[(str.length() * 3)];
        int byteLen = char2ByteUTF8(str, 0, str.length(), bufByte, 0, bufByte.length, false);
        byte[] ret = new byte[byteLen];
        System.arraycopy(bufByte, 0, ret, 0, byteLen);
        return ret;
    }

    public static int char2ByteUTF8(String input, int inOff, int inEnd, byte[] output, int outOff, int outEnd, boolean getLengthFlag) {
        int byteOff = outOff;
        for (int charOff = inOff; charOff < inEnd; charOff++) {
            int outputSize;
            char inputChar = input.charAt(charOff);
            if (inputChar < '') {
                outputByte[0] = (byte) inputChar;
                outputSize = 1;
            } else if (inputChar < 'ࠀ') {
                outputByte[0] = (byte) (((inputChar >> 6) & 31) | 192);
                outputByte[1] = (byte) ((inputChar & 63) | 128);
                outputSize = 2;
            } else {
                outputByte[0] = (byte) (((inputChar >> 12) & 15) | 224);
                outputByte[1] = (byte) (((inputChar >> 6) & 63) | 128);
                outputByte[2] = (byte) ((inputChar & 63) | 128);
                outputSize = 3;
            }
            if (getLengthFlag) {
                byteOff += outputSize;
            } else if (byteOff + outputSize > outEnd) {
                return -1;
            } else {
                int i = 0;
                int byteOff2 = byteOff;
                while (i < outputSize) {
                    byteOff = byteOff2 + 1;
                    output[byteOff2] = outputByte[i];
                    i++;
                    byteOff2 = byteOff;
                }
                byteOff = byteOff2;
            }
        }
        return byteOff - outOff;
    }

    public static String bytes2StringUNICODE(byte[] buf, int offset, int length, boolean bigEndian) {
        if (buf == null || offset < 0 || length < 2 || buf.length < offset + length) {
            return null;
        }
        int charsLen = length / 2;
        char[] cbuf = new char[charsLen];
        for (int i = 0; i < charsLen; i++) {
            if (bigEndian) {
                cbuf[i] = (char) ((((buf[(i * 2) + offset] & 255) << 8) | (buf[((i * 2) + 1) + offset] & 255)) & SupportMenu.USER_MASK);
            } else {
                cbuf[i] = (char) ((((buf[((i * 2) + 1) + offset] & 255) << 8) | (buf[(i * 2) + offset] & 255)) & SupportMenu.USER_MASK);
            }
        }
        return new String(cbuf, 0, charsLen);
    }
}
