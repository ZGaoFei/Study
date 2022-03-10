package com.zgf.study.net;

import android.util.Log;

import com.zgf.study.model.HomeModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRequest {

    public void requestOne() {
        Retrofit retrofit = new Retrofit.Builder()
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                //设置网络请求的Url地址
                .baseUrl("http://apis.baidu.com/txapi/")
                .build();
        // 创建网络请求接口的实例
        retrofit.create(TestRequestInterface.class)
                .getNews("", "", "")
                .enqueue(new Callback<HomeModel>() {
                    @Override
                    public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {

                    }

                    @Override
                    public void onFailure(Call<HomeModel> call, Throwable t) {

                    }
                });
    }

    public void requestOkHttp() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(null).addNetworkInterceptor(null).build();
        Request build = new Request.Builder().url("").build();
        okhttp3.Call call = client.newCall(build);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

            }
        });

    }

    public void testOkHttp() {
        new OkHttpClient().newBuilder().build();
        OkHttpClient client = new OkHttpClient.Builder().build();

        Request build = new Request.Builder().url("").build();
        client.newCall(build).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                // TODO: 2022/3/8 child thread run, should change to main thread to update ui
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                // TODO: 2022/3/8 child thread run, should change to main thread to update ui
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void test(HomeModel model) {
        Log.e("zgf", "===222===" + model.getTitle());
    }
}
