package com.laiyy.design.demo12;

import java.util.HashMap;

/**
 * @author laiyy
 * @date 2018/9/3 14:20
 * @description
 */
public class ShapeFactory {

    private static final HashMap<String, Shape> CIRCLE_MAP = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) CIRCLE_MAP.get(color);
        if (circle == null) {
            circle = new Circle(color);
            CIRCLE_MAP.put(color, circle);
            System.out.println("Creating circle of color:" + color);
        }
        return circle;
    }


}
