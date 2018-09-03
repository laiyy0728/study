package com.laiyy.design.demo10;

/**
 * @author laiyy
 * @date 2018/9/3 10:44
 * @description
 */
public abstract class ShapeDecorator implements Shape{

    protected Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        shape.draw();
    }
}
