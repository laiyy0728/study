package com.laiyy.cloud_java.mock;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author laiyy
 * @date 2018/11/30 17:23
 * @description
 */
@Entity
@Table(name = "t_cat")
@Data
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Cat(String name) {
        this.name = name;
    }
}
