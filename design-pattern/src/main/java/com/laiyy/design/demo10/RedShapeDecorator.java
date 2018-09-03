package com.laiyy.design.demo10;

/**
 * @author laiyy
 * @date 2018/9/3 10:49
 * @description
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        shape.draw();
        serRedBroder(shape);
    }

    public void serRedBroder(Shape shape){
        System.out.println("Border Color: Red");
    }
}
