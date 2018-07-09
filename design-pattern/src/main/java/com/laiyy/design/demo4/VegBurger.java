package com.laiyy.design.demo4;

/**
 * @author laiyy
 * @date 2018/7/6 17:27
 * @description
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "Veg burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}
