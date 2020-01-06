package com.example.week_test2.presenter;

import com.example.week_test2.contract.ClsContract;
import com.example.week_test2.model.ClsModel;

import java.util.HashMap;

public class ClsPresenter implements ClsContract.IPresenter {
    private ClsContract.IView iView;
    private ClsModel clsModel;

    public ClsPresenter(ClsContract.IView iView, ClsModel clsModel) {
        this.iView = iView;
        this.clsModel = clsModel;
    }
    @Override
    public void getLeftData() {
        clsModel.getLeftData(new ClsContract.IModel.ModelCallBack() {
            @Override
            public void success(Object o) {
                iView.success(o);
            }

            @Override
            public void error(Throwable throwable) {
                iView.error(throwable);
            }
        });
    }

    @Override
    public void getRightData(HashMap<String, String> params) {
        clsModel.getLeftData(new ClsContract.IModel.ModelCallBack() {
            @Override
            public void success(Object o) {
                iView.success(o);
            }

            @Override
            public void error(Throwable throwable) {
                iView.error(throwable);
            }
        });
    }
}
