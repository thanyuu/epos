package p000u.aly;

import com.umeng.analytics.C0291a;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* compiled from: UMCCTimeRange */
public class C0378q {
    public static final int f511a = 1;
    private static final int f512b = 1000;
    private static final int f513c = 1001;
    private static final int f514d = 1002;

    public static String m624a(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return String.valueOf(((long) ((((instance.get(12) / 6) + 1) + (instance.get(11) * 10)) - 1)) + (C0378q.m628d(j) * 240));
    }

    private static long m628d(long j) {
        long j2 = 0;
        try {
            long time = new SimpleDateFormat("yyyy").parse("1970").getTime();
            long j3 = (j - time) / C0291a.f41j;
            if ((j - time) % C0291a.f41j > 0) {
                j2 = 1;
            }
            return j2 + j3;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long m626b(long j) {
        return C0378q.m623a(j, (int) f513c);
    }

    public static long m627c(long j) {
        return C0378q.m623a(j, (int) f514d);
    }

    private static long m623a(long j, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i2 = ((instance.get(12) / 6) + 1) + (instance.get(11) * 10);
        int i3 = instance.get(13);
        int i4 = 0;
        if (i == f514d) {
            i4 = 360 - (((instance.get(12) % 6) * 60) + i3);
        } else if (i == f513c) {
            i4 = 60 - (i3 % 60);
            if (i2 % 6 == 0) {
                i4 += 60;
            }
        }
        return (long) (i4 * f512b);
    }

    public static boolean m625a(long j, long j2) {
        if (C0378q.m629e(j) == C0378q.m629e(j2)) {
            return true;
        }
        return false;
    }

    private static int m629e(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(5);
    }
}
