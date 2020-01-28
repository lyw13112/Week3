package com.bawei.week3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.week3.R;
import com.bawei.week3.base.BaseActivity;
import com.bawei.week3.base.BasePresenter;
import com.bawei.week3.entity.LoginBean;
import com.bawei.week3.presenter.MyPresenter;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 刘云蔚
 * @createTime 2020/1/28 17:24
 * @description 登录注册
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwd)
    EditText editPwd;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
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

    }

    @Override
    public void viewGetData(Object o) {
        if (o instanceof LoginBean) {
            LoginBean bean = (LoginBean) o;
            Toast.makeText(this, bean.message, Toast.LENGTH_SHORT).show();
            if (bean.result != null) {
                Intent intent = new Intent(this, ShowActivity.class);
                intent.putExtra("userId", bean.result.userId + "");
                intent.putExtra("sessionId", bean.result.sessionId + "");
                intent.putExtra("headPic", bean.result.headPic);
                startActivity(intent);
            }
        }
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        String phone = editPhone.getText().toString();
        String pwd = editPwd.getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", pwd);
        switch (view.getId()) {
            case R.id.btn_login:
                p.login(map);
                break;
            case R.id.btn_register:
                p.reggister(map);
                break;
        }
    }
}
