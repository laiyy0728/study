package com.laiyy.batch.itemreadermultifile;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/11/26 10:54
 * @description
 */
//@Component
public class MultiFileWriter implements ItemWriter<City> {
    @Override
    public void write(List<? extends City> list) throws Exception {
        list.forEach(System.out::println);
        System.err.println("==================");
    }
}
