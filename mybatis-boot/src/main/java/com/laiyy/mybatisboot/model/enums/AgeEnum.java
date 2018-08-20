package com.laiyy.mybatisboot.model.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * @author laiyy
 * @date 2018/8/20 16:04
 * @description
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AgeEnum implements IEnum {
    // 1岁
    ONE(1,"一岁"),
    // 2岁
    TWO(2, "两岁");

    private int value;

    private String desc;

    AgeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }
}
