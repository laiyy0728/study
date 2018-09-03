package com.laiyy.design.demo15;

/**
 * @author laiyy
 * @date 2018/9/3 14:57
 * @description
 */
public class Stock {

    private String name = "ABC";

    private int quantity = 10;

    public void buy(){
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
    }

    public void sell(){
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] Sold");
    }

}
