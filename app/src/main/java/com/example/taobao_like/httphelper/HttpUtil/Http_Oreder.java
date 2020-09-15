package com.example.taobao_like.httphelper.HttpUtil;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Http_Oreder {
    private static String url = "http://120.26.177.29:5001/";

    //增加订单
    public static void addOrder(int order_commodity_id,String order_user_name,String order_address,int order_num,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("order_commodity_id", String.valueOf(order_commodity_id))
                .add("order_user_name",order_user_name)
                .add("order_address",order_address)
                .add("order_num", String.valueOf(order_num))
                .build();
        Request request = new Request.Builder()
                .url(url+"add_order")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //删除订单
    public static void delOrder(int order_id,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("order_id", String.valueOf(order_id))
                .build();
        Request request = new Request.Builder()
                .url(url+"del_order")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //更改订单信息，不修改的传入“”
    public static void updateOrder(int order_id,String order_address,int order_num,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("order_id", String.valueOf(order_id))
                .add("order_address",order_address)
                .add("order_num", String.valueOf(order_num))
                .build();
        Request request = new Request.Builder()
                .url(url+"update_order")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //通过订单id获得订单信息
    public static void getOrderByID(int order_id,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("order_id", String.valueOf(order_id))
                .build();
        Request request = new Request.Builder()
                .url(url+"getorder_by_id")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //通过用户名获取订单信息
    public static void getOrderByUsername(String order_user_name,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("order_user_name", order_user_name)
                .build();
        Request request = new Request.Builder()
                .url(url+"getorder_by_user_name")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
