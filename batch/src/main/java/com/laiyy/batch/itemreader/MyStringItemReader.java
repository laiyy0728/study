package com.laiyy.batch.itemreader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/11/16 9:43
 * @description
 */
public class MyStringItemReader implements ItemReader<String> {

    private Iterator<String> iterator;

    public MyStringItemReader(List<String> data) {
        this.iterator = data.iterator();
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        // 一个数据一个数据的读
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
}
