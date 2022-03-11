package com.zgf.study.ui.multitype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.zgf.study.R;
import com.zgf.study.ui.multitype.model.MultiTypeBaseModel;
import com.zgf.study.ui.multitype.model.OneModel;
import com.zgf.study.ui.multitype.model.TwoModel;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeListActivity extends AppCompatActivity {
    private List<MultiTypeBaseModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type_list);

        initData();
        initView();
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i % 2 == 0) {
                list.add(new OneModel(0, "item position is: " + i));
            } else {
                list.add(new TwoModel(1, "title", "item position is: " + i));
            }
        }
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.multi_type_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MultiTypeAdapter adapter = new MultiTypeAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }
}