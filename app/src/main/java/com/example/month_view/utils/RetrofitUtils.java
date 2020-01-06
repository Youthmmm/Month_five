package com.example.month_view.utils;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.month_view.api.api;
import com.google.gson.Gson;

import java.net.NetPermission;
import java.net.NetworkInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private Retrofit retrofit;

    public RetrofitUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtils getInstance(){
        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils==null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }
    //动态获取管理类对象
    public <T>T createservice(Class<T> tClass){
        T t = retrofit.create(tClass);
        return t;
    }
    //判断是否有网
    public boolean isNet(){
            return NetworkUtils.isConnected();
    }
}
