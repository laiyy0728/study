package com.laiyy.design.demo7;

/**
 * @author laiyy
 * @date 2018/8/17 16:22
 * @description
 */
public abstract class Shape {

    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();

}
