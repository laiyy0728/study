package com.laiyy.mybatisboot.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

/**
 * @author laiyy
 * @date 2018/8/20 16:21
 * @description
 */
public class SuperEntity<T extends Model> extends Model<T> {

//    private long id;
//
    @TableField(value = "tenant_id")
    private long tenantId;

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public long getTenantId() {
        return tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    protected Serializable pkVal() {
        return this.tenantId;
    }
}
