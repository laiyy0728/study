package com.laiyy.kafka.demo2;

/**
 * @author laiyy
 * @date 2018/7/30 17:16
 * @description 表示用户
 */
public class Customer {

    private int customerID;

    private String customerName;

    public Customer(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
