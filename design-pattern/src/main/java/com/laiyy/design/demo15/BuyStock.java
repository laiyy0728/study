package com.laiyy.design.demo15;

/**
 * @author laiyy
 * @date 2018/9/3 14:59
 * @description
 */
public class BuyStock implements Order {

    private Stock stock;

    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy();
    }
}
