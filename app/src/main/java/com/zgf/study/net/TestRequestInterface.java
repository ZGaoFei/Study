package com.zgf.study.net;

import com.zgf.study.model.HomeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TestRequestInterface {

    @GET("word/word")
    Call<HomeModel> getNews(@Header("apikey") String apikey, @Query("num") String num, @Query("page") String page);
}
