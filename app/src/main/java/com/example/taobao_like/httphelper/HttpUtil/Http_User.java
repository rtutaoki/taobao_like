package com.example.taobao_like.httphelper.HttpUtil;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Http_User {
    private static String url = "http://120.26.177.29:5001/";

    //登录
    public static void login(String user_name,String user_password,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("user_name",user_name)
                .add("password",user_password)
                .build();
        Request request = new Request.Builder()
                .url(url+"login")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //注册
    public static void register(String user_name,String user_password,String user_address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("user_name",user_name)
                .add("password",user_password)
                .add("user_address",user_address)
                .build();
        Request request = new Request.Builder()
                .url(url+"register")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //更改用户信息，不更改的赋值""
    public static void updateUser(String user_name,String user_password,String user_address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("user_name",user_name)
                .add("user_password",user_password)
                .add("user_address",user_address)
                .build();
        Request request = new Request.Builder()
                .url(url+"update_user")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //删除用户（user_name）
    public static void delUser(String user_name,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("user_name",user_name)
                .build();
        Request request = new Request.Builder()
                .url(url+"del_user")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //用用户名获取用户对象
    public static void getUser(String user_name,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("user_name",user_name)
                .build();
        Request request = new Request.Builder()
                .url(url+"getuser")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
