package com.laiyy.design.demo7;

import com.laiyy.design.demo7.extend.Circle;
import com.laiyy.design.demo7.impl.GreenCircle;
import com.laiyy.design.demo7.impl.RedCircle;

/**
 * @author laiyy
 * @date 2018/8/17 16:25
 * @description
 */
public class BridgePatterDemo {

    public static void main(String[] args) {
        Circle readCircle = new Circle(new RedCircle(), 100, 100, 10);
        Circle greenCircle = new Circle(new GreenCircle(), 100, 100, 10);

        readCircle.draw();
        greenCircle.draw();
    }

}
