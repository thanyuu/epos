package com.android.common.service.impl;

import com.android.common.service.FileNameRule;
import com.android.common.utils.FileUtils;
import com.android.common.utils.StringUtils;
import java.util.Calendar;

public class FileNameRuleCurrentTime implements FileNameRule {
    private static /* synthetic */ int[] f569x50f69cbb = null;
    private static final long serialVersionUID = 1;
    private TimeRule timeRule;

    public enum TimeRule {
        YEAR,
        DAY_OF_MONTH,
        MILLISECOND,
        HOUR_OF_DAY_TO_MILLIS,
        HOUR_OF_DAY_TO_SECONDS,
        HOUR_OF_DAY_TO_MINUTES,
        HOUR_TO_MILLIS,
        MINUTE_TO_SECONDS,
        TO_MILLIS,
        TO_SECONDS
    }

    static /* synthetic */ int[] m704x50f69cbb() {
        int[] iArr = f569x50f69cbb;
        if (iArr == null) {
            iArr = new int[TimeRule.values().length];
            try {
                iArr[TimeRule.DAY_OF_MONTH.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TimeRule.HOUR_OF_DAY_TO_MILLIS.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TimeRule.HOUR_OF_DAY_TO_MINUTES.ordinal()] = 6;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[TimeRule.HOUR_OF_DAY_TO_SECONDS.ordinal()] = 5;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[TimeRule.HOUR_TO_MILLIS.ordinal()] = 7;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[TimeRule.MILLISECOND.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[TimeRule.MINUTE_TO_SECONDS.ordinal()] = 8;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[TimeRule.TO_MILLIS.ordinal()] = 9;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[TimeRule.TO_SECONDS.ordinal()] = 10;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[TimeRule.YEAR.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            f569x50f69cbb = iArr;
        }
        return iArr;
    }

    public FileNameRuleCurrentTime(TimeRule timeRule) {
        this.timeRule = timeRule;
    }

    public String getFileName(String imageUrl) {
        long time;
        Calendar now = Calendar.getInstance();
        switch (m704x50f69cbb()[this.timeRule.ordinal()]) {
            case 1:
                time = (long) now.get(1);
                break;
            case 2:
                time = (long) now.get(5);
                break;
            case 3:
                time = (long) now.get(14);
                break;
            case 4:
                time = (long) ((((((now.get(11) * 60) + now.get(12)) * 60) + now.get(13)) * 1000) + now.get(14));
                break;
            case 5:
                time = (long) ((((now.get(11) * 60) + now.get(12)) * 60) + now.get(13));
                break;
            case 6:
                time = (long) ((now.get(11) * 60) + now.get(12));
                break;
            case 7:
                time = (long) ((((((now.get(10) * 60) + now.get(12)) * 60) + now.get(13)) * 1000) + now.get(14));
                break;
            case 8:
                time = (long) ((now.get(12) * 60) + now.get(13));
                break;
            case 9:
                time = now.getTimeInMillis();
                break;
            case 10:
                time = now.getTimeInMillis() / 1000;
                break;
            default:
                time = now.getTimeInMillis();
                break;
        }
        String ext = FileUtils.getFileExtension(imageUrl);
        if (StringUtils.isEmpty(ext)) {
            return Long.toString(time);
        }
        return Long.toString(time) + FileUtils.FILE_EXTENSION_SEPARATOR + ext;
    }
}
