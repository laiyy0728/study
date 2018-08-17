package com.laiyy.design.demo8.impl;

import com.laiyy.design.demo8.Criteria;
import com.laiyy.design.demo8.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author laiyy
 * @date 2018/8/17 16:52
 * @description
 */
public class CriteriaSingle implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> people) {
        return people.stream().filter(person -> "single".equalsIgnoreCase(person.getMaritalStatus())).collect(Collectors.toList());
    }
}
