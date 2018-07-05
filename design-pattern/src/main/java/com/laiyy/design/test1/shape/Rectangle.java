package com.laiyy.design.test1.shape;

import com.laiyy.design.test1.Shape;

/**
 * @author laiyy
 * @date 2018/7/5 16:55
 * description：绘制一个长方形
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        // 调用统一接口，进行具体实现
        System.out.println("调用长方形的绘制接口");
    }
}
