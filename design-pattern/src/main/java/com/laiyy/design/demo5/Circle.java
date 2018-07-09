package com.laiyy.design.demo5;

/**
 * @author laiyy
 * @date 2018/7/9 10:18
 * @description
 */
public class Circle extends Shape {

    public Circle() {
        type = "Circle";
    }

    @Override
    void draw() {
        System.out.println("正在绘制圆形");
    }
}
