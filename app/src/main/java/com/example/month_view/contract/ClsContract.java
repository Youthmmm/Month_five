package com.example.month_view.contract;

import android.view.View;

import java.util.HashMap;

public interface ClsContract {
    interface IModel{
        void getLeftData(ModelCallBack modelCallBack);
        void getRightData(HashMap<String,String> params,ModelCallBack modelCallBack);
        interface ModelCallBack{
            void success(Object o);
            void error(Throwable throwable);
        }
    }
    interface IView{
        void success(Object o);
        void error(Throwable throwable);
    }
    interface IPresenter{
        void getLeftData();
        //二级分类查询商品
        void getRightData(HashMap<String,String> params);
    }
}
