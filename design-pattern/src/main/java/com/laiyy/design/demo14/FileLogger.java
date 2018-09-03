package com.laiyy.design.demo14;

/**
 * @author laiyy
 * @date 2018/9/3 14:48
 * @description
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
