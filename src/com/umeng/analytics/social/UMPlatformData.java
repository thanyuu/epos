package com.umeng.analytics.social;

import android.text.TextUtils;
import com.umeng.analytics.C0291a;
import java.util.Locale;

public class UMPlatformData {
    private UMedia f102a;
    private String f103b = "";
    private String f104c = "";
    private String f105d;
    private GENDER f106e;

    public enum GENDER {
        MALE(0) {
            public String toString() {
                return String.format(Locale.US, "Male:%d", new Object[]{Integer.valueOf(this.value)});
            }
        },
        FEMALE(1) {
            public String toString() {
                return String.format(Locale.US, "Female:%d", new Object[]{Integer.valueOf(this.value)});
            }
        };
        
        public int value;

        private GENDER(int i) {
            this.value = i;
        }
    }

    public enum UMedia {
        SINA_WEIBO {
            public String toString() {
                return "sina";
            }
        },
        TENCENT_WEIBO {
            public String toString() {
                return "tencent";
            }
        },
        TENCENT_QZONE {
            public String toString() {
                return "qzone";
            }
        },
        TENCENT_QQ {
            public String toString() {
                return "qq";
            }
        },
        WEIXIN_FRIENDS {
            public String toString() {
                return "wxsesion";
            }
        },
        WEIXIN_CIRCLE {
            public String toString() {
                return "wxtimeline";
            }
        },
        RENREN {
            public String toString() {
                return "renren";
            }
        },
        DOUBAN {
            public String toString() {
                return "douban";
            }
        }
    }

    public UMPlatformData(UMedia uMedia, String str) {
        if (uMedia == null || TextUtils.isEmpty(str)) {
            C0311b.m110b(C0291a.f35d, "parameter is not valid");
            return;
        }
        this.f102a = uMedia;
        this.f103b = str;
    }

    public String getWeiboId() {
        return this.f104c;
    }

    public void setWeiboId(String str) {
        this.f104c = str;
    }

    public UMedia getMeida() {
        return this.f102a;
    }

    public String getUsid() {
        return this.f103b;
    }

    public String getName() {
        return this.f105d;
    }

    public void setName(String str) {
        this.f105d = str;
    }

    public GENDER getGender() {
        return this.f106e;
    }

    public void setGender(GENDER gender) {
        this.f106e = gender;
    }

    public boolean isValid() {
        if (this.f102a == null || TextUtils.isEmpty(this.f103b)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "UMPlatformData [meida=" + this.f102a + ", usid=" + this.f103b + ", weiboId=" + this.f104c + ", name=" + this.f105d + ", gender=" + this.f106e + "]";
    }
}
