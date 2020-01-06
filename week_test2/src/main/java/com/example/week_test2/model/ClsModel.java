package com.example.week_test2.model;

import com.example.week_test2.api.ClsApiService;
import com.example.week_test2.contract.ClsContract;
import com.example.week_test2.entity.ClsEntity;
import com.example.week_test2.entity.RightEntity;
import com.example.week_test2.presenter.ClsPresenter;
import com.example.week_test2.utils.RetrofitUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClsModel implements ClsContract.IModel {
    @Override
    public void getLeftData(ModelCallBack modelCallBack) {
        RetrofitUtils.getInstance().createservice(ClsApiService.class)
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
        RetrofitUtils.getInstance().createservice(ClsApiService.class)
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
