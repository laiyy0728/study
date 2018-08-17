package com.laiyy.design.demo8.impl;

import com.laiyy.design.demo8.Criteria;
import com.laiyy.design.demo8.Person;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/8/17 16:55
 * @description
 */
public class OrCriteria implements Criteria {

    private Criteria criteria;

    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }


    @Override
    public List<Person> meetCriteria(List<Person> people) {
        List<Person> firstCriteriaItems = criteria.meetCriteria(people);
        List<Person> otherCriteriaItems = otherCriteria.meetCriteria(people);

        for (Person person : otherCriteriaItems) {
            if(!firstCriteriaItems.contains(person)){
                firstCriteriaItems.add(person);
            }
        }
        return firstCriteriaItems;
    }
}
