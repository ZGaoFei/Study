package com.zgf.study.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zgf.androidlib.customerview.DownloadProgressView;
import com.zgf.study.R;

import java.util.Timer;
import java.util.TimerTask;

public class DownloadProgressActivity extends AppCompatActivity {
    private int i = 0;

    private DownloadProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_progress);

        progressView = findViewById(R.id.progress_view);

        final EditText editText = findViewById(R.id.edit_text);

        Button button = findViewById(R.id.bt_set_progress);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = editText.getText().toString().trim();
                progressView.setProgress(Integer.parseInt(trim));
            }
        });

        Button auto = findViewById(R.id.bt_auto_set_progress);
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 0;
                auto();
            }
        });
    }

    private void auto() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                progressView.setProgress(i);
                progressView.setText("已下载" + i + "%");
                i ++;
                if (i > 100) {
                    timer.cancel();
                }
            }
        }, 1, 100);

    }
}