package com.epos.pospay.model;

public class LoginBean {
    private String aboutInfo;
    private String authCode;
    private String feeType;
    private MerchantBean merchant;
    private String message;
    private String messageCode;
    private String rate;
    private boolean success;
    private String weposId;

    public static class MerchantBean {
        private String adQrcode;
        private String adRemark;
        private Object agent;
        private String contactPerson;
        private String contactPersonEmail;
        private String contactPersonTel;
        private String director;
        private String loginName;
        private Double maxPayment;
        private int merchantId;
        private String mobile;
        private String nickname;
        private String nzNumber;
        private String orgAddress;
        private String orgDesc;
        private String orgDistrict;
        private String orgName;
        private String orgRemark;
        private String orgShortName;
        private Object parentOrganization;
        private int printCount;
        private String serviceTel;
        private String state;
        private String telephone;
        private String timeZone;
        private String webSite;

        public int getMerchant() {
            return this.merchantId;
        }

        public void setMerchant(int merchant) {
            this.merchantId = merchant;
        }

        public int getPrintCount() {
            return this.printCount;
        }

        public void setPrintCount(int printCount) {
            this.printCount = printCount;
        }

        public String getTimeZone() {
            return this.timeZone;
        }

        public void setTimeZone(String timeZone) {
            this.timeZone = timeZone;
        }

        public String getAdQrcode() {
            return this.adQrcode;
        }

        public void setAdQrcode(String adQrcode) {
            this.adQrcode = adQrcode;
        }

        public String getAdRemark() {
            return this.adRemark;
        }

        public void setAdRemark(String adRemark) {
            this.adRemark = adRemark;
        }

        public Double getMaxPayment() {
            return this.maxPayment;
        }

        public void setMaxPayment(Double maxPayment) {
            this.maxPayment = maxPayment;
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

        public Object getParentOrganization() {
            return this.parentOrganization;
        }

        public void setParentOrganization(Object parentOrganization) {
            this.parentOrganization = parentOrganization;
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

    public String getFeeType() {
        return this.feeType;
    }

    public LoginBean setFeeType(String feeType) {
        this.feeType = feeType;
        return this;
    }

    public String getAboutInfo() {
        return this.aboutInfo;
    }

    public void setAboutInfo(String aboutInfo) {
        this.aboutInfo = aboutInfo;
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

    public String getWeposId() {
        return this.weposId;
    }

    public void setWeposId(String weposId) {
        this.weposId = weposId;
    }

    public String getRate() {
        return this.rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public MerchantBean getMerchant() {
        return this.merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }
}
