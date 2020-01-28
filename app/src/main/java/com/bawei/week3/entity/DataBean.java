package com.bawei.week3.entity;

import java.util.List;

/**
 * @author 刘云蔚
 * @createTime 2020/1/6 20:32
 * @description
 */
public class DataBean {
    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        public String categoryName;
        public List<ShoppingCartListBean> shoppingCartList;
        public boolean isChecked;

        public static class ShoppingCartListBean {
            public int commodityId;
            public String commodityName;
            public int count;
            public String pic;
            public int price;
            public boolean isChecked;
            public int num;
        }
    }
}