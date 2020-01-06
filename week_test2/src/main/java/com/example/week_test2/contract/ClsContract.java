package com.example.week_test2.contract;

import android.graphics.ColorSpace;

import java.util.HashMap;

public class ClsContract {
    public interface IModel{
        void getLeftData(ModelCallBack modelCallBack);
        void getRightData(HashMap<String,String> params, ModelCallBack modelCallBack);
        interface ModelCallBack{
            void success(Object o);
            void error(Throwable throwable);
        }
    }
    public interface IView{
        void success(Object o);
        void error(Throwable throwable);
    }
    public interface IPresenter{
        void getLeftData();
        void getRightData(HashMap<String,String> params);
    }
}
