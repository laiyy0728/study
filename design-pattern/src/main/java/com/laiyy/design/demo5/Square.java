package com.laiyy.design.demo5;

/**
 * @author laiyy
 * @date 2018/7/9 10:17
 * @description
 */
public class Square extends Shape {

    public Square() {
        type = "Square";
    }

    @Override
    void draw() {
        System.out.println("正在绘制正方形");
    }
}
