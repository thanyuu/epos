package com.epos.pospay.common;

public class PosConst {
    public static final String[] Transaction_State = new String[]{"NOTPAY", "SUCCESS", "REFUND", "CLOSED", "REVOKED", "USERPAYING", "PAYERROR", "REFUNDING", "Settled"};
    public static final String[] Transaction_Type = new String[]{"cashPay", HttpRequestManager.PAY_CHANNEL, "alipay", "baiduPay", "bankCardPay"};
}
