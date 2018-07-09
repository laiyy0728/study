package com.laiyy.design.demo3.demo1;

/**
 * @author laiyy
 * @date 2018/7/6 16:47
 * @description
 */
public class SingletonPatternDemo {

    public static void main(String[] args) {
        // 不合法的构造函数
        // 编译时错误：构造函数 SingleObject() 是不可见的
        // SingleObject object = new SingleObject();

        // 获取唯一可用对象
        SingleObject instance = SingleObject.getInstance();

        // 显示消息
        instance.showMessage();

    }


}
