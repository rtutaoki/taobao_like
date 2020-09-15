package com.example.taobao_like.object;

import java.io.Serializable;

public class Order implements Serializable {
    private int order_id;
    private int order_commodity_id;
    private String order_user_name;
    private String order_address;
    private int order_num;
    private int order_status;

    public Order(int order_id,int order_commodity_id,String order_user_name,String order_address,int order_num,int order_status) {
        this.order_id = order_id;
        this.order_commodity_id = order_commodity_id;
        this.order_user_name = order_user_name;
        this.order_address = order_address;
        this.order_num = order_num;
        this.order_status = order_status;
    }


    public int getOrder_id() {
        return order_id;
    }

    public int getOrder_commodity_id() {
        return order_commodity_id;
    }

    public String getOrder_user_name() {
        return order_user_name;
    }

    public String getOrder_address() {
        return order_address;
    }

    public int getOrder_num() {
        return order_num;
    }

    public int getOrder_status() {
        return order_status;
    }
}
