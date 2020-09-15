package com.example.taobao_like.object;

import java.io.Serializable;

public class Commodity implements Serializable {
    private int commodity_id;
    private String commodity_user_name;
    private String commodity_name;
    private String commodity_info;
    private String commodity_price;
    private int commodity_stock;
    private String commodity_resource;

    public Commodity(int commodity_id,String commodity_user_name,String commodity_name,String commodity_info,String commodity_price,int commodity_stock,String commodity_resource) {
        this.commodity_id = commodity_id;
        this.commodity_user_name = commodity_user_name;
        this.commodity_name = commodity_name;
        this.commodity_info = commodity_info;
        this.commodity_price = commodity_price;
        this.commodity_stock = commodity_stock;
        this.commodity_resource = commodity_resource;
    }

    public int getCommodity_id() {
        return commodity_id;
    }

    public String getCommodity_user_name() {
        return commodity_user_name;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public String getCommodity_info() {
        return commodity_info;
    }

    public String getCommodity_price() {
        return commodity_price;
    }

    public int getCommodity_stock() {
        return commodity_stock;
    }

    public String getCommodity_resource() {
        return commodity_resource;
    }
}
