package com.laiyy.design.demo13;

/**
 * @author laiyy
 * @date 2018/9/3 14:34
 * @description
 */
public class ProxyImage implements Image{

    private RealImage realImage;

    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
