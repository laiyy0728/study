package com.laiyy.batch.itemreaderxml;

import lombok.Data;

import java.util.Date;

/**
 * Created by laiyy
 * Date 2018/2/6.
 * <p>
 * 标签库
 */
@Data
public class Label {

    /**
     * 标签 id
     */
    private int id;

    /**
     * 标签名
     */
    private String label = "";

    /**
     * 标签类型
     */
    private int type;

    private String labelType = "";


    /**
     * 是否是私人？ 有用户id是私人，没有用户id是共有
     */
    private int userId;

    /**
     * 创建人id
     */
    private int createUserId;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 创建人
     */
    private String username = "";

    /**
     * 标签状态 0：禁用，1：启用
     */
    private int status = 1;

    /**
     * 站点
     */
    private String siteId = "";

    /**
     * 排序
     */
    private int seq;

}
