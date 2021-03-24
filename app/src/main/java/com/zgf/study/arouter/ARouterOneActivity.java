package com.zgf.study.arouter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zgf.study.R;

@Route(path = "/test/activity")
public class ARouterOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_router_one);

        ARouter.getInstance().inject(this);

        findViewById(R.id.tv_arouter_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/test/two")
                        .navigation();
            }
        });
    }
}