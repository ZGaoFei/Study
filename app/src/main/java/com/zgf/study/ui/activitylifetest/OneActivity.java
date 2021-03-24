package com.zgf.study.ui.activitylifetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.zgf.study.R;

public class OneActivity extends AppCompatActivity {
    private static final String TAG = "zgf";

    private Button btPop;

    public static void start(Context context) {
        context.startActivity(new Intent(context, OneActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        findViewById(R.id.bt_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwoActivity.start(OneActivity.this);
//                OneActivity.start(OneActivity.this);
            }
        });

        btPop = findViewById(R.id.bt_pop);
        btPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

        log("onCreate");
    }

    private void pop() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_pop, null);
        PopupWindow window = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        window.showAsDropDown(btPop);
    }

    @Override
    protected void onStart() {
        super.onStart();

        log("onStart");
        ThreadLocal threadLocal;
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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        log("onNewIntent");
    }

    private void log(String method) {
        Log.e(TAG, "===OneActivity===" + method);
    }
}