package com.example.taobao_like.httphelper;

import android.util.Log;

import com.example.taobao_like.object.Commodity;
import com.example.taobao_like.object.User;
import com.example.taobao_like.object.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class JsonHelper {
    private int ret_code;
    private String data;

    //构造函数，用于获取ret_code
    public JsonHelper(Response response) throws IOException {
        //将response变成字符串，用于接下来的解析操作
        String responseData=response.body().string();
        Log.i("json",responseData);
        try{
            //将ret_code剥离下来，将data数据进一步解析
            JSONObject jsonObject=new JSONObject(responseData);
            this.data = jsonObject.getString("data");
            this.ret_code = jsonObject.getInt("ret_code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getRet_code() {
        return ret_code;
    }
    public String getData() {
        return data;
    }

    //从json串中获取user对象
    public User getuser(String data) {
        Log.i("json_data",data);
        try{
            JSONObject jsonObject=new JSONObject(data);
            //获取user相应属性
            String name = jsonObject.getString("user_name");
            String password = jsonObject.getString("user_password");
            String address = jsonObject.getString("user_address");
            //返回User对象
            User user = new User(name,password,address);
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //从json串中获取商品对象
    public List<Commodity> getcommodity(String data,int flag) {
        //创建一个Commodity数组用于存放获得的多个commodity
        List<Commodity> commodityList = new ArrayList<>();
        Log.i("json_data",data);
        try{
            //只返回了一个商品对象
            if(flag == 1) {
                JSONObject jsonObject=new JSONObject(data);
                int commodity_id = jsonObject.getInt("commodity_id");
                String commodity_user_name = jsonObject.getString("commodity_user_name");
                String commodity_name = jsonObject.getString("commodity_name");
                String commodity_info = jsonObject.getString("commodity_info");
                String commodity_price = jsonObject.getString("commodity_price");
                int commodity_stock = jsonObject.getInt("commodity_stock");
                String commodity_resource = jsonObject.getString("commodity_resource");
                Commodity commodity = new Commodity(commodity_id,commodity_user_name,commodity_name,commodity_info,commodity_price,commodity_stock,commodity_resource);
                commodityList.add(commodity);
                return commodityList;
            }
            //返回了多个商品对象
            else if (flag == 2) {

                JSONArray jsonArray = new JSONArray(data);
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    int commodity_id = jsonObject.getInt("commodity_id");
                    String commodity_user_name = jsonObject.getString("commodity_user_name");
                    String commodity_name = jsonObject.getString("commodity_name");
                    String commodity_info = jsonObject.getString("commodity_info");
                    String commodity_price = jsonObject.getString("commodity_price");
                    int commodity_stock = jsonObject.getInt("commodity_stock");
                    String commodity_resource = jsonObject.getString("commodity_resource");
                    Commodity commodity = new Commodity(commodity_id,commodity_user_name,commodity_name,commodity_info,commodity_price,commodity_stock,commodity_resource);
                    commodityList.add(commodity);
                }
                return commodityList;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //从json串中获取订单对象
    public List<Order> getorder(String data,int flag) {
        //创建一个Commodity数组用于存放获得的多个commodity
        List<Order> orderList = new ArrayList<>();
        Log.i("json_data",data);
        try{
            //只返回了一个订单对象
            if(flag == 1) {
                JSONObject jsonObject=new JSONObject(data);
                int order_id = jsonObject.getInt("order_id");
                int order_commodity_id = jsonObject.getInt("order_commodity_id");
                String order_user_name = jsonObject.getString("order_user_name");
                String order_address = jsonObject.getString("order_address");
                int order_stock = jsonObject.getInt("order_stock");
                int order_status = jsonObject.getInt("order_status");
                Order order = new Order(order_id,order_commodity_id,order_user_name,order_address,order_stock,order_status);
                orderList.add(order);
                return orderList;
            }
            //返回了多个订单对象
            else if (flag == 2) {

                JSONArray jsonArray = new JSONArray(data);
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    int order_id = jsonObject.getInt("order_id");
                    int order_commodity_id = jsonObject.getInt("order_commodity_id");
                    String order_user_name = jsonObject.getString("order_user_name");
                    String order_address = jsonObject.getString("order_address");
                    int order_stock = jsonObject.getInt("order_stock");
                    int order_status = jsonObject.getInt("order_status");
                    Order order = new Order(order_id,order_commodity_id,order_user_name,order_address,order_stock,order_status);
                    orderList.add(order);
                }
                return orderList;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

