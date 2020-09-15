package com.example.taobao_like.object;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String password;
    private String address;

    public User(String name,String password,String address) {
        this.name = name;
        this.password = password;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }
}
