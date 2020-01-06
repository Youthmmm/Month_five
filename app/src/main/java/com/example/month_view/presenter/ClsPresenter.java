package com.example.month_view.presenter;

import com.example.month_view.contract.ClsContract;
import com.example.month_view.model.ClsModel;

import java.util.HashMap;

public class ClsPresenter implements ClsContract.IPresenter {

    private ClsContract.IView iView;
    private ClsModel clsModel;
    public ClsPresenter(ClsContract.IView iView){
        this.iView = iView;
        this.clsModel = new ClsModel();
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
            clsModel.getRightData(params, new ClsContract.IModel.ModelCallBack() {
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
