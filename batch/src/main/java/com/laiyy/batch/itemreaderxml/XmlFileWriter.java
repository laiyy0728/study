package com.laiyy.batch.itemreaderxml;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/11/26 10:12
 * @description
 */
//@Component
public class XmlFileWriter implements ItemWriter<Label> {
    @Override
    public void write(List<? extends Label> list) throws Exception {
        list.forEach(System.out::println);
        System.err.println("===================");
    }
}
