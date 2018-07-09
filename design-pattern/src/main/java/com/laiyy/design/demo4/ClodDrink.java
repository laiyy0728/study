package com.laiyy.design.demo4;

/**
 * @author laiyy
 * @date 2018/7/6 17:26
 * @description
 */
public abstract class ClodDrink implements Item{

    @Override
    public Packing packing(){
        return new Bottle();
    }

    @Override
    public abstract float price();

}
