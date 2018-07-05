package com.laiyy.design.test1;

import com.laiyy.design.test1.shape.Circle;
import com.laiyy.design.test1.shape.Rectangle;
import com.laiyy.design.test1.shape.Square;

/**
 * @author laiyy
 * @date 2018/7/5 16:59
 * description：创建一个绘制图形的工厂，根据传入参数不同，绘制不同的图形
 */
public class ShapeFactory {

    public static final String CIRCLE = "CIRCLE";

    public static final String RECTANGLE = "RECTANGLE";

    public static final String SQUARE = "SQUARE";


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

