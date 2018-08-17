package com.laiyy.design.demo7.impl;

import com.laiyy.design.demo7.DrawAPI;

/**
 * @author laiyy
 * @date 2018/8/17 16:22
 * @description
 */
public class GreenCircle implements DrawAPI {

    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing circle [ color: green, radius: " + radius + ", x: " + x + ",y : " + y + "]");
    }
}
