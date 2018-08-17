package com.laiyy.design.demo8.impl;

import com.laiyy.design.demo8.Criteria;
import com.laiyy.design.demo8.Person;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/8/17 16:53
 * @description
 */
public class AndCriteria implements Criteria {

    private Criteria criteria;

    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> people) {
        List<Person> personList = criteria.meetCriteria(people);
        return otherCriteria.meetCriteria(personList);
    }
}
