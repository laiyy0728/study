package com.laiyy.design.demo5;

/**
 * @author laiyy
 * @date 2018/7/9 10:22
 * @description
 */
public class PropertypePatternDemo {

    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape shape = ShapeCache.getShape("1");
        System.out.println("Shape: " + shape.getType());

        shape = ShapeCache.getShape("2");
        System.out.println("Shape: " + shape.getType());

        shape = ShapeCache.getShape("3");
        System.out.println("Shape: " + shape.getType());
    }

}
