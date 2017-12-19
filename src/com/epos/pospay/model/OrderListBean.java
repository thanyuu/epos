package com.epos.pospay.model;

import java.util.List;

public class OrderListBean {

    public class List1 {
        private float amountNZD;
        private float amountRMB;
        private String cashFeeType;
        private String currencyType;
        private String currencyTypeSymbol;
        private double discount;
        private double exchangeRate;
        private int id;
        private String ipAddress;
        private String terminalNum;
        private String timeEnd;
        private String transactionDateNZ;
        private String transactionNum;
        private String transactionNumUnion;
        private int transactionOrg;
        private String transactionOrgName;
        private String transactionState;
        private String transactionStateRemark;
        private String transactionType;

        public String getTransactionNumUnion() {
            return this.transactionNumUnion;
        }

        public List1 setTransactionNumUnion(String transactionNumUnion) {
            this.transactionNumUnion = transactionNumUnion;
            return this;
        }

        public String getCurrencyTypeSymbol() {
            return this.currencyTypeSymbol;
        }

        public void setCurrencyTypeSymbol(String currencyTypeSymbol) {
            this.currencyTypeSymbol = currencyTypeSymbol;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setTransactionType(String transactionType) {
            this.transactionType = transactionType;
        }

        public String getTransactionType() {
            return this.transactionType;
        }

        public void setAmountNZD(float amountNZD) {
            this.amountNZD = amountNZD;
        }

        public float getAmountNZD() {
            return this.amountNZD;
        }

        public void setAmountRMB(float amountRMB) {
            this.amountRMB = amountRMB;
        }

        public float getAmountRMB() {
            return this.amountRMB;
        }

        public void setTransactionDateNZ(String transactionDateNZ) {
            this.transactionDateNZ = transactionDateNZ;
        }

        public String getTransactionDateNZ() {
            return this.transactionDateNZ;
        }

        public void setCashFeeType(String cashFeeType) {
            this.cashFeeType = cashFeeType;
        }

        public String getCashFeeType() {
            return this.cashFeeType;
        }

        public void setCurrencyType(String currencyType) {
            this.currencyType = currencyType;
        }

        public String getCurrencyType() {
            return this.currencyType;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public double getDiscount() {
            return this.discount;
        }

        public void setExchangeRate(double exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public double getExchangeRate() {
            return this.exchangeRate;
        }

        public void setTimeEnd(String timeEnd) {
            this.timeEnd = timeEnd;
        }

        public String getTimeEnd() {
            return this.timeEnd;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public String getIpAddress() {
            return this.ipAddress;
        }

        public void setTerminalNum(String terminalNum) {
            this.terminalNum = terminalNum;
        }

        public String getTerminalNum() {
            return this.terminalNum;
        }

        public void setTransactionNum(String transactionNum) {
            this.transactionNum = transactionNum;
        }

        public String getTransactionNum() {
            return this.transactionNum;
        }

        public void setTransactionOrg(int transactionOrg) {
            this.transactionOrg = transactionOrg;
        }

        public int getTransactionOrg() {
            return this.transactionOrg;
        }

        public void setTransactionOrgName(String transactionOrgName) {
            this.transactionOrgName = transactionOrgName;
        }

        public String getTransactionOrgName() {
            return this.transactionOrgName;
        }

        public void setTransactionState(String transactionState) {
            this.transactionState = transactionState;
        }

        public String getTransactionState() {
            return this.transactionState;
        }

        public void setTransactionStateRemark(String transactionStateRemark) {
            this.transactionStateRemark = transactionStateRemark;
        }

        public String getTransactionStateRemark() {
            return this.transactionStateRemark;
        }
    }

    public class Root {
        private List<List1> list;
        private String message;
        private String messageCode;
        private boolean success;
        private String total;

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean getSuccess() {
            return this.success;
        }

        public void setMessageCode(String messageCode) {
            this.messageCode = messageCode;
        }

        public String getMessageCode() {
            return this.messageCode;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getTotal() {
            return this.total;
        }

        public void setList(List<List1> list) {
            this.list = list;
        }

        public List<List1> getList() {
            return this.list;
        }
    }
}
