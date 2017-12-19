package com.cloudpos.scanserver.aidl;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ScanParameter implements Parcelable {
    public static final String BROADCAST_SET_CAMERA = "com.wizarpos.scanner.setcamera";
    public static final String BROADCAST_SET_FLASHLIGHT = "com.wizarpos.scanner.setflashlight";
    public static final String BROADCAST_SET_INDICATOR = "com.wizarpos.scanner.setindicator";
    public static final String BROADCAST_VALUE = "overlay_config";
    public static final Creator<ScanParameter> CREATOR = new C02241();
    public static final String KEY_CAMERA_INDEX = "camera_index";
    public static final String KEY_DECODEFORMAT = "decodeformat";
    public static final String KEY_DECODER_MODE = "decoder_mode";
    public static final String KEY_DISPLAY_SCAN_LINE = "display_scan_line";
    public static final String KEY_ENABLE_FLASH_ICON = "enable_flash_icon";
    public static final String KEY_ENABLE_INDICATOR_LIGHT = "enable_indicator_light";
    public static final String KEY_ENABLE_RETURN_IMAGE = "enable_return_image";
    public static final String KEY_ENABLE_SCAN_SECTION = "enable_scan_section";
    public static final String KEY_ENABLE_SWITCH_ICON = "enable_switch_icon";
    public static final String KEY_FLASH_LIGHT_STATE = "flash_light_state";
    public static final String KEY_INDICATOR_LIGHT_STATE = "indicator_light_state";
    public static final String KEY_SCAN_MODE = "scan_mode";
    public static final String KEY_SCAN_SECTION_BORDER_COLOR = "scan_section_border_color";
    public static final String KEY_SCAN_SECTION_CORNER_COLOR = "scan_section_corner_color";
    public static final String KEY_SCAN_SECTION_HEIGHT = "scan_section_height";
    public static final String KEY_SCAN_SECTION_LINE_COLOR = "scan_section_line_color";
    public static final String KEY_SCAN_SECTION_WIDTH = "scan_section_width";
    public static final String KEY_SCAN_TIME_OUT = "scan_time_out";
    public static final String KEY_SCAN_TIP_TEXT = "scan_tip_text";
    public static final String KEY_SCAN_TIP_TEXTCOLOR = "scan_tip_textColor";
    public static final String KEY_SCAN_TIP_TEXTMARGIN = "scan_tip_textMargin";
    public static final String KEY_SCAN_TIP_TEXTSIZE = "scan_tip_textSize";
    public static final String KEY_SCAN_WITH_EXPOSURE = "scan_with_exposure";
    public static final String KEY_UI_WINDOW_HEIGHT = "window_height";
    public static final String KEY_UI_WINDOW_LEFT = "window_left";
    public static final String KEY_UI_WINDOW_TOP = "window_top";
    public static final String KEY_UI_WINDOW_WIDTH = "window_width";
    static final String TAG = ScanParameter.class.getSimpleName();
    private final Bundle bundle;

    static class C02241 implements Creator<ScanParameter> {
        C02241() {
        }

        public ScanParameter createFromParcel(Parcel source) {
            return new ScanParameter(source);
        }

        public ScanParameter[] newArray(int size) {
            return new ScanParameter[size];
        }
    }

    public ScanParameter() {
        this.bundle = new Bundle();
    }

    private ScanParameter(Parcel source) {
        this.bundle = source.readBundle();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(this.bundle);
    }

    public String toString() {
        return this.bundle.toString();
    }

    public void set(String key, String value) {
        this.bundle.putString(key, value);
    }

    public void set(String key, boolean value) {
        this.bundle.putBoolean(key, value);
    }

    public void set(String key, int value) {
        this.bundle.putInt(key, value);
    }

    public Object get(String key) {
        return this.bundle.get(key);
    }

    public String getString(String key) {
        return this.bundle.getString(key);
    }

    public Bundle getBundle() {
        return this.bundle;
    }
}
