package com.laiyy.design.test1.shape;

import com.laiyy.design.test1.Shape;

/**
 * @author laiyy
 * @date 2018/7/5 16:58
 * description：绘制一个圆形
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("调用圆形的绘制接口");
    }
}
