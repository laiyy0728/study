package com.laiyy.mybatisboot.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.laiyy.mybatisboot.model.enums.AgeEnum;
import com.laiyy.mybatisboot.model.enums.PhoneEnum;

import java.util.Date;

/**
 * @author laiyy
 * @date 2018/8/20 16:03
 * @description
 */
public class User extends SuperEntity<User>{

    private String name;

    @TableField(value = "age")
    private AgeEnum ageEnum;

    @TableField(value = "test_type")
    @TableLogic
    private int type;

    @TableField(fill = FieldFill.INSERT, value = "test_date")
    private Date testDate;

    private long role;

    @TableField(value = "phone")
    private PhoneEnum phoneEnum;

    public User() {
    }

    public User(Long id, String name, AgeEnum age, Integer testType) {
        this.setTenantId(id);
        this.name = name;
        this.ageEnum = age;
        this.type = testType;
    }

    public User(String name, AgeEnum age, Integer testType) {
        this.name = name;
        this.ageEnum = age;
        this.type = testType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id= " + pkVal() +
                ", name='" + name + '\'' +
                ", ageEnum=" + ageEnum +
                ", type=" + type +
                ", testDate=" + testDate +
                ", role=" + role +
                ", phoneEnum=" + phoneEnum +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AgeEnum getAgeEnum() {
        return ageEnum;
    }

    public void setAgeEnum(AgeEnum ageEnum) {
        this.ageEnum = ageEnum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public PhoneEnum getPhoneEnum() {
        return phoneEnum;
    }

    public void setPhoneEnum(PhoneEnum phoneEnum) {
        this.phoneEnum = phoneEnum;
    }
}
