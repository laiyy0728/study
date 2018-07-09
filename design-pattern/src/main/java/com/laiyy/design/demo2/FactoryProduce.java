package com.laiyy.design.demo2;

import com.laiyy.design.demo2.factory.ColorFactory;
import com.laiyy.design.demo2.factory.ShapeFactory;

/**
 * @author laiyy
 * @date 2018/7/5 17:27
 * @description
 *
 * 工厂选择器
 */
public class FactoryProduce {

    public static final String SHAPE = "SHAPE";

    public static final String COLOR = "COLOR";


    public static AbstractFactory getFactory(String factory){
        if (SHAPE.equals(factory)) {
            return new ShapeFactory();
        } else if (COLOR.equals(factory)) {
            return new ColorFactory();
        }
        return null;
    }

}
