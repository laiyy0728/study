package com.laiyy.mybatisboot.model.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * @author laiyy
 * @date 2018/8/20 16:16
 * @description
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PhoneEnum implements IEnum {
    // 移动
    CMCC("10086", "中国移动"),
    CUCC("10010", "中国联通"),
    CT("10000", "中国电信");

    private String value;
    private String desc;

    PhoneEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }
}
