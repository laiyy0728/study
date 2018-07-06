package com.laiyy.design.test4;

/**
 * @author laiyy
 * @date 2018/7/6 17:25
 * @description
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
