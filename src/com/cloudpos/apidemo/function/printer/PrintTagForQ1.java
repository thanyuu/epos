package com.cloudpos.apidemo.function.printer;

import android.graphics.Bitmap;

public class PrintTagForQ1 {

    public static final class PurchaseBillTag {
        public static final String AMOUT_TAG = "金额（AMOUT）：";
        public static final String CARD_HOLDER_SIGNATURE_TAG = "持卡人签名(CARD HOLDER SIGNATURE)";
        public static final String CARD_NO_TAG = "卡号（CARD NO）";
        public static final String C_AGREE_TRADE_TAG = "本人确认以上交易，同意将其记入本卡账户";
        public static final String DATE_TIME_EXP_DATE_TAG = "日期/时间（DATE/TIME）    有效期（EXP DATE）";
        public static final String E_AGREE_TRADE_TAG = "I ACKNOWLEDGE SATISFACTORY RECEIPT OF RELATIVE GOODS/SERVICE";
        public static final String ISSUER_ACQUIRER_TAG = "发卡机构（ISSUER）    收单机构（ACQUIRER）";
        public static final String MERCHANT_COPY_TAG = "商户存根        MERCHANT COPY";
        public static final String MERCHANT_NAME_TAG = "商户名称（MERCHANT NAME）";
        public static final String MERCHANT_NO_TAG = "商户编号（MERCHANT NO）";
        public static final Bitmap PURCHASE_BILL_TITLE = null;
        public static final String REFERENCE_TAG = "备注/REFERENCE";
        public static final String REF_NO_BATCH_NO_TAG = "交易参考号（REF NO）    批次号（BATCH NO）";
        public static final String SEPARATE = "-------------------------------";
        public static final String TERMINAL_NO_AND_OPERATOR_TAG = "终端编号（TERMINAL NO）  操作员号（OPERATOR）";
        public static final String TRANS_TYPE_TAG = "交易类型（TRANS TYPE）";
        public static final String VOUCHER_NO_AUTH_NO_TAG = "凭证号（VOUCHER）    授权码（AUTH NO）";
    }
}
