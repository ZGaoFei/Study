package com.zgf.study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.IntentService;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.fingerprint.FingerprintManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.Toast;

import com.zgf.study.adapter.HomeAdapter;
import com.zgf.study.model.HomeModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import dalvik.system.BaseDexClassLoader;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeEmitter;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeToObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private List<HomeModel> list;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        presenter = new MainPresenter();

        addHomeItems();
        initView();

        test();

        Log.e("zgf", "====onCreate=======");

        testFile();
    }

    private void testFile() {
        String path = "/sdcard/xllive/xllive_plugin_app_project-release.apk";
        File file = new File(path);
        Log.e("zgf", "=====file====" + file.exists());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("zgf", "=====onStart======");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("zgf", "=====onResume======");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("zgf", "=====onPause======");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("zgf", "=====onRestart======");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("zgf", "=====onStop======");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.e("zgf", "====onDestroy=======");
    }

    private void addHomeItems() {
        list = new ArrayList<>();
        list.add(new HomeModel("查看Android-Review文档", "https://github.com/ZGaoFei/Android-Review"));
        list.add(new HomeModel("YCBlogs", "https://github.com/yangchong211/YCBlogs"));
        list.add(new HomeModel("查看面试宝典内容", "https://github.com/JackChan1999/Android-Interview"));
        list.add(new HomeModel("自定义View之下载进度条", "zgf://downloadprogress"));
        list.add(new HomeModel("监听屏幕高度变化", "zgf://screenchangelistener"));
        list.add(new HomeModel("测试activity的生命周期调用", "zgf://oneactivity"));
        list.add(new HomeModel("测试屏幕旋转", "zgf://screenchange"));
        list.add(new HomeModel("Glide test", "zgf://glidetest"));
        list.add(new HomeModel("ARouter test", "zgf://arouterone"));
        list.add(new HomeModel("EventBus test", "test/eventbus"));
        list.add(new HomeModel("RecyclerView click test", "zgf://recyclerviewtest"));
        list.add(new HomeModel("Multi type RecyclerView test", "zgf://multitypelisttest"));
        list.add(new HomeModel("ListView test", "zgf://listviewtest"));
        list.add(new HomeModel("LiveData ViewModel test", "zgf://livedatatest"));
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HomeAdapter adapter = new HomeAdapter(this, list);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new HomeAdapter.ClickListener() {
            @Override
            public void onItemClick(View view, String flag) {
                if (flag.equals("test/eventbus")) {
                    HomeModel homeModel = new HomeModel();
                    homeModel.setScheme("https");
                    homeModel.setTitle("hello world!");
                    EventBus.getDefault().post(homeModel);
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void test(HomeModel model) {
        Log.e("zgf", "===111===" + model.getTitle());
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

        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ConcurrentHashMap concurrentHashMap;
        Collections.synchronizedMap(linkedHashMap);

        Activity activity;
        IntentService service;

        LiveData<Integer> liveData;

//        new ViewModelProvider(this, ).get();

        AsyncTask task;

        HandlerThread h;
        IntentService service1;
        LinearLayout linearLayout;

        RecyclerView recyclerView;

        ViewPager viewPager;

        Scroller scroller;
        View view;

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        ContextWrapper contextWrapper;

        SharedPreferences sharedPreferences = getSharedPreferences("", MODE_PRIVATE);

        ThreadLocal threadLocal;
        Thread thread;

        LocalBroadcastManager manager;

        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@androidx.annotation.NonNull Message msg) {

                return false;
            }
        });

        getLifecycle().addObserver(presenter);

        Choreographer.getInstance();

        LifecycleService lifecycleService;

        ProcessLifecycleOwner owner;

        Bitmap bitmap;

        Instrumentation instrumentation;

        BlockingQueue blockingQueue;

        ValueAnimator.ofInt(10).start();
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

        /**
         * 基本流程
         * 1、创建被观察者Observable
         * 2、产生事件
         * 3、创建观察者Observer
         * 4、绑定被观察者和观察者subscribe
         * 5、处理事件
         */
        Observable.create(new ObservableOnSubscribe<String>() { // 1
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("hello world!"); // 2
                emitter.onComplete();
            }
        }).subscribe(new Observer<String>() { // 3/4
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                d.dispose();
            }

            @Override
            public void onNext(@NonNull String s) {
                // 5
                Log.e("zgf", "=======onNext=========" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {

            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Throwable {
                return null;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {

            }
        }).flatMap(new Function<String, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(String s) throws Throwable {
                return null;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {

                    }
                }).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<String> emitter) throws Throwable {

            }
        }).subscribe(new MaybeObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void testHandlerThread() {
        // 主线程的handler
        final Handler uiHandler = new Handler(getMainLooper());

        HandlerThread handlerThread = new HandlerThread("handler_thread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(@androidx.annotation.NonNull Message msg) {
                super.handleMessage(msg);
                // TODO: 2022/5/12 处理异步任务

                // 通知UI线程
                uiHandler.sendEmptyMessage(0);
            }
        };

        Message message = Message.obtain();
        message.what = 0;
        handler.sendMessage(message);

        // handlerThread.quit();
    }

    private void testFragment() {
        getFragmentManager()
                .beginTransaction()
                .add(0, null)
                .commitAllowingStateLoss();
    }
}
