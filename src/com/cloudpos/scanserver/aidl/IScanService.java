package com.cloudpos.scanserver.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IScanService extends IInterface {

    public static abstract class Stub extends Binder implements IScanService {
        private static final String DESCRIPTOR = "com.cloudpos.scanserver.aidl.IScanService";
        static final int TRANSACTION_scanBarcode = 1;
        static final int TRANSACTION_startScan = 2;
        static final int TRANSACTION_stopScan = 3;

        private static class Proxy implements IScanService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.cloudpos.scanserver.aidl.IScanService";
            }

            public ScanResult scanBarcode(ScanParameter parameter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    ScanResult _result;
                    _data.writeInterfaceToken("com.cloudpos.scanserver.aidl.IScanService");
                    if (parameter != null) {
                        _data.writeInt(1);
                        parameter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (ScanResult) ScanResult.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void startScan(ScanParameter parameter, IScanCallBack callBack) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.cloudpos.scanserver.aidl.IScanService");
                    if (parameter != null) {
                        _data.writeInt(1);
                        parameter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callBack != null ? callBack.asBinder() : null);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean stopScan() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.cloudpos.scanserver.aidl.IScanService");
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.cloudpos.scanserver.aidl.IScanService");
        }

        public static IScanService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.cloudpos.scanserver.aidl.IScanService");
            if (iin == null || !(iin instanceof IScanService)) {
                return new Proxy(obj);
            }
            return (IScanService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            ScanParameter _arg0;
            switch (code) {
                case 1:
                    data.enforceInterface("com.cloudpos.scanserver.aidl.IScanService");
                    if (data.readInt() != 0) {
                        _arg0 = (ScanParameter) ScanParameter.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    ScanResult _result = scanBarcode(_arg0);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 2:
                    data.enforceInterface("com.cloudpos.scanserver.aidl.IScanService");
                    if (data.readInt() != 0) {
                        _arg0 = (ScanParameter) ScanParameter.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    startScan(_arg0, com.cloudpos.scanserver.aidl.IScanCallBack.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("com.cloudpos.scanserver.aidl.IScanService");
                    boolean _result2 = stopScan();
                    reply.writeNoException();
                    if (_result2) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 1598968902:
                    reply.writeString("com.cloudpos.scanserver.aidl.IScanService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    ScanResult scanBarcode(ScanParameter scanParameter) throws RemoteException;

    void startScan(ScanParameter scanParameter, IScanCallBack iScanCallBack) throws RemoteException;

    boolean stopScan() throws RemoteException;
}
