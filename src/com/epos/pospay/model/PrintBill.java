package com.epos.pospay.model;

public class PrintBill {
    private String message;
    private String messageCode;
    private boolean success;
    private TotalBean total;

    public static class TotalBean {
        private int refund;
        private float refundAmount;
        private int success;
        private float successAmount;
        private int total;
        private float totalAmount;

        public int getTotal() {
            return this.total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSuccess() {
            return this.success;
        }

        public void setSuccess(int success) {
            this.success = success;
        }

        public int getRefund() {
            return this.refund;
        }

        public void setRefund(int refund) {
            this.refund = refund;
        }

        public float getTotalAmount() {
            return this.totalAmount;
        }

        public void setTotalAmount(float totalAmount) {
            this.totalAmount = totalAmount;
        }

        public float getSuccessAmount() {
            return this.successAmount;
        }

        public void setSuccessAmount(float successAmount) {
            this.successAmount = successAmount;
        }

        public float getRefundAmount() {
            return this.refundAmount;
        }

        public void setRefundAmount(float refundAmount) {
            this.refundAmount = refundAmount;
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

    public TotalBean getTotal() {
        return this.total;
    }

    public void setTotal(TotalBean total) {
        this.total = total;
    }
}
