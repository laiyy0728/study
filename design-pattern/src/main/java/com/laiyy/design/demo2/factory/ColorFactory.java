package com.laiyy.design.demo2.factory;

import com.laiyy.design.demo2.AbstractFactory;
import com.laiyy.design.demo2.Color;
import com.laiyy.design.demo2.Shape;
import com.laiyy.design.demo2.color.Blue;
import com.laiyy.design.demo2.color.Green;
import com.laiyy.design.demo2.color.Red;

/**
 * @author laiyy
 * @date 2018/7/5 17:25
 * @description
 */
public class ColorFactory extends AbstractFactory {

    public static final String RED = "RED";

    public static final String GREEN = "GREEN";

    public static final String BLUE = "BLUE";

    @Override
    protected Color getColor(String color) {
        if (color == null){
            return null;
        }
        if (RED.equals(color)) {
            return new Red();
        } else if (GREEN.equals(color)) {
            return new Green();
        } else if (BLUE.equals(color)) {
            return new Blue();
        }
        return null;
    }

    @Override
    protected Shape getShape(String shape) {
        return null;
    }
}
