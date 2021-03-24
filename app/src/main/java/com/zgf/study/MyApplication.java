package com.zgf.study;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (true) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
