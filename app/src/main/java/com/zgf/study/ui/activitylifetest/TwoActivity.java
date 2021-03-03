package com.zgf.study.ui.activitylifetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zgf.study.R;

public class TwoActivity extends AppCompatActivity {
    private static final String TAG = "zgf";

    public static void start(Context context) {
        context.startActivity(new Intent(context, TwoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        findViewById(R.id.bt_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        log("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        log("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        log("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        log("onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        log("onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        log("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        log("onDestroy");
    }

    private void log(String method) {
        Log.e(TAG, "=====TwoActivity=====" + method);
    }
}