package com.epos.pospay.model;

import java.io.Serializable;

public class CheckSuccessBean implements Serializable {
    private String message;
    private String messageCode;
    private OrderInfoBean orderInfo;
    private boolean success;

    public static class OrderInfoBean implements Serializable {
        private double amountNZD;
        private double amountRMB;
        private String cashFeeType;
        private float couponFee;
        private String currencyType;
        private double discount;
        private double exchangeRate;
        private int id;
        private String ipAddress;
        private float price;
        private float refundTotal;
        private String terminalNum;
        private String timeEnd;
        private String transactionDateCN;
        private String transactionDateNZ;
        private String transactionId;
        private String transactionNum;
        private String transactionNumUnion;
        private TransactionOrgBean transactionOrg;
        private String transactionState;
        private String transactionStateRemark;
        private String transactionType;
        private TransactionUserBean transactionUser;

        public static class TransactionOrgBean implements Serializable {
            private String adQrcode;
            private String adRemark;
            private Object agent;
            private String contactPerson;
            private String contactPersonEmail;
            private String contactPersonTel;
            private String director;
            private String nzNumber;
            private String orgAddress;
            private String orgDesc;
            private String orgDistrict;
            private String orgName;
            private String orgRemark;
            private String orgShortName;
            private String serviceTel;
            private String state;
            private String webSite;

            public String getAdQrcode() {
                return this.adQrcode;
            }

            public TransactionOrgBean setAdQrcode(String adQrcode) {
                this.adQrcode = adQrcode;
                return this;
            }

            public String getAdRemark() {
                return this.adRemark;
            }

            public TransactionOrgBean setAdRemark(String adRemark) {
                this.adRemark = adRemark;
                return this;
            }

            public String getOrgName() {
                return this.orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public String getOrgShortName() {
                return this.orgShortName;
            }

            public void setOrgShortName(String orgShortName) {
                this.orgShortName = orgShortName;
            }

            public String getOrgDistrict() {
                return this.orgDistrict;
            }

            public void setOrgDistrict(String orgDistrict) {
                this.orgDistrict = orgDistrict;
            }

            public String getOrgAddress() {
                return this.orgAddress;
            }

            public void setOrgAddress(String orgAddress) {
                this.orgAddress = orgAddress;
            }

            public String getOrgDesc() {
                return this.orgDesc;
            }

            public void setOrgDesc(String orgDesc) {
                this.orgDesc = orgDesc;
            }

            public String getWebSite() {
                return this.webSite;
            }

            public void setWebSite(String webSite) {
                this.webSite = webSite;
            }

            public String getServiceTel() {
                return this.serviceTel;
            }

            public void setServiceTel(String serviceTel) {
                this.serviceTel = serviceTel;
            }

            public String getContactPerson() {
                return this.contactPerson;
            }

            public void setContactPerson(String contactPerson) {
                this.contactPerson = contactPerson;
            }

            public String getContactPersonTel() {
                return this.contactPersonTel;
            }

            public void setContactPersonTel(String contactPersonTel) {
                this.contactPersonTel = contactPersonTel;
            }

            public String getContactPersonEmail() {
                return this.contactPersonEmail;
            }

            public void setContactPersonEmail(String contactPersonEmail) {
                this.contactPersonEmail = contactPersonEmail;
            }

            public String getOrgRemark() {
                return this.orgRemark;
            }

            public void setOrgRemark(String orgRemark) {
                this.orgRemark = orgRemark;
            }

            public String getDirector() {
                return this.director;
            }

            public void setDirector(String director) {
                this.director = director;
            }

            public String getNzNumber() {
                return this.nzNumber;
            }

            public void setNzNumber(String nzNumber) {
                this.nzNumber = nzNumber;
            }

            public String getState() {
                return this.state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public Object getAgent() {
                return this.agent;
            }

            public void setAgent(Object agent) {
                this.agent = agent;
            }
        }

        public static class TransactionUserBean implements Serializable {
            private String loginName;
            private String mobile;
            private String nickname;
            private String telephone;

            public String getLoginName() {
                return this.loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getNickname() {
                return this.nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getTelephone() {
                return this.telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getMobile() {
                return this.mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }
        }

        public float getPrice() {
            return this.price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public float getRefundTotal() {
            return this.refundTotal;
        }

        public void setRefundTotal(float refundTotal) {
            this.refundTotal = refundTotal;
        }

        public float getCouponFee() {
            return this.couponFee;
        }

        public void setCouponFee(float couponFee) {
            this.couponFee = couponFee;
        }

        public String getTransactionNumUnion() {
            return this.transactionNumUnion;
        }

        public OrderInfoBean setTransactionNumUnion(String transactionNumUnion) {
            this.transactionNumUnion = transactionNumUnion;
            return this;
        }

        public String getTransactionId() {
            return this.transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTransactionType() {
            return this.transactionType;
        }

        public void setTransactionType(String transactionType) {
            this.transactionType = transactionType;
        }

        public double getAmountNZD() {
            return this.amountNZD;
        }

        public void setAmountNZD(double amountNZD) {
            this.amountNZD = amountNZD;
        }

        public double getAmountRMB() {
            return this.amountRMB;
        }

        public void setAmountRMB(double amountRMB) {
            this.amountRMB = amountRMB;
        }

        public String getTransactionDateCN() {
            return this.transactionDateCN;
        }

        public void setTransactionDateCN(String transactionDateCN) {
            this.transactionDateCN = transactionDateCN;
        }

        public String getTransactionDateNZ() {
            return this.transactionDateNZ;
        }

        public void setTransactionDateNZ(String transactionDateNZ) {
            this.transactionDateNZ = transactionDateNZ;
        }

        public String getCashFeeType() {
            return this.cashFeeType;
        }

        public void setCashFeeType(String cashFeeType) {
            this.cashFeeType = cashFeeType;
        }

        public String getCurrencyType() {
            return this.currencyType;
        }

        public void setCurrencyType(String currencyType) {
            this.currencyType = currencyType;
        }

        public double getDiscount() {
            return this.discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public double getExchangeRate() {
            return this.exchangeRate;
        }

        public void setExchangeRate(double exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public String getTimeEnd() {
            return this.timeEnd;
        }

        public void setTimeEnd(String timeEnd) {
            this.timeEnd = timeEnd;
        }

        public String getIpAddress() {
            return this.ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public String getTransactionNum() {
            return this.transactionNum;
        }

        public void setTransactionNum(String transactionNum) {
            this.transactionNum = transactionNum;
        }

        public String getTerminalNum() {
            return this.terminalNum;
        }

        public void setTerminalNum(String terminalNum) {
            this.terminalNum = terminalNum;
        }

        public TransactionUserBean getTransactionUser() {
            return this.transactionUser;
        }

        public void setTransactionUser(TransactionUserBean transactionUser) {
            this.transactionUser = transactionUser;
        }

        public TransactionOrgBean getTransactionOrg() {
            return this.transactionOrg;
        }

        public void setTransactionOrg(TransactionOrgBean transactionOrg) {
            this.transactionOrg = transactionOrg;
        }

        public String getTransactionState() {
            return this.transactionState;
        }

        public void setTransactionState(String transactionState) {
            this.transactionState = transactionState;
        }

        public String getTransactionStateRemark() {
            return this.transactionStateRemark;
        }

        public void setTransactionStateRemark(String transactionStateRemark) {
            this.transactionStateRemark = transactionStateRemark;
        }
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessageCode() {
        return this.messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderInfoBean getOrderInfo() {
        return this.orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }
}
