package com.laiyy.design.demo1.shape;

import com.laiyy.design.demo1.Shape;

/**
 * @author laiyy
 * @date 2018/7/5 16:58
 * description：绘制一个正方形
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("调用正方形的绘制接口");
    }
}
