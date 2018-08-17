package com.laiyy.design.demo7.extend;

import com.laiyy.design.demo7.DrawAPI;
import com.laiyy.design.demo7.Shape;

/**
 * @author laiyy
 * @date 2018/8/17 16:23
 * @description
 */
public class Circle extends Shape {

    private int x, y, radius;

    public Circle(DrawAPI drawAPI, int x, int y, int radius) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}
