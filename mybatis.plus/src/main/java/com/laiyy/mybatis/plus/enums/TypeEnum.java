package com.laiyy.mybatis.plus.enums;

/**
 * @author laiyy
 * @date 2018/8/20 10:00
 * @description
 */
public enum TypeEnum {

    // 禁用
    DISABLE(0, "禁用"),
    // 启用
    NORMAL(1, "启用");

    private final int value;

    private final String desc;

    TypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
