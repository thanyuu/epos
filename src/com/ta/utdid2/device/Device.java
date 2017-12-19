package com.ta.utdid2.device;

public class Device {
    private String deviceId = "";
    private String imei = "";
    private String imsi = "";
    private long mCheckSum = 0;
    private long mCreateTimestamp = 0;
    private String utdid = "";

    long getCheckSum() {
        return this.mCheckSum;
    }

    void setCheckSum(long mCheckSum) {
        this.mCheckSum = mCheckSum;
    }

    long getCreateTimestamp() {
        return this.mCreateTimestamp;
    }

    void setCreateTimestamp(long mCreateTimestamp) {
        this.mCreateTimestamp = mCreateTimestamp;
    }

    public String getImei() {
        return this.imei;
    }

    void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return this.imsi;
    }

    void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUtdid() {
        return this.utdid;
    }

    void setUtdid(String utdid) {
        this.utdid = utdid;
    }
}
