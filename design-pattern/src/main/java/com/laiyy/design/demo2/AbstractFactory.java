package com.laiyy.design.demo2;

/**
 * @author laiyy
 * @date 2018/7/5 17:21
 * @description
 */
public abstract class AbstractFactory {

    protected abstract Color getColor(String color);

    protected abstract Shape getShape(String shape);

}
