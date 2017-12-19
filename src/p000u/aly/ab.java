package p000u.aly;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: UTDIdTracker */
public class ab extends C0379r {
    private static final String f645a = "utdid";
    private static final String f646b = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final Pattern f647c = Pattern.compile("UTDID\">([^<]+)");
    private Context f648d;

    public ab(Context context) {
        super(f645a);
        this.f648d = context;
    }

    public String mo1737f() {
        try {
            return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", new Class[]{Context.class}).invoke(null, new Object[]{this.f648d});
        } catch (Exception e) {
            return m789g();
        }
    }

    private String m789g() {
        File h = m790h();
        if (h == null || !h.exists()) {
            return null;
        }
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(h);
            String b = m788b(bk.m302a(fileInputStream));
            bk.m313c(fileInputStream);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            bk.m313c(fileInputStream);
        }
    }

    private String m788b(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = f647c.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private File m790h() {
        if (!bj.m264a(this.f648d, f646b) || !Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
        } catch (Exception e) {
            return null;
        }
    }
}
