package com.laiyy.design.demo13;

/**
 * @author laiyy
 * @date 2018/9/3 14:33
 * @description
 */
public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying: " + fileName);
    }
}
