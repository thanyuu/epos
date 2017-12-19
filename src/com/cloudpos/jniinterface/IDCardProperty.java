package com.cloudpos.jniinterface;

public class IDCardProperty {
    public byte[] strAddress = new byte[70];
    public byte[] strBorn = new byte[16];
    public byte[] strGrantDept = new byte[30];
    public byte[] strIDCardNo = new byte[36];
    public byte[] strName = new byte[30];
    public byte[] strNation = new byte[4];
    public byte[] strPicture = new byte[1024];
    public byte[] strReserved = new byte[36];
    public byte[] strSex = new byte[2];
    public byte[] strUserLifeBegin = new byte[16];
    public byte[] strUserLifeEnd = new byte[16];
}
