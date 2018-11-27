package com.laiyy.batch.itemreaderfile;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/11/26 9:30
 * @description
 */
@Component
public class FlatFileWriter implements ItemWriter<Template> {
    @Override
    public void write(List<? extends Template> list) throws Exception {
        for (Template template : list) {
            System.out.println(template);
        }
        System.err.println("==================");
    }
}
