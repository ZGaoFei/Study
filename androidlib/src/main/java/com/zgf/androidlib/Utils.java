package com.zgf.androidlib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

public class Utils {

    private Utils() {
    }

    public static void skipPageWithScheme(Context context, String scheme) {
        if (TextUtils.isEmpty(scheme)) {
            return;
        }
        if (context == null) {
            return;
        }
        Uri uri = Uri.parse(scheme);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

}
