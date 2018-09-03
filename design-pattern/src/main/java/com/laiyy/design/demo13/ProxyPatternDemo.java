package com.laiyy.design.demo13;

/**
 * @author laiyy
 * @date 2018/9/3 14:35
 * @description
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("proxy");

        image.display();

        image.display();
    }

}
