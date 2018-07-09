package com.laiyy.design.demo4;

/**
 * @author laiyy
 * @date 2018/7/6 17:27
 * @description
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}
