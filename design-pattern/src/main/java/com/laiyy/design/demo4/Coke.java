package com.laiyy.design.demo4;

/**
 * @author laiyy
 * @date 2018/7/6 17:28
 * @description
 */
public class Coke extends ClodDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}
