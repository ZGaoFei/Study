package com.zgf.study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.IntentService;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.zgf.study.adapter.HomeAdapter;
import com.zgf.study.model.HomeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;


public class MainActivity extends AppCompatActivity {
    private List<HomeModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addHomeItems();
        initView();

        test();
    }

    private void addHomeItems() {
        list = new ArrayList<>();
        list.add(new HomeModel("查看Android-Review文档", "https://github.com/ZGaoFei/Android-Review"));
        list.add(new HomeModel("查看面试宝典内容", "https://github.com/JackChan1999/Android-Interview"));
        list.add(new HomeModel("自定义View之下载进度条", "zgf://downloadprogress"));
        list.add(new HomeModel("监听屏幕高度变化", "zgf://screenchangelistener"));
        list.add(new HomeModel("测试activity的生命周期调用", "zgf://oneactivity"));
        list.add(new HomeModel("测试屏幕旋转", "zgf://screenchange"));
        list.add(new HomeModel("Glide test", "zgf://glidetest"));
        list.add(new HomeModel("ARouter test", "zgf://arouterone"));
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HomeAdapter adapter = new HomeAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    private void test() {
        /**
         * Set
         * HashSet
         * TreeSet
         *
         * List
         * ArrayList
         * LinkedList
         *
         * Vector/Stack
         * Queue
         *
         * HashMap
         * HashTable
         * TreeMap
         * LinkedHashMap
         * SparseArray
         * ArrayMap
         */

        ArrayMap map;
        StringBuffer buffer;
        StringBuilder builder;

        ArrayList arrayList;
        LinkedList linkedList;

        HashSet hashSet;
        TreeSet treeSet;

        HashMap hashMap;
        Hashtable hashtable;
        TreeMap treeMap;

        ArrayMap arrayMap;
        SparseArray sparseArray;

        LinkedHashMap linkedHashMap;
        ConcurrentHashMap concurrentHashMap;

        Activity activity;
        IntentService service;

        LiveData<Integer> liveData;

//        new ViewModelProvider(this, ).get();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
