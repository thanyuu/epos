package com.epos.pospay.zxing.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalcRate {
    public static String calcRate(String rate, String money) {
        try {
            float tatal = Float.parseFloat(rate) * Float.parseFloat(money);
            DecimalFormat formater = new DecimalFormat();
            formater.setMaximumFractionDigits(2);
            formater.setGroupingSize(0);
            formater.setRoundingMode(RoundingMode.FLOOR);
            Double incomeRMB = Double.valueOf(Double.parseDouble(formater.format((double) tatal)));
            if (incomeRMB.doubleValue() == 0.0d) {
                return "0";
            }
            return incomeRMB + "";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
}
