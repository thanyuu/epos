package com.cloudpos.apidemo.action;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.cloudpos.apidemo.activity.C0223R;
import com.cloudpos.apidemo.activity.MainActivity;
import com.cloudpos.apidemo.function.ActionCallbackImpl;
import com.cloudpos.apidemo.function.idcard.IDCard;
import com.cloudpos.jniinterface.IDCardInterface;
import com.cloudpos.jniinterface.IDCardProperty;
import com.synjones.bluetooth.DecodeWlt;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IDCardAction extends Activity implements OnClickListener {
    private static String TAG = "IDCardAction";
    private static boolean isOpened;
    private static boolean isRun = false;
    private String bmpPath;
    private Button btnBegin;
    private Button btnEnd;
    private ActionCallbackImpl callback;
    private HandlerImpl handler;
    private Activity host;
    ImageView imgPicture;
    private IDCardProperty property;
    TextView txtAddress;
    TextView txtBorn;
    TextView txtGrantDept;
    TextView txtIDCardNo;
    TextView txtName;
    TextView txtNation;
    TextView txtSex;
    TextView txtValidFromDate;
    TextView txtValidToDate;
    private String wltPath;

    class HandlerImpl extends Handler {
        HandlerImpl() {
        }

        public void handleMessage(Message msg) {
            IDCard card = new IDCard();
            try {
                String result = new String(IDCardAction.this.property.strName, "UTF-16LE").substring(0, IDCardAction.this.property.strName.length / 2).trim();
                card.setName(result);
                IDCardAction.this.txtName.setText(result);
                result = new String(IDCardAction.this.property.strSex, "UTF-16LE").substring(0, IDCardAction.this.property.strSex.length / 2).trim();
                card.setSex(result);
                IDCardAction.this.txtSex.setText(result);
                result = new String(IDCardAction.this.property.strNation, "UTF-16LE").substring(0, IDCardAction.this.property.strNation.length / 2).trim();
                card.setNation(result);
                IDCardAction.this.txtNation.setText(result);
                result = new String(IDCardAction.this.property.strBorn, "UTF-16LE").substring(0, IDCardAction.this.property.strBorn.length / 2).trim();
                card.setBorn(result);
                IDCardAction.this.txtBorn.setText(result);
                result = new String(IDCardAction.this.property.strAddress, "UTF-16LE").substring(0, IDCardAction.this.property.strAddress.length / 2).trim();
                card.setAddress(result);
                IDCardAction.this.txtAddress.setText(result);
                result = new String(IDCardAction.this.property.strGrantDept, "UTF-16LE").substring(0, IDCardAction.this.property.strGrantDept.length / 2).trim();
                card.setGrantDept(result);
                IDCardAction.this.txtGrantDept.setText(result);
                result = new String(IDCardAction.this.property.strIDCardNo, "UTF-16LE").substring(0, IDCardAction.this.property.strIDCardNo.length / 2).trim();
                card.setIDCardNo(result);
                IDCardAction.this.txtIDCardNo.setText(result);
                result = new String(IDCardAction.this.property.strUserLifeBegin, "UTF-16LE").substring(0, IDCardAction.this.property.strUserLifeBegin.length / 2).trim();
                card.setValidFromDate(result);
                IDCardAction.this.txtValidFromDate.setText(result);
                result = new String(IDCardAction.this.property.strUserLifeEnd, "UTF-16LE").substring(0, IDCardAction.this.property.strUserLifeEnd.length / 2).trim();
                card.setValidToDate(result);
                IDCardAction.this.txtValidToDate.setText(result);
                try {
                    FileOutputStream fos = new FileOutputStream(new File(IDCardAction.this.wltPath));
                    fos.write(IDCardAction.this.property.strPicture);
                    fos.close();
                    DecodeWlt dw = new DecodeWlt();
                    Log.e(IDCardAction.TAG, "wltPath = " + IDCardAction.this.wltPath);
                    Log.e(IDCardAction.TAG, "bmpPath = " + IDCardAction.this.bmpPath);
                    Log.e(IDCardAction.TAG, "DecodeWlt.Wlt2Bmp result = " + DecodeWlt.Wlt2Bmp(IDCardAction.this.wltPath, IDCardAction.this.bmpPath));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                Bitmap bitmap = BitmapFactory.decodeFile(IDCardAction.this.bmpPath);
                if (bitmap != null) {
                    IDCardAction.this.imgPicture.setImageBitmap(bitmap);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    class ReadIDCardThread extends Thread {
        ReadIDCardThread() {
        }

        public void run() {
            IDCardAction.isRun = true;
            IDCardAction.this.open();
            if (IDCardAction.isOpened) {
                while (IDCardAction.isRun) {
                    IDCardAction.this.searchTarget();
                    if (IDCardAction.this.getInformation(IDCardAction.this.property) >= 0) {
                        IDCardAction.this.handler.sendMessage(new Message());
                        IDCardAction.isRun = false;
                    }
                }
                IDCardAction.this.close();
            }
        }
    }

    class C04601 implements DataAction {
        C04601() {
        }

        public int getResult() {
            return IDCardInterface.searchTarget();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0223R.layout.activity_idcard);
        initView();
        initParameters();
    }

    private void initParameters() {
        this.callback = (ActionCallbackImpl) MainActivity.callback;
        this.host = this;
        this.handler = new HandlerImpl();
        this.wltPath = getFileStreamPath("photo.wlt").getAbsolutePath();
        this.bmpPath = getFileStreamPath("photo.bmp").getAbsolutePath();
        this.property = new IDCardProperty();
    }

    private void initView() {
        this.txtName = (TextView) findViewById(C0223R.id.txt_name_content);
        this.txtSex = (TextView) findViewById(C0223R.id.txt_sex_content);
        this.txtNation = (TextView) findViewById(C0223R.id.txt_nation_content);
        this.txtBorn = (TextView) findViewById(C0223R.id.txt_born_content);
        this.txtAddress = (TextView) findViewById(C0223R.id.txt_address_content);
        this.txtGrantDept = (TextView) findViewById(C0223R.id.txt_grantdept_content);
        this.txtIDCardNo = (TextView) findViewById(C0223R.id.txt_id_no_content);
        this.txtValidFromDate = (TextView) findViewById(C0223R.id.txt_userlifebegin_content);
        this.txtValidToDate = (TextView) findViewById(C0223R.id.txt_userlifeend_content);
        this.txtName.getPaint().setFlags(8);
        this.txtSex.getPaint().setFlags(8);
        this.txtNation.getPaint().setFlags(8);
        this.txtBorn.getPaint().setFlags(8);
        this.txtAddress.getPaint().setFlags(8);
        this.txtGrantDept.getPaint().setFlags(8);
        this.txtIDCardNo.getPaint().setFlags(8);
        this.txtValidFromDate.getPaint().setFlags(8);
        this.txtValidToDate.getPaint().setFlags(8);
        this.imgPicture = (ImageView) findViewById(C0223R.id.img_picture);
        this.btnBegin = (Button) findViewById(C0223R.id.btn_begin);
        this.btnEnd = (Button) findViewById(C0223R.id.btn_end);
        this.btnBegin.setOnClickListener(this);
        this.btnEnd.setOnClickListener(this);
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == C0223R.id.btn_begin) {
            new ReadIDCardThread().start();
        } else if (i == C0223R.id.btn_end) {
            isRun = false;
            finish();
        }
    }

    private void open() {
        if (isOpened) {
            this.callback.sendFailedMsg(getResources().getString(C0223R.string.device_opened));
            return;
        }
        try {
            int result = IDCardInterface.open();
            if (result < 0) {
                this.callback.sendFailedMsg(getResources().getString(C0223R.string.operation_with_error) + result);
                return;
            }
            isOpened = true;
            this.callback.sendSuccessMsg(getResources().getString(C0223R.string.operation_successful));
        } catch (Throwable th) {
            this.callback.sendFailedMsg(getResources().getString(C0223R.string.operation_failed));
        }
    }

    private void close() {
        if (isOpened) {
            try {
                int result = IDCardInterface.close();
                isOpened = false;
                if (result < 0) {
                    this.callback.sendFailedMsg(getResources().getString(C0223R.string.operation_with_error) + result);
                    return;
                } else {
                    this.callback.sendSuccessMsg(getResources().getString(C0223R.string.operation_successful));
                    return;
                }
            } catch (Throwable th) {
                this.callback.sendFailedMsg(getResources().getString(C0223R.string.operation_failed));
                return;
            }
        }
        this.callback.sendFailedMsg(getResources().getString(C0223R.string.device_not_open));
    }

    private int searchTarget() {
        return checkOpenAndGetData(new C04601());
    }

    private int getInformation(final IDCardProperty property) {
        return checkOpenAndGetData(new DataAction() {
            public int getResult() {
                return IDCardInterface.getInformation(property);
            }
        });
    }

    int checkOpenAndGetData(DataAction action) {
        int result = 0;
        try {
            result = action.getResult();
            if (result < 0) {
                this.callback.sendFailedMsg(this.host.getResources().getString(C0223R.string.operation_with_error) + result);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            this.callback.sendFailedMsg(this.host.getResources().getString(C0223R.string.operation_failed));
        }
        return result;
    }
}
