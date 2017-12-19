package com.android.common.service;

import java.io.Serializable;

public interface FileNameRule extends Serializable {
    String getFileName(String str);
}
