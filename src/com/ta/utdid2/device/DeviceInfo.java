package com.ta.utdid2.device;

import android.content.Context;
import com.android.common.utils.ShellUtils;
import com.ta.utdid2.android.utils.PhoneInfoUtils;
import com.ta.utdid2.android.utils.StringUtils;
import java.util.zip.Adler32;

public class DeviceInfo {
    static final Object CREATE_DEVICE_METADATA_LOCK = new Object();
    static String HMAC_KEY = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
    static final byte UTDID_VERSION_CODE = (byte) 1;
    private static Device mDevice = null;

    static long getMetadataCheckSum(Device device) {
        if (device != null) {
            String checkSumContent = String.format("%s%s%s%s%s", new Object[]{device.getUtdid(), device.getDeviceId(), Long.valueOf(device.getCreateTimestamp()), device.getImsi(), device.getImei()});
            if (!StringUtils.isEmpty(checkSumContent)) {
                Adler32 adler32 = new Adler32();
                adler32.reset();
                adler32.update(checkSumContent.getBytes());
                return adler32.getValue();
            }
        }
        return 0;
    }

    private static Device _initDeviceMetadata(Context aContext) {
        Throwable th;
        if (aContext != null) {
            Device device = new Device();
            synchronized (CREATE_DEVICE_METADATA_LOCK) {
                try {
                    String utdid = UTUtdid.instance(aContext).getValue();
                    if (!StringUtils.isEmpty(utdid)) {
                        if (utdid.endsWith(ShellUtils.COMMAND_LINE_END)) {
                            utdid = utdid.substring(0, utdid.length() - 1);
                        }
                        Device device2 = new Device();
                        try {
                            long timestamp = System.currentTimeMillis();
                            String imei = PhoneInfoUtils.getImei(aContext);
                            String imsi = PhoneInfoUtils.getImsi(aContext);
                            device2.setDeviceId(imei);
                            device2.setImei(imei);
                            device2.setCreateTimestamp(timestamp);
                            device2.setImsi(imsi);
                            device2.setUtdid(utdid);
                            device2.setCheckSum(getMetadataCheckSum(device2));
                            return device2;
                        } catch (Throwable th2) {
                            th = th2;
                            device = device2;
                            throw th;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    throw th;
                }
            }
        }
        return null;
    }

    public static synchronized Device getDevice(Context context) {
        Device device;
        synchronized (DeviceInfo.class) {
            if (mDevice != null) {
                device = mDevice;
            } else if (context != null) {
                device = _initDeviceMetadata(context);
                mDevice = device;
            } else {
                device = null;
            }
        }
        return device;
    }
}
