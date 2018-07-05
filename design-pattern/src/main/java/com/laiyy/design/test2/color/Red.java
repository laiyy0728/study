package com.laiyy.design.test2.color;

import com.laiyy.design.test2.Color;

/**
 * @author laiyy
 * @date 2018/7/5 17:19
 * @description
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("调用填充红色的方法");
    }
}
