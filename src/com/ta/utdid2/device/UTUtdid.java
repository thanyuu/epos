package com.ta.utdid2.device;

import android.content.Context;
import android.provider.Settings.System;
import com.android.common.utils.ShellUtils;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.android.utils.IntUtils;
import com.ta.utdid2.android.utils.PhoneInfoUtils;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.core.persistent.PersistentConfiguration;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.zip.Adler32;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class UTUtdid {
    private static final Object CREATE_LOCK = new Object();
    private static final String HMAC_KEY = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
    private static final String S_GLOBAL_PERSISTENT_CONFIG_DIR = (".UTSystemConfig" + File.separator + "Global");
    private static final String S_GLOBAL_PERSISTENT_CONFIG_KEY = "Alvin2";
    private static final String S_LOCAL_STORAGE_KEY = "ContextData";
    private static final String S_LOCAL_STORAGE_NAME = ".DataStorage";
    static final String UM_SETTINGS_STORAGE = "dxCRMxhQkdGePGnp";
    static final String UM_SETTINGS_STORAGE_NEW = "mqBRboGZkQPcAkyk";
    private static UTUtdid s_umutdid = null;
    private String mCBDomain = "xx_utdid_domain";
    private String mCBKey = "xx_utdid_key";
    private Context mContext = null;
    private PersistentConfiguration mPC = null;
    private Pattern mPattern = Pattern.compile("[^0-9a-zA-Z=/+]+");
    private PersistentConfiguration mTaoPC = null;
    private String mUtdid = null;
    private UTUtdidHelper mUtdidHelper = null;

    public UTUtdid(Context context) {
        this.mContext = context;
        this.mTaoPC = new PersistentConfiguration(context, S_GLOBAL_PERSISTENT_CONFIG_DIR, S_GLOBAL_PERSISTENT_CONFIG_KEY, false, true);
        this.mPC = new PersistentConfiguration(context, S_LOCAL_STORAGE_NAME, S_LOCAL_STORAGE_KEY, false, true);
        this.mUtdidHelper = new UTUtdidHelper();
        this.mCBKey = String.format("K_%d", new Object[]{Integer.valueOf(StringUtils.hashCode(this.mCBKey))});
        this.mCBDomain = String.format("D_%d", new Object[]{Integer.valueOf(StringUtils.hashCode(this.mCBDomain))});
    }

    public static UTUtdid instance(Context context) {
        if (context != null && s_umutdid == null) {
            synchronized (CREATE_LOCK) {
                if (s_umutdid == null) {
                    s_umutdid = new UTUtdid(context);
                }
            }
        }
        return s_umutdid;
    }

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

    private void saveUtdidToTaoPPC(String pUtdid) {
        if (isValidUTDID(pUtdid)) {
            if (pUtdid.endsWith(ShellUtils.COMMAND_LINE_END)) {
                pUtdid = pUtdid.substring(0, pUtdid.length() - 1);
            }
            if (pUtdid.length() == 24 && this.mTaoPC != null) {
                String lUTDID = this.mTaoPC.getString("UTDID");
                String lIMEI = this.mTaoPC.getString("EI");
                if (StringUtils.isEmpty(lIMEI)) {
                    lIMEI = PhoneInfoUtils.getImei(this.mContext);
                }
                String lIMSI = this.mTaoPC.getString("SI");
                if (StringUtils.isEmpty(lIMSI)) {
                    lIMSI = PhoneInfoUtils.getImsi(this.mContext);
                }
                String lDID = this.mTaoPC.getString("DID");
                if (StringUtils.isEmpty(lDID)) {
                    lDID = lIMEI;
                }
                if (lUTDID == null || !lUTDID.equals(pUtdid)) {
                    Device lD = new Device();
                    lD.setImei(lIMEI);
                    lD.setImsi(lIMSI);
                    lD.setUtdid(pUtdid);
                    lD.setDeviceId(lDID);
                    lD.setCreateTimestamp(System.currentTimeMillis());
                    this.mTaoPC.putString("UTDID", pUtdid);
                    this.mTaoPC.putString("EI", lD.getImei());
                    this.mTaoPC.putString("SI", lD.getImsi());
                    this.mTaoPC.putString("DID", lD.getDeviceId());
                    this.mTaoPC.putLong("timestamp", lD.getCreateTimestamp());
                    this.mTaoPC.putLong("S", getMetadataCheckSum(lD));
                    this.mTaoPC.commit();
                }
            }
        }
    }

    private void saveUtdidToLocalStorage(String pPackedUtdid) {
        if (pPackedUtdid != null && this.mPC != null && !pPackedUtdid.equals(this.mPC.getString(this.mCBKey))) {
            this.mPC.putString(this.mCBKey, pPackedUtdid);
            this.mPC.commit();
        }
    }

    private void saveUtdidToNewSettings(String lUtdid) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0 && isValidUTDID(lUtdid)) {
            if (lUtdid.endsWith(ShellUtils.COMMAND_LINE_END)) {
                lUtdid = lUtdid.substring(0, lUtdid.length() - 1);
            }
            if (24 == lUtdid.length() && !isValidUTDID(System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW))) {
                System.putString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW, lUtdid);
            }
        }
    }

    private void syncUTDIDToSettings(String pPackedUtdid) {
        if (!pPackedUtdid.equals(System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE))) {
            System.putString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE, pPackedUtdid);
        }
    }

    private void saveUtdidToSettings(String lPackedUtdid) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0 && lPackedUtdid != null) {
            syncUTDIDToSettings(lPackedUtdid);
        }
    }

    private String getUtdidFromTaoPPC() {
        if (this.mTaoPC != null) {
            String lUTDID = this.mTaoPC.getString("UTDID");
            if (!(StringUtils.isEmpty(lUTDID) || this.mUtdidHelper.packUtdidStr(lUTDID) == null)) {
                return lUTDID;
            }
        }
        return null;
    }

    private boolean isValidUTDID(String pUTDID) {
        if (pUTDID == null) {
            return false;
        }
        if (pUTDID.endsWith(ShellUtils.COMMAND_LINE_END)) {
            pUTDID = pUTDID.substring(0, pUTDID.length() - 1);
        }
        if (24 != pUTDID.length() || this.mPattern.matcher(pUTDID).find()) {
            return false;
        }
        return true;
    }

    public synchronized String getValue() {
        String str;
        if (this.mUtdid != null) {
            str = this.mUtdid;
        } else {
            str = System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW);
            if (!isValidUTDID(str)) {
                UTUtdidHelper2 lHelper2 = new UTUtdidHelper2();
                boolean lNeedUpdateSettings = false;
                String data = System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE);
                if (StringUtils.isEmpty(data)) {
                    lNeedUpdateSettings = true;
                } else {
                    String lTmpUtdidBase64 = lHelper2.dePackWithBase64(data);
                    if (isValidUTDID(lTmpUtdidBase64)) {
                        saveUtdidToNewSettings(lTmpUtdidBase64);
                        str = lTmpUtdidBase64;
                    } else {
                        String lTmpUtdid = lHelper2.dePack(data);
                        if (isValidUTDID(lTmpUtdid)) {
                            String lPTmpUtdid = this.mUtdidHelper.packUtdidStr(lTmpUtdid);
                            if (!StringUtils.isEmpty(lPTmpUtdid)) {
                                saveUtdidToSettings(lPTmpUtdid);
                                data = System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE);
                            }
                        }
                        String lDePackedUtdid = this.mUtdidHelper.dePack(data);
                        if (isValidUTDID(lDePackedUtdid)) {
                            this.mUtdid = lDePackedUtdid;
                            saveUtdidToTaoPPC(lDePackedUtdid);
                            saveUtdidToLocalStorage(data);
                            saveUtdidToNewSettings(this.mUtdid);
                            str = this.mUtdid;
                        }
                    }
                }
                String lSUtdid = getUtdidFromTaoPPC();
                String lPackedUtdid;
                if (isValidUTDID(lSUtdid)) {
                    lPackedUtdid = this.mUtdidHelper.packUtdidStr(lSUtdid);
                    if (lNeedUpdateSettings) {
                        saveUtdidToSettings(lPackedUtdid);
                    }
                    saveUtdidToNewSettings(lSUtdid);
                    saveUtdidToLocalStorage(lPackedUtdid);
                    this.mUtdid = lSUtdid;
                    str = lSUtdid;
                } else {
                    String lContent = this.mPC.getString(this.mCBKey);
                    if (!StringUtils.isEmpty(lContent)) {
                        String lUtdid = lHelper2.dePack(lContent);
                        if (!isValidUTDID(lUtdid)) {
                            lUtdid = this.mUtdidHelper.dePack(lContent);
                        }
                        if (isValidUTDID(lUtdid)) {
                            String lBUtdid = this.mUtdidHelper.packUtdidStr(lUtdid);
                            if (!StringUtils.isEmpty(lUtdid)) {
                                this.mUtdid = lUtdid;
                                if (lNeedUpdateSettings) {
                                    saveUtdidToSettings(lBUtdid);
                                }
                                saveUtdidToTaoPPC(this.mUtdid);
                                str = this.mUtdid;
                            }
                        }
                    }
                    try {
                        byte[] lUtdid2 = _generateUtdid();
                        if (lUtdid2 != null) {
                            this.mUtdid = Base64.encodeToString(lUtdid2, 2);
                            saveUtdidToTaoPPC(this.mUtdid);
                            lPackedUtdid = this.mUtdidHelper.pack(lUtdid2);
                            if (lPackedUtdid != null) {
                                if (lNeedUpdateSettings) {
                                    saveUtdidToSettings(lPackedUtdid);
                                }
                                saveUtdidToLocalStorage(lPackedUtdid);
                            }
                            str = this.mUtdid;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    str = null;
                }
            }
        }
        return str;
    }

    private final byte[] _generateUtdid() throws Exception {
        String imei;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        int uniqueID = new Random().nextInt();
        byte[] bTimestamp = IntUtils.getBytes(timestamp);
        byte[] bUniqueID = IntUtils.getBytes(uniqueID);
        baos.write(bTimestamp, 0, 4);
        baos.write(bUniqueID, 0, 4);
        baos.write((byte) 3);
        baos.write((byte) 0);
        try {
            imei = PhoneInfoUtils.getImei(this.mContext);
        } catch (Exception e) {
            imei = "" + new Random().nextInt();
        }
        baos.write(IntUtils.getBytes(StringUtils.hashCode(imei)), 0, 4);
        String hmac = "";
        baos.write(IntUtils.getBytes(StringUtils.hashCode(_calcHmac(baos.toByteArray()))));
        return baos.toByteArray();
    }

    private static String _calcHmac(byte[] src) throws Exception {
        String key = HMAC_KEY;
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(key.getBytes(), mac.getAlgorithm()));
        return Base64.encodeToString(mac.doFinal(src), 2);
    }
}
