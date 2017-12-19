package com.cloudpos.apidemo.common;

import android.graphics.Bitmap;
import java.io.Serializable;

public class PrintBean implements Serializable {
    private Bitmap bitmap;
    private String content;
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public PrintBean setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        return this;
    }
}
