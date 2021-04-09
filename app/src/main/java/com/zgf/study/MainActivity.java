package com.zgf.study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.IntentService;
import android.hardware.fingerprint.FingerprintManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.HandlerThread;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

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
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import dalvik.system.BaseDexClassLoader;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;


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

        AsyncTask task;

        HandlerThread h;
        IntentService service1;
    }

    private void testRxjava() {
        Observable.fromCallable(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("Hello world!");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {

            }
        });

        Observer o;

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("hello world!");
                emitter.onComplete();
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Throwable {
                return Integer.parseInt(s);
            }
        });

        BaseDexClassLoader loader;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
