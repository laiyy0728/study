package com.laiyy.design.demo3.demo2;

/**
 * @author laiyy
 * @date 2018/7/6 16:50
 * @description
 *
 * 懒汉式，线程不安全
 *
 * 是否 Lazy 初始化：是
 * 是否多线程安全：否
 * 实现难度：易
 * 描述：这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
 * 这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
 */
public class Singleton {

    private static Singleton instance;

    private Singleton(){

    }

    /**
     * 懒汉式（需要时再创建），线程不安全
     */
    public static Singleton getInstance(){
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }



}
