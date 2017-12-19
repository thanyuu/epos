package com.ta.utdid2.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.android.common.utils.FileUtils;
import p000u.aly.bj;

public class NetworkUtils {
    public static final String DEFAULT_WIFI_ADDRESS = "00-00-00-00-00-00";
    public static final String WIFI = "Wi-Fi";

    public static boolean isConnectInternet(Context context) {
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (conManager != null) {
            try {
                NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
                if (networkInfo != null) {
                    return networkInfo.isAvailable();
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static String[] getNetworkState(Context paramContext) {
        String[] arrayOfString = new String[]{"Unknown", "Unknown"};
        try {
            if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0) {
                arrayOfString[0] = "Unknown";
            } else {
                ConnectivityManager localConnectivityManager = (ConnectivityManager) paramContext.getSystemService("connectivity");
                if (localConnectivityManager == null) {
                    arrayOfString[0] = "Unknown";
                } else {
                    NetworkInfo localNetworkInfo1 = localConnectivityManager.getNetworkInfo(1);
                    if (localNetworkInfo1 == null || localNetworkInfo1.getState() != State.CONNECTED) {
                        NetworkInfo localNetworkInfo2 = localConnectivityManager.getNetworkInfo(0);
                        if (localNetworkInfo2 != null && localNetworkInfo2.getState() == State.CONNECTED) {
                            arrayOfString[0] = bj.f349c;
                            arrayOfString[1] = localNetworkInfo2.getSubtypeName();
                        }
                    } else {
                        arrayOfString[0] = "Wi-Fi";
                    }
                }
            }
        } catch (Exception e) {
        }
        return arrayOfString;
    }

    public static String getWifiAddress(Context context) {
        if (context == null) {
            return DEFAULT_WIFI_ADDRESS;
        }
        WifiInfo wifiinfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (wifiinfo == null) {
            return DEFAULT_WIFI_ADDRESS;
        }
        String address = wifiinfo.getMacAddress();
        if (StringUtils.isEmpty(address)) {
            return DEFAULT_WIFI_ADDRESS;
        }
        return address;
    }

    private static String _convertIntToIp(int i) {
        return (i & 255) + FileUtils.FILE_EXTENSION_SEPARATOR + ((i >> 8) & 255) + FileUtils.FILE_EXTENSION_SEPARATOR + ((i >> 16) & 255) + FileUtils.FILE_EXTENSION_SEPARATOR + ((i >> 24) & 255);
    }

    public static String getWifiIpAddress(Context context) {
        String str = null;
        if (context != null) {
            try {
                WifiInfo wifiinfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                if (wifiinfo != null) {
                    str = _convertIntToIp(wifiinfo.getIpAddress());
                }
            } catch (Exception e) {
            }
        }
        return str;
    }

    public static boolean isWifi(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (getNetworkState(context)[0].equals("Wi-Fi")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
