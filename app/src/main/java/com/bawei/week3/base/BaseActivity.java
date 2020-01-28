package com.bawei.week3.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.week3.contract.IContract;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 刘云蔚
 * @createTime 2020/1/28 17:20
 * @description Activity基类
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IContract.IView {
    protected P p;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        //绑定ButterKnife
        bind = ButterKnife.bind(this);
        p = initPresenter();
        if (p != null) p.attachView(this);
        initView();
        initData();
    }

    protected abstract int initLayout();

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (p != null) {
            p.detachView();
            p = null;
        }
        if (bind != null) {
            bind.unbind();
        }
    }
}
