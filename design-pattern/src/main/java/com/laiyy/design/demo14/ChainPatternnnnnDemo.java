package com.laiyy.design.demo14;

/**
 * @author laiyy
 * @date 2018/9/3 14:49
 * @description
 */
public class ChainPatternnnnnDemo {

    private static AbstractLogger getChainOfLoggers(){
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger chainOfLoggers = getChainOfLoggers();
        chainOfLoggers.logMessage(AbstractLogger.INFO, "This is an information.");

        chainOfLoggers.logMessage(AbstractLogger.DEBUG, "This is an debug level information.");

        chainOfLoggers.logMessage(AbstractLogger.ERROR, "This is an error level information.");
    }

}
