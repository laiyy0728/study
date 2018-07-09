package com.laiyy.design.demo2;

import com.laiyy.design.demo1.ShapeFactory;
import com.laiyy.design.demo2.factory.ColorFactory;

/**
 * @author laiyy
 * @date 2018/7/5 17:27
 * @description 测试抽象工厂（工厂的工厂）
 */
public class Test {

    public static void main(String[] args) {
        // 图形工厂
        AbstractFactory factory = FactoryProduce.getFactory(FactoryProduce.SHAPE);

        if (factory == null) {
            System.out.println("获取图形工厂失败");
            return;
        }
        // 绘制圆形
        factory.getShape(ShapeFactory.CIRCLE).draw();

        // 绘制正方形
        factory.getShape(ShapeFactory.SQUARE).draw();

        // 绘制长方形
        factory.getShape(ShapeFactory.RECTANGLE).draw();

        // 颜色工厂
        factory = FactoryProduce.getFactory(FactoryProduce.COLOR);

        if (factory == null) {
            System.out.println("获取颜色工厂失败");
            return;
        }
        // 绘制红色
        factory.getColor(ColorFactory.RED).fill();

        // 绘制蓝色
        factory.getColor(ColorFactory.BLUE).fill();

        // 绘制绿色
        factory.getColor(ColorFactory.GREEN).fill();


    }

}
