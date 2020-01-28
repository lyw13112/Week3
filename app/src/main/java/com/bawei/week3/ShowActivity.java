package com.bawei.week3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week3.adapter.BigAdapter;
import com.bawei.week3.base.BaseActivity;
import com.bawei.week3.base.BasePresenter;
import com.bawei.week3.entity.DataBean;
import com.bawei.week3.presenter.MyPresenter;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 刘云蔚
 * @createTime 2020/1/28 17:28
 * @description 购物车页面
 */
public class ShowActivity extends BaseActivity {

    @BindView(R.id.image_headPic)
    ImageView imageHeadPic;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.check_all)
    CheckBox checkAll;
    @BindView(R.id.text_all_price)
    TextView textAllPrice;
    @BindView(R.id.btn_pay)
    Button btnPay;
    private DataBean bean;
    private BigAdapter adapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_show;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        String sessionId = intent.getStringExtra("sessionId");
        String headPic = intent.getStringExtra("headPic");
        Glide.with(this)
                .load(headPic)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.mipmap.ic_launcher)
                .into(imageHeadPic);
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        p.getData(map);
    }

    @Override
    public void viewGetData(Object o) {
        if (o instanceof DataBean){
            bean = (DataBean) o;

        }
    }

    @OnClick({R.id.check_all, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_all:
                adapter = new BigAdapter(this, bean.result);
                recycler.setAdapter(adapter);
                countPrice();
                //全选按钮监听事件
                checkAll.setOnClickListener(v -> {
                    for (DataBean.ResultBean resultBean : bean.result) {
                        for (DataBean.ResultBean.ShoppingCartListBean cartList : resultBean.shoppingCartList) {
                            cartList.isChecked=checkAll.isChecked();
                        }
                        resultBean.isChecked=checkAll.isChecked();
                    }
                    adapter.notifyDataSetChanged();

                    //计算总价
                    countPrice();
                });
                break;
            case R.id.btn_pay:

                break;
        }
    }

    //判断是否全选+++计算总价
    public void countPrice() {
        boolean isAllCheck = true;
        int allPrice = 0;
        for (DataBean.ResultBean resultBean : bean.result) {
            for (DataBean.ResultBean.ShoppingCartListBean listBean : resultBean.shoppingCartList) {
                if (!listBean.isChecked) {
                    isAllCheck = false;
                    allPrice += listBean.price * listBean.num;
                }
                if (!resultBean.isChecked) isAllCheck = false;
            }
            textAllPrice.setText("￥" + allPrice);
        }
        checkAll.setChecked(isAllCheck);
    }
}
