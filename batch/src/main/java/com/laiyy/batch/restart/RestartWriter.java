package com.laiyy.batch.restart;

import com.laiyy.batch.itemreadermultifile.City;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/11/26 11:29
 * @description
 */
//@Component
public class RestartWriter implements ItemWriter<City> {
    @Override
    public void write(List<? extends City> list) throws Exception {
        list.forEach(System.err::println);
    }
}
