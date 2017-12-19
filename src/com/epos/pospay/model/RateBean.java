package com.epos.pospay.model;

public class RateBean {
    private String fee_type;
    private String poundagePayer;
    private String poundageRate;
    private String rate;
    private String rate_time;
    private boolean success;

    public String getPoundageRate() {
        return this.poundageRate;
    }

    public void setPoundageRate(String poundageRate) {
        this.poundageRate = poundageRate;
    }

    public String getPoundagePayer() {
        return this.poundagePayer;
    }

    public void setPoundagePayer(String poundagePayer) {
        this.poundagePayer = poundagePayer;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFee_type() {
        return this.fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getRate() {
        return this.rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate_time() {
        return this.rate_time;
    }

    public void setRate_time(String rate_time) {
        this.rate_time = rate_time;
    }
}
