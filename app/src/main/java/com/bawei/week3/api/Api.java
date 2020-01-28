package com.bawei.week3.api;

/**
 * @author 刘云蔚
 * @createTime 2020/1/28 17:19
 * @description
 */
public class Api {
    public static final String BASE_URL = "http://mobile.bwstudent.com/small/";

    //查询购物车
    public static final String findCategory = "order/verify/v1/findShoppingCart";

    //登录
    public static final String login = "user/v1/login";

    //注册
    public static final String register = "user/v1/register";
}
