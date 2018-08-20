package com.laiyy.mybatis.plus.common;

import java.io.Serializable;

/**
 * @author laiyy
 * @date 2018/8/20 9:59
 * @description
 */
public class SuperEntity implements Serializable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
