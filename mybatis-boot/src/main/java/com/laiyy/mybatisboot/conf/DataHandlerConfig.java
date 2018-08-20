package com.laiyy.mybatisboot.conf;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author laiyy
 * @date 2018/8/20 15:50
 * @description
 */
@Component
public class DataHandlerConfig extends MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("插入数据时自动填充数据");
        setFieldValByName("testDate", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("修改数据时自动填充数据");
    }
}
