package com.laiyy.design.demo2.factory;

import com.laiyy.design.demo2.AbstractFactory;
import com.laiyy.design.demo2.Color;
import com.laiyy.design.demo2.Shape;
import com.laiyy.design.demo2.shape.Circle;
import com.laiyy.design.demo2.shape.Rectangle;
import com.laiyy.design.demo2.shape.Square;

;

/**
 * @author laiyy
 * @date 2018/7/5 16:59
 * description：创建一个绘制图形的工厂，根据传入参数不同，绘制不同的图形
 */
public class ShapeFactory extends AbstractFactory {
    public static final String CIRCLE = "CIRCLE";

    public static final String RECTANGLE = "RECTANGLE";

    public static final String SQUARE = "SQUARE";


    @Override
    protected Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (CIRCLE.equals(shapeType)) {
            return new Circle();
        } else if (RECTANGLE.equals(shapeType)) {
            return new Rectangle();
        } else if (SQUARE.equals(shapeType)) {
            return new Square();
        }
        return null;
    }
}

