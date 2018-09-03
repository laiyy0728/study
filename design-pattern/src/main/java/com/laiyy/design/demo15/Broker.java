package com.laiyy.design.demo15;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/9/3 15:00
 * @description
 */
public class Broker {


    private List<Order> orderList = new ArrayList<>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void palceOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }

}
