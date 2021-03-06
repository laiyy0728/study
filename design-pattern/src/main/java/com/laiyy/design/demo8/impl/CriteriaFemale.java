package com.laiyy.design.demo8.impl;

import com.laiyy.design.demo8.Criteria;
import com.laiyy.design.demo8.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author laiyy
 * @date 2018/8/17 16:51
 * @description
 */
public class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> people) {
        return people.stream().filter(person -> "female".equalsIgnoreCase(person.getGender())).collect(Collectors.toList());
    }
}
