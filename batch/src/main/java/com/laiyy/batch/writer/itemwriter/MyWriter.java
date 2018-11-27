package com.laiyy.batch.writer.itemwriter;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/11/26 11:43
 * @description
 */
//@Component
public class MyWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        System.out.println(list.size());
        for (String item : list) {
            System.out.println(item);
        }
    }
}
