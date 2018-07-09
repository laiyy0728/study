package com.laiyy.design.demo2.color;

import com.laiyy.design.demo2.Color;

/**
 * @author laiyy
 * @date 2018/7/5 17:20
 * @description
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("调用填充绿色的方法");
    }
}
