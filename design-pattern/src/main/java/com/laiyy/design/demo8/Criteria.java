package com.laiyy.design.demo8;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/8/17 16:34
 * @description
 */
public interface Criteria {

    List<Person> meetCriteria(List<Person> people);

}
