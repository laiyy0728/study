package com.laiyy.design.test1;

/**
 * @author laiyy
 * @date 2018/7/5 17:03
 * @description
 *
 * 只使用一个工厂类，即可根据传入参数的不同，获取不同的实例，调用不同的方法
 */
public class Test {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        //获取 Circle 的对象，并调用它的 draw 方法
        shapeFactory.getShape(ShapeFactory.CIRCLE).draw();

        //调用 Rectangle  的 draw 方法
        shapeFactory.getShape(ShapeFactory.RECTANGLE).draw();

        //获取 Square 的对象，并调用它的 draw 方法
        shapeFactory.getShape(ShapeFactory.SQUARE).draw();

    }

}
