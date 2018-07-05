package com.laiyy.design.test2;

import com.laiyy.design.test2.factory.ColorFactory;
import com.laiyy.design.test2.factory.ShapeFactory;
import sun.security.provider.SHA;

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
