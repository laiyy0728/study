package com.laiyy.design.demo7.impl;

import com.laiyy.design.demo7.DrawAPI;

/**
 * @author laiyy
 * @date 2018/8/17 16:21
 * @description
 */
public class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing circle [ color: red, radius: " + radius + ", x: " + x + ",y : " + y + "]");
    }
}
