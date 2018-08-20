package com.laiyy.mybatis.plus.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.laiyy.mybatis.plus.common.SuperEntity;
import com.laiyy.mybatis.plus.enums.TypeEnum;

import java.util.Date;

/**
 * @author laiyy
 * @date 2018/8/20 9:58
 * @description
 */
@TableName("sys_user")
public class User extends SuperEntity {

    /**
     * 可以使用 @TableField(condition=SqlCondition=LIKE) 模糊查询
     */
    private String name;

    private int age;

    /**
     * FieldFill.INSERT 自动填充插入字段值
     * FieldFill.UPDATE 自动填充更新字段值
     * FieldFill.INSERT_UPDATE 自动填充插入并更新字段
     * FieldFill.DEFAULT 不处理字段值
     *
     * 可以使用 update="now()" 更新数据库时间
     */
    @TableField(fill = FieldFill.INSERT, value = "ctime")
    private Date createTime;

    @TableField("type")
    private TypeEnum typeEnum;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", createTime=" + createTime +
                ", typeEnum=" + typeEnum +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public TypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(TypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }
}
