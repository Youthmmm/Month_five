package com.example.month_view.model;

import com.example.month_view.api.ClsApiservice;
import com.example.month_view.contract.ClsContract;
import com.example.month_view.entity.ClsEntity;
import com.example.month_view.entity.RightEntity;
import com.example.month_view.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClsModel implements ClsContract.IModel {
    @Override
    public void getLeftData(ModelCallBack modelCallBack) {
        RetrofitUtils.getInstance().createservice(ClsApiservice.class)
                .getCls()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClsEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClsEntity clsEntity) {
                        modelCallBack.success(clsEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        modelCallBack.error(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getRightData(HashMap<String, String> params, ModelCallBack modelCallBack) {
        RetrofitUtils.getInstance().createservice(ClsApiservice.class)
                .getRight(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RightEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RightEntity rightEntity) {
                        modelCallBack.success(rightEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        modelCallBack.error(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
