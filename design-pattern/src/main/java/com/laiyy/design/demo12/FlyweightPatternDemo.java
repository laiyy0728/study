package com.laiyy.design.demo12;

/**
 * @author laiyy
 * @date 2018/9/3 14:22
 * @description
 */
public class FlyweightPatternDemo {

    private static final String[] COLORS = {"RED", "GREEN", "BLUE", "WHITE", "BLACK"};

    public static void main(String[] args) {
        for (int index = 0; index < 20; index++) {
            Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.setRadius(100);
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.draw();
        }
    }


    private static int getRandomY(){
        return (int) (Math.random() * COLORS.length);
    }


    private static int getRandomX(){
        return (int) (Math.random() * COLORS.length);
    }

    private static String getRandomColor(){
        return COLORS[(int) (Math.random() * COLORS.length)];
    }

}
