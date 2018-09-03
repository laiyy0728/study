package com.laiyy.design.demo14;

/**
 * @author laiyy
 * @date 2018/9/3 14:47
 * @description
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger:" + message);
    }
}
