package com.laiyy.design.demo5;

/**
 * @author laiyy
 * @date 2018/7/9 10:16
 * @description
 */
public class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    void draw() {
        System.out.println("正在绘制长方形");
    }
}
