package com.laiyy.design.demo15;

/**
 * @author laiyy
 * @date 2018/9/3 15:00
 * @description
 */
public class SellSock implements Order {

    private Stock stock;

    public SellSock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell();
    }
}
