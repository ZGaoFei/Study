package com.zgf.study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.zgf.study.adapter.HomeAdapter;
import com.zgf.study.model.HomeModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<HomeModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addHomeItems();
        initView();
    }

    private void addHomeItems() {
        list = new ArrayList<>();
        list.add(new HomeModel("自定义View之下载进度条", "zgf://downloadprogress"));
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HomeAdapter adapter = new HomeAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }
}
