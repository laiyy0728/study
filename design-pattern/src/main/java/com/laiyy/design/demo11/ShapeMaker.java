package com.laiyy.design.demo11;

/**
 * @author laiyy
 * @date 2018/9/3 14:06
 * @description
 */
public class ShapeMaker {

    private Shape circle;

    private Shape rectangle;

    private Shape square;

    public ShapeMaker(){
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle(){
        circle.draw();
    }

    public void drawRectangle(){
        rectangle.draw();
    }

    public void drawSquare(){
        square.draw();
    }
}
