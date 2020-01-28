package com.bawei.week3.entity;

/**
 * @author 刘云蔚
 * @createTime 2020/1/12 14:00
 * @description
 */
public class LoginBean {
    public String message;
    public String status;
    public ResultBean result;

    public static class ResultBean {
        public int sex;
        public int userId;
        public String sessionId;
        public String phone;
        public String nickName;
        public String headPic;
    }
}
