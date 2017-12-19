package com.android.common.utils.special;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.util.Log;
import com.android.common.utils.MapUtils;
import java.util.List;

public class WifiController {
    private static final String APP_TAG = "wifiutil";
    private List<WifiConfiguration> mWifiConfiguration;
    private WifiInfo mWifiInfo = this.mWifiManager.getConnectionInfo();
    private List<ScanResult> mWifiList;
    WifiLock mWifiLock;
    private WifiManager mWifiManager;

    public WifiController(Context context) {
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
    }

    public void OpenWifi() {
        if (!this.mWifiManager.isWifiEnabled()) {
            this.mWifiManager.setWifiEnabled(true);
        }
        Log.e(APP_TAG, "mWifiManager.isWifiEnabled() = " + this.mWifiManager.isWifiEnabled());
    }

    public void CloseWifi() {
        Log.e(APP_TAG, "mWifiManager.isWifiEnabled() = " + this.mWifiManager.isWifiEnabled());
        if (this.mWifiManager.isWifiEnabled()) {
            this.mWifiManager.setWifiEnabled(false);
        }
    }

    public void AcquireWifiLock() {
        this.mWifiLock.acquire();
    }

    public void ReleaseWifiLock() {
        if (this.mWifiLock.isHeld()) {
            this.mWifiLock.acquire();
        }
    }

    public void CreatWifiLock() {
        this.mWifiLock = this.mWifiManager.createWifiLock("Test");
    }

    public List<WifiConfiguration> GetConfiguration() {
        return this.mWifiConfiguration;
    }

    public void ConnectConfiguration(int index) {
        if (index <= this.mWifiConfiguration.size()) {
            this.mWifiManager.enableNetwork(((WifiConfiguration) this.mWifiConfiguration.get(index)).networkId, true);
        }
    }

    public void StartScan() {
        this.mWifiManager.startScan();
        this.mWifiList = this.mWifiManager.getScanResults();
        this.mWifiConfiguration = this.mWifiManager.getConfiguredNetworks();
    }

    public List<ScanResult> GetWifiList() {
        return this.mWifiList;
    }

    public StringBuilder LookUpScan() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.mWifiList.size(); i++) {
            stringBuilder.append("Index_" + new Integer(i + 1).toString() + MapUtils.DEFAULT_KEY_AND_VALUE_SEPARATOR);
            stringBuilder.append(((ScanResult) this.mWifiList.get(i)).toString());
            stringBuilder.append("/n");
        }
        return stringBuilder;
    }

    public String GetMacAddress() {
        return this.mWifiInfo == null ? "NULL" : this.mWifiInfo.getMacAddress();
    }

    public String GetBSSID() {
        return this.mWifiInfo == null ? "NULL" : this.mWifiInfo.getBSSID();
    }

    public int GetIPAddress() {
        return this.mWifiInfo == null ? 0 : this.mWifiInfo.getIpAddress();
    }

    public int GetNetworkId() {
        return this.mWifiInfo == null ? 0 : this.mWifiInfo.getNetworkId();
    }

    public String GetWifiInfo() {
        return this.mWifiInfo == null ? "NULL" : this.mWifiInfo.toString();
    }

    public boolean AddNetwork(WifiConfiguration wcg) {
        return this.mWifiManager.enableNetwork(this.mWifiManager.addNetwork(wcg), true);
    }

    public boolean DisconnectWifi(int netId) {
        this.mWifiManager.disableNetwork(netId);
        return this.mWifiManager.disconnect();
    }

    public WifiConfiguration CreateWifiInfo(String SSID, String Password, int Type) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";
        WifiConfiguration tempConfig = isExsits(SSID);
        if (tempConfig != null) {
            this.mWifiManager.removeNetwork(tempConfig.networkId);
        }
        if (Type == 1) {
            config.wepKeys[0] = "";
            config.allowedKeyManagement.set(0);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 2) {
            config.hiddenSSID = true;
            config.wepKeys[0] = "\"" + Password + "\"";
            config.allowedAuthAlgorithms.set(1);
            config.allowedGroupCiphers.set(3);
            config.allowedGroupCiphers.set(2);
            config.allowedGroupCiphers.set(0);
            config.allowedGroupCiphers.set(1);
            config.allowedKeyManagement.set(0);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 3) {
            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms.set(0);
            config.allowedGroupCiphers.set(2);
            config.allowedKeyManagement.set(1);
            config.allowedPairwiseCiphers.set(1);
            config.allowedGroupCiphers.set(3);
            config.allowedPairwiseCiphers.set(2);
            config.status = 2;
        }
        return config;
    }

    private WifiConfiguration isExsits(String SSID) {
        for (WifiConfiguration existingConfig : this.mWifiManager.getConfiguredNetworks()) {
            if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                return existingConfig;
            }
        }
        return null;
    }
}
