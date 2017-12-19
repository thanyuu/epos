package com.android.common.service.impl;

import com.android.common.service.FileNameRule;
import com.android.common.utils.FileUtils;
import com.android.common.utils.StringUtils;

public class FileNameRuleImageUrl implements FileNameRule {
    public static final String DEFAULT_FILE_NAME = "ImageSDCardCacheFile.jpg";
    public static final int MAX_FILE_NAME_LENGTH = 127;
    private static final long serialVersionUID = 1;
    private String fileExtension = null;

    public String getFileName(String imageUrl) {
        if (StringUtils.isEmpty(imageUrl)) {
            return DEFAULT_FILE_NAME;
        }
        String ext = this.fileExtension == null ? FileUtils.getFileExtension(imageUrl) : this.fileExtension;
        if (imageUrl.length() > 127) {
            imageUrl = imageUrl.substring(imageUrl.length() - 127, imageUrl.length());
        }
        String fileName = imageUrl.replaceAll("[\\W]", "_");
        if (StringUtils.isEmpty(ext)) {
            return fileName;
        }
        return fileName + FileUtils.FILE_EXTENSION_SEPARATOR + ext.replaceAll("[\\W]", "_");
    }

    public FileNameRuleImageUrl setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
        return this;
    }
}
