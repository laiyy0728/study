package com.laiyy.design.demo14;

/**
 * @author laiyy
 * @date 2018/9/3 14:47
 * @description
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
