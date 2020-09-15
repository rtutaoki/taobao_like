package com.example.taobao_like.httphelper.HttpUtil;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Http_Commodity {
    private static String url = "http://120.26.177.29:5001/";

    //添加商品,只有commodity_resource可以为空用“”表示,commodity_user_name和commodity_name为联合主键
    public static void addCommodity(String commodity_user_name,String commodity_name,String commodity_info,String commodity_price,int commodity_stock,String commodity_resource,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("commodity_user_name",commodity_user_name)
                .add("commodity_name",commodity_name)
                .add("commodity_info",commodity_info)
                .add("commodity_price",commodity_price)
                .add("commodity_stock", String.valueOf(commodity_stock))
                .add("commodity_resource",commodity_resource)
                .build();
        Request request = new Request.Builder()
                .url(url+"add_commodity")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //删除商品
    public static void delComByUserName(int commodity_id,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("commodity_user_name", String.valueOf(commodity_id))
                .build();
        Request request = new Request.Builder()
                .url(url+"del_commodity")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //更改商品信息
    public static void updateCommodity(String commodity_user_name,String commodity_name,String commodity_info,String commodity_price,int commodity_stock,String commodity_resource,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("commodity_user_name",commodity_user_name)
                .add("commodity_name",commodity_name)
                .add("commodity_info",commodity_info)
                .add("commodity_price",commodity_price)
                .add("commodity_stock", String.valueOf(commodity_stock))
                .add("commodity_resource",commodity_resource)
                .build();
        Request request = new Request.Builder()
                .url(url+"update_commodity")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //根据商品id获取商品对象
    public static void getCommodity(int commodity_id,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("commodity_id", String.valueOf(commodity_id))
                .build();
        Request request = new Request.Builder()
                .url(url+"update_commodity")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //用用户名获取所有商品对象
    public static void getComByUserName(String commodity_user_name,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("commodity_user_name",commodity_user_name)
                .build();
        Request request = new Request.Builder()
                .url(url+"get_allcommodity_byuser_name")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //用商品名获取所有商品对象
    public static void getComByComName(String name_like, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("name_like",name_like)
                .build();
        Request request = new Request.Builder()
                .url(url+"get_allcommodity_byname")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
