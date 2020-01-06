package com.example.week_test2.api;

import com.example.week_test2.entity.ClsEntity;
import com.example.week_test2.entity.RightEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ClsApiService {
    @GET("http://blog.zhaoliang5156.cn/baweiapi/category")
    Observable<ClsEntity> getCls();

    @GET("http://blog.zhaoliang5156.cn/baweiapi/shopByCategory?category=生活")
    Observable<RightEntity> getRight(@QueryMap HashMap<String,String> params);
}
