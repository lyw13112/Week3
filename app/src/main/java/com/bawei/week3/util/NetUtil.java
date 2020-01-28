package com.bawei.week3.util;

import com.bawei.week3.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 刘云蔚
 * @createTime 2020/1/28 17:21
 * @description
 */
public class NetUtil {

    private final Retrofit retrofit;

    private NetUtil() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static class SingleHolder {
        private static final NetUtil UTIL = new NetUtil();
    }

    public static NetUtil getInstance() {
        return SingleHolder.UTIL;
    }

    public <T> T createService(Class<T> tClass) {
        return retrofit.create(tClass);
    }
}
