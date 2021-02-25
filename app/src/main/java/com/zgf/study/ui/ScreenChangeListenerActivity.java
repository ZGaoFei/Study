package com.zgf.study.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zgf.study.R;

/**
 * 适合场景
 * 在页面中隐藏和显示软键盘
 * 或者屏幕变化导致屏幕的高度变化
 * 如果屏幕显示区域的高度变化了，需要重新获取高度
 */
public class ScreenChangeListenerActivity extends AppCompatActivity {
    private FrameLayout flRoot;
    private TextView textView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ScreenChangeListenerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_change_listener);

        flRoot = findViewById(R.id.fl_root);
        textView = findViewById(R.id.tv_screen_height);

        setListener();
        setListener2();
    }

    private void setListener() {
        // 可行，但是有可能回调多次
        flRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.e("zgf", "========onGlobalLayout====1111=====");
                int height = flRoot.getHeight();
                textView.setText("屏幕显示的高度为：" + height);
            }
        });
    }

    private void setListener2() {
        // 可行，只回调一次
        flRoot.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Log.e("zgf", "========onLayoutChange====2222=====" + flRoot.getHeight());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        setListener3();
    }

    private void setListener3() {
        // 也可以，但是需要配合onResume使用，如果页面内变化就没法获取到
        flRoot.post(new Runnable() {
            @Override
            public void run() {
                Log.e("zgf", "========onLayoutChange====333=====" + flRoot.getHeight());
            }
        });
    }

    // 只有屏幕旋转的时候有变化
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("zgf", "==========onConfigurationChanged===========");
    }

}
