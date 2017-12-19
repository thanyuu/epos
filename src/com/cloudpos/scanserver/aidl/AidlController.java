package com.cloudpos.scanserver.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.cloudpos.scanserver.aidl.IScanService.Stub;

public class AidlController {
    public static final String DESC_SCAN_SERVICE = "com.cloudpos.scanserver.aidl.IScanService";
    public static final String TAG = "AidlController";
    private static AidlController instance;
    private IAIDLListener aidlListener;
    private ServiceConnection connection;

    protected class ServiceConnectionImpl implements ServiceConnection {
        protected ServiceConnectionImpl() {
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                String temp = service.getInterfaceDescriptor();
                Log.d(AidlController.TAG, "onServiceConnected : " + temp);
                Object objService = null;
                if (temp.equals(AidlController.DESC_SCAN_SERVICE)) {
                    objService = Stub.asInterface(service);
                } else {
                    Log.e(AidlController.TAG, "can't identify AIDL");
                }
                if (objService != null) {
                    AidlController.this.aidlListener.serviceConnected(objService, AidlController.this.connection);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            AidlController.this.connection = null;
        }
    }

    private AidlController() {
    }

    public static AidlController getInstance() {
        if (instance == null) {
            instance = new AidlController();
        }
        return instance;
    }

    protected void setListener(IAIDLListener aidlListener) {
        this.aidlListener = aidlListener;
    }

    protected boolean startConnectService(Context host, String packageName, String className, IAIDLListener aidlListener) {
        return startConnectService(host, new ComponentName(packageName, className), aidlListener);
    }

    protected boolean startConnectService(Context host, ComponentName comp, IAIDLListener aidlListener) {
        setListener(aidlListener);
        Intent intent = new Intent();
        intent.setComponent(comp);
        this.connection = new ServiceConnectionImpl();
        boolean isSuccess = host.bindService(intent, this.connection, 1);
        host.startService(intent);
        return isSuccess;
    }

    public boolean startScanService(Context host, IAIDLListener aidlListener) {
        return startConnectService(host, new ComponentName("com.cloudpos.scanserver", "com.cloudpos.scanserver.service.ScannerService"), aidlListener);
    }
}
