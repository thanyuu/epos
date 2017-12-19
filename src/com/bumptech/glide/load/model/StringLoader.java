package com.bumptech.glide.load.model;

import android.net.Uri;
import android.text.TextUtils;
import com.android.common.utils.HttpUtils;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.File;

public class StringLoader<T> implements ModelLoader<String, T> {
    private final ModelLoader<Uri, T> uriLoader;

    public StringLoader(ModelLoader<Uri, T> uriLoader) {
        this.uriLoader = uriLoader;
    }

    public DataFetcher<T> getResourceFetcher(String model, int width, int height) {
        if (TextUtils.isEmpty(model)) {
            return null;
        }
        Uri uri;
        if (model.startsWith(HttpUtils.PATHS_SEPARATOR)) {
            uri = toFileUri(model);
        } else {
            uri = Uri.parse(model);
            if (uri.getScheme() == null) {
                uri = toFileUri(model);
            }
        }
        return this.uriLoader.getResourceFetcher(uri, width, height);
    }

    private static Uri toFileUri(String path) {
        return Uri.fromFile(new File(path));
    }
}
