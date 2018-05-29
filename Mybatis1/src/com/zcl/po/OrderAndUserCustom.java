package com.zcl.po;

/**
 * Created by Administrator on 2018-05-16.
 */
public class OrderAndUserCustom extends Orders {
    private String username;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderAndUserCustom{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                "} " + super.toString();
    }
}
