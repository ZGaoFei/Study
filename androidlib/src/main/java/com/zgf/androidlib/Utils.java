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

    public static int px2dip(Context cc, float pxValue) {
        final float scale = cc.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context cc, float dipValue) {
        final float scale = cc.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
