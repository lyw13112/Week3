package com.bawei.week3.contract;

import java.util.Map;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 8:47
 * @description
 */
public interface IContract {

    interface IModel {
        void modelLogin(Map<String, String> map, ModelCallback callback);
        void modelRegister(Map<String, String> map, ModelCallback callback);
        void modelGetData(Map<String, String> map, ModelCallback callback);
    }

    interface ModelCallback {
        void modelSuccess(Object o);

        void modelError(String error);
    }

    interface IView {
        void viewGetData(Object o);
    }

    interface IPresenter {
        void getData(Map<String, String> map);
        void login(Map<String, String> map);
        void reggister(Map<String, String> map);
    }
}
