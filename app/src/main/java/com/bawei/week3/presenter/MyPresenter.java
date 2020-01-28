package com.bawei.week3.presenter;

import com.bawei.week3.base.BasePresenter;
import com.bawei.week3.contract.IContract;
import com.bawei.week3.model.MyModel;

import java.util.Map;

/**
 * @author 刘云蔚
 * @createTime 2020/1/28 17:22
 * @description P层
 */
public class MyPresenter extends BasePresenter {

    @Override
    public void getData(Map<String, String> map) {
        new MyModel().modelGetData(map, new IContract.ModelCallback() {
            @Override
            public void modelSuccess(Object o) {
                getView().viewGetData(o);
            }

            @Override
            public void modelError(String error) {

            }
        });
    }

    @Override
    public void login(Map<String, String> map) {
        new MyModel().modelLogin(map, new IContract.ModelCallback() {
            @Override
            public void modelSuccess(Object o) {
                getView().viewGetData(o);
            }

            @Override
            public void modelError(String error) {

            }
        });
    }

    @Override
    public void reggister(Map<String, String> map) {
        new MyModel().modelRegister(map, new IContract.ModelCallback() {
            @Override
            public void modelSuccess(Object o) {
                getView().viewGetData(o);
            }

            @Override
            public void modelError(String error) {

            }
        });
    }
}
