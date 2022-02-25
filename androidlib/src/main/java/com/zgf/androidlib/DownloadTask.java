package com.zgf.androidlib;

import android.os.AsyncTask;

public class DownloadTask extends AsyncTask<String, Integer, Void> {
    @Override
    protected Void doInBackground(String... strings) {
        // 更新进度
        publishProgress();
        return null;
    }
}
