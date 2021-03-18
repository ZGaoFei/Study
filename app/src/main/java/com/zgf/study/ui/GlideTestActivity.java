package com.zgf.study.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zgf.study.R;

public class GlideTestActivity extends AppCompatActivity {
    private static final String IMAGE_URL = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1730713693,2130926401&fm=26&gp=0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);

        initView();
    }

    private void initView() {
        ImageView ivOne = findViewById(R.id.iv_one);
        Glide.with(this).load(IMAGE_URL).into(ivOne);
    }
}