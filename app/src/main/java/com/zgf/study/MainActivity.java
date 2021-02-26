package com.zgf.study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.SparseArray;

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
        list.add(new HomeModel("自定义View之下载进度条", "zgf://downloadprogress"));
        list.add(new HomeModel("监听屏幕高度变化", "zgf://screenchangelistener"));
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
    }
}
