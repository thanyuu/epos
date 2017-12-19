package com.cloudpos.apidemo.function.printer;

import com.cloudpos.apidemo.util.StringUtility;
import java.io.Serializable;

public class PurchaseBillForQ1Entity implements Serializable {
    private static final long serialVersionUID = 1;
    private String amout;
    private String cardNo;
    private String dataTimeAndExpDate;
    private String issuerAndAcquirer;
    private String merchantName;
    private String merchantNo;
    private String refNoAndBatchNo;
    private String reference;
    private String terminalNoAndOperator;
    private String transType;
    private String voucherAndAuthNo;

    public String getIssuerAndAcquirer() {
        return this.issuerAndAcquirer;
    }

    public void setIssuerAndAcquirer(String issuerAndAcquirer) {
        this.issuerAndAcquirer = issuerAndAcquirer;
    }

    public String getDataTimeAndExpDate() {
        return this.dataTimeAndExpDate;
    }

    public void setDataTimeAndExpDate(String dataTimeAndExpDate) {
        this.dataTimeAndExpDate = dataTimeAndExpDate;
    }

    public String getRefNoAndBatchNo() {
        return this.refNoAndBatchNo;
    }

    public void setRefNoAndBatchNo(String refNoAndBatchNo) {
        this.refNoAndBatchNo = refNoAndBatchNo;
    }

    public String getVoucherAndAuthNo() {
        return this.voucherAndAuthNo;
    }

    public void setVoucherAndAuthNo(String voucherAndAuthNo) {
        this.voucherAndAuthNo = voucherAndAuthNo;
    }

    public boolean checkValidity() {
        if (StringUtility.isEmpty(this.merchantName) || StringUtility.isEmpty(this.merchantNo) || StringUtility.isEmpty(this.terminalNoAndOperator) || StringUtility.isEmpty(this.cardNo) || StringUtility.isEmpty(this.transType) || StringUtility.isEmpty(this.dataTimeAndExpDate) || StringUtility.isEmpty(this.refNoAndBatchNo) || StringUtility.isEmpty(this.voucherAndAuthNo) || StringUtility.isEmpty(this.amout)) {
            return false;
        }
        return true;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getTransType() {
        return this.transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public static long getSerialversionuid() {
        return 1;
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

    public String getTerminalNoAndOperator() {
        return this.terminalNoAndOperator;
    }

    public void setTerminalNoAndOperator(String terminalNoAndOperator) {
        this.terminalNoAndOperator = terminalNoAndOperator;
    }

    public String getAmout() {
        return this.amout;
    }

    public void setAmout(String amout) {
        this.amout = amout;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
