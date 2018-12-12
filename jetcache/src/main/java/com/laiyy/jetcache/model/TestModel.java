package com.laiyy.jetcache.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author laiyy
 * @date 2018/12/4 15:10
 * @description
 */
@Data
public class TestModel implements Serializable {

    private int id;

    private String name;

    public TestModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TestModel() {
    }
}
