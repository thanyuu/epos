package com.epos.pospay.zxing.utils;

import android.content.Context;
import java.util.Calendar;

public class TimeUitl {
    public static String getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTimeInMillis() + "";
    }

    public static String getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 24);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTimeInMillis() + "";
    }

    public static String getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(7, 2);
        return cal.getTimeInMillis() + "";
    }

    public static String getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(7, 2);
        return (cal.getTime().getTime() + 604800000) + "";
    }

    public static String getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(5, cal.getActualMinimum(5));
        return cal.getTimeInMillis() + "";
    }

    public static String getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(5, cal.getActualMaximum(5));
        cal.set(11, 24);
        return cal.getTimeInMillis() + "";
    }

    public static boolean isChinese(Context context) {
        if (context.getResources().getConfiguration().locale.getCountry().equals("CN")) {
            return true;
        }
        return false;
    }
}
