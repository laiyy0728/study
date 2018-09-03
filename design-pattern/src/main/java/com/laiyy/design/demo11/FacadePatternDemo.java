package com.laiyy.design.demo11;

/**
 * @author laiyy
 * @date 2018/9/3 14:08
 * @description
 */
public class FacadePatternDemo {

    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }

}
