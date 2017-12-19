package com.cloudpos.apidemo.function.idcard;

import android.graphics.Bitmap;

public class IDCard {
    private String IDCardNo;
    private String address;
    private String born;
    private String grantDept;
    private String name;
    private String nation;
    private final String[] nations = new String[]{"解码错", "汉", "蒙古", "回", "藏", "维吾尔", "苗", "彝", "壮", "布依", "朝鲜", "满", "侗", "瑶", "白", "土家", "哈尼", "哈萨克", "傣", "黎", "傈僳", "佤", "畲", "高山", "拉祜", "水", "东乡", "纳西", "景颇", "柯尔克孜", "土", "达斡尔", "仫佬", "羌", "布朗", "撒拉", "毛南", "仡佬", "锡伯", "阿昌", "普米", "塔吉克", "怒", "乌孜别克", "俄罗斯", "鄂温克", "德昴", "保安", "裕固", "京", "塔塔尔", "独龙", "鄂伦春", "赫哲", "门巴", "珞巴", "基诺", "编码错", "其他", "外国血统"};
    private Bitmap picture;
    private String reserved;
    private String sex;
    private String validFromDate;
    private String validToDate;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        int nationcode = Integer.parseInt(this.nation);
        if (nationcode >= 1 && nationcode <= 56) {
            this.nation = this.nations[nationcode];
        } else if (nationcode == 97) {
            this.nation = "其他";
        } else if (nationcode == 98) {
            this.nation = "外国血统";
        } else {
            this.nation = "编码错";
        }
        return this.nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBorn() {
        return this.born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIDCardNo() {
        return this.IDCardNo;
    }

    public void setIDCardNo(String IDCardNo) {
        this.IDCardNo = IDCardNo;
    }

    public String getGrantDept() {
        return this.grantDept;
    }

    public void setGrantDept(String grantDept) {
        this.grantDept = grantDept;
    }

    public String getValidFromDate() {
        return this.validFromDate;
    }

    public void setValidFromDate(String validFromDate) {
        this.validFromDate = validFromDate;
    }

    public String getValidToDate() {
        return this.validToDate;
    }

    public void setValidToDate(String validToDate) {
        this.validToDate = validToDate;
    }

    public String getReserved() {
        return this.reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public Bitmap getPicture() {
        return this.picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}
