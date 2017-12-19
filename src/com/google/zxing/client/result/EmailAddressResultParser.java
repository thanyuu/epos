package com.google.zxing.client.result;

import com.google.zxing.Result;
import com.umeng.analytics.C0291a;
import java.util.Map;
import java.util.regex.Pattern;
import p000u.aly.au;

public final class EmailAddressResultParser extends ResultParser {
    private static final Pattern COMMA = Pattern.compile(",");

    public EmailAddressParsedResult parse(Result result) {
        String rawText = ResultParser.getMassagedText(result);
        if (rawText.startsWith("mailto:") || rawText.startsWith("MAILTO:")) {
            String hostEmail = rawText.substring(7);
            int queryStart = hostEmail.indexOf(63);
            if (queryStart >= 0) {
                hostEmail = hostEmail.substring(0, queryStart);
            }
            hostEmail = ResultParser.urlDecode(hostEmail);
            String[] tos = null;
            if (!hostEmail.isEmpty()) {
                tos = COMMA.split(hostEmail);
            }
            Map<String, String> nameValues = ResultParser.parseNameValuePairs(rawText);
            String[] ccs = null;
            String[] bccs = null;
            String subject = null;
            String body = null;
            if (nameValues != null) {
                if (tos == null) {
                    String tosString = (String) nameValues.get("to");
                    if (tosString != null) {
                        tos = COMMA.split(tosString);
                    }
                }
                String ccString = (String) nameValues.get(au.ap);
                if (ccString != null) {
                    ccs = COMMA.split(ccString);
                }
                String bccString = (String) nameValues.get("bcc");
                if (bccString != null) {
                    bccs = COMMA.split(bccString);
                }
                subject = (String) nameValues.get("subject");
                body = (String) nameValues.get(C0291a.f54w);
            }
            return new EmailAddressParsedResult(tos, ccs, bccs, subject, body);
        } else if (EmailDoCoMoResultParser.isBasicallyValidEmailAddress(rawText)) {
            return new EmailAddressParsedResult(rawText);
        } else {
            return null;
        }
    }
}
