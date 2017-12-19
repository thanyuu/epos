package com.cloudpos.apidemo.function.printer;

import com.cloudpos.apidemo.util.StringUtility;
import java.io.Serializable;

public class PurchaseBillEntity implements Serializable {
    private static final long serialVersionUID = 1;
    private String acqNo;
    private String amout;
    private String authNo;
    private String batchNo;
    private String cardNumber;
    private String dataTime;
    private String expDate;
    private String issNo;
    private String merchantName;
    private String merchantNo;
    private String operator;
    private String refNo;
    private String reference;
    private String terminalNo;
    private String tips;
    private String total;
    private String txnType;
    private String voucherNo;

    public boolean checkValidity() {
        if (StringUtility.isEmpty(this.merchantName) || StringUtility.isEmpty(this.merchantNo) || StringUtility.isEmpty(this.terminalNo) || StringUtility.isEmpty(this.operator) || StringUtility.isEmpty(this.cardNumber) || StringUtility.isEmpty(this.issNo) || StringUtility.isEmpty(this.acqNo) || StringUtility.isEmpty(this.txnType) || StringUtility.isEmpty(this.batchNo) || StringUtility.isEmpty(this.voucherNo) || StringUtility.isEmpty(this.dataTime) || StringUtility.isEmpty(this.refNo) || StringUtility.isEmpty(this.amout)) {
            return false;
        }
        return true;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantNo() {
        return this.merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getTerminalNo() {
        return this.terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getIssNo() {
        return this.issNo;
    }

    public void setIssNo(String issNo) {
        this.issNo = issNo;
    }

    public String getAcqNo() {
        return this.acqNo;
    }

    public void setAcqNo(String acqNo) {
        this.acqNo = acqNo;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getExpDate() {
        return this.expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getBatchNo() {
        return this.batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getVoucherNo() {
        return this.voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getAuthNo() {
        return this.authNo;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }

    public String getDataTime() {
        return this.dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getRefNo() {
        return this.refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getAmout() {
        return this.amout;
    }

    public void setAmout(String amout) {
        this.amout = amout;
    }

    public String getTips() {
        return this.tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
