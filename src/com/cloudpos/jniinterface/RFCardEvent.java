package com.cloudpos.jniinterface;

public class RFCardEvent {
    public static int nMaxEventDataLength = 255;
    public byte[] arryEventData = new byte[nMaxEventDataLength];
    public int nEventDataLength = 0;
    public int nEventID = -1;
}
