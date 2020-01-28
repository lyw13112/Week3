package com.bawei.week3.base;

import com.bawei.week3.contract.IContract;

import java.lang.ref.WeakReference;

/**
 * @author 刘云蔚
 * @createTime 2020/1/28 17:20
 * @description Presenter基类
 */
public abstract class BasePresenter<V extends BaseActivity> implements IContract.IPresenter {

    protected WeakReference<V> v;

    protected void attachView(V v) {
        this.v = new WeakReference<>(v);
    }

    protected void detachView() {
        v.clear();
        v = null;
    }

    protected V getView() {
        return v.get();
    }
}
