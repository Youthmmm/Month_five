package com.example.month_view.api;

import com.example.month_view.entity.ClsEntity;
import com.example.month_view.entity.RightEntity;

import java.util.HashMap;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ClsApiservice {
    @GET("commodity/v1/findCategory")
    Observable<ClsEntity> getCls();

    @GET("commodity/v1/findCommodityByCategory")
    Observable<RightEntity> getRight(@QueryMap HashMap<String,String> params);
}
