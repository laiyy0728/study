package com.laiyy.design.demo15;

/**
 * @author laiyy
 * @date 2018/9/3 15:01
 * @description
 */
public class CommandPatternDemo {

    public static void main(String[] args) {
        Stock stock = new Stock();

        BuyStock buyStock = new BuyStock(stock);
        SellSock sellSock = new SellSock(stock);

        Broker broker = new Broker();
        broker.takeOrder(buyStock);
        broker.takeOrder(sellSock);

        broker.palceOrders();
    }

}
