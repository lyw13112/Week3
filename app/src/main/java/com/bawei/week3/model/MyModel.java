package com.bawei.week3.model;

import com.bawei.week3.api.ApiService;
import com.bawei.week3.contract.IContract;
import com.bawei.week3.entity.DataBean;
import com.bawei.week3.entity.LoginBean;
import com.bawei.week3.util.NetUtil;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 刘云蔚
 * @createTime 2020/1/28 17:21
 * @description M层
 */
public class MyModel implements IContract.IModel {
    @Override
    public void modelLogin(Map<String, String> map, IContract.ModelCallback callback) {
        NetUtil.getInstance().createService(ApiService.class).login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        callback.modelSuccess(loginBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.modelError(throwable.getMessage());
                    }
                });
    }

    @Override
    public void modelRegister(Map<String, String> map, IContract.ModelCallback callback) {
        NetUtil.getInstance().createService(ApiService.class).register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        callback.modelSuccess(loginBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.modelError(throwable.getMessage());
                    }
                });
    }

    @Override
    public void modelGetData(Map<String, String> map, IContract.ModelCallback callback) {
        NetUtil.getInstance().createService(ApiService.class).getData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DataBean>() {
                    @Override
                    public void accept(DataBean dataBean) throws Exception {
                        callback.modelSuccess(dataBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.modelError(throwable.getMessage());
                    }
                });
    }
}
