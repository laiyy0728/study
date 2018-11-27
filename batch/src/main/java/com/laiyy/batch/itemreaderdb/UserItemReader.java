package com.laiyy.batch.itemreaderdb;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

/**
 * @author laiyy
 * @date 2018/11/16 10:03
 * @description
 */
//@Component
public class UserItemReader implements ItemReader<User> {
    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
