package com.zgf.study.net;

import com.zgf.study.model.HomeModel;

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
}
