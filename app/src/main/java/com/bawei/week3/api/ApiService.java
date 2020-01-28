package com.bawei.week3.api;

import com.bawei.week3.entity.DataBean;
import com.bawei.week3.entity.LoginBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author 刘云蔚
 * @createTime 2020/1/28 17:19
 * @description
 */
public interface ApiService {
    @POST(Api.login)
    Observable<LoginBean> login(@QueryMap Map<String, String> map);

    @POST(Api.register)
    Observable<LoginBean> register(@QueryMap Map<String, String> map);

    @GET(Api.findCategory)
    Observable<DataBean> getData(@HeaderMap Map<String, String> map);
}
