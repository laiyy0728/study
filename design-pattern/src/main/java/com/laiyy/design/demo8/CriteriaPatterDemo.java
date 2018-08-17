package com.laiyy.design.demo8;

import com.laiyy.design.demo8.impl.AndCriteria;
import com.laiyy.design.demo8.impl.CriteriaFemale;
import com.laiyy.design.demo8.impl.CriteriaMale;
import com.laiyy.design.demo8.impl.CriteriaSingle;
import com.laiyy.design.demo8.impl.OrCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/8/17 16:56
 * @description
 */
public class CriteriaPatterDemo {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Robert","Male", "Single"));
        personList.add(new Person("John","Male", "Married"));
        personList.add(new Person("Laura","Female", "Married"));
        personList.add(new Person("Diana","Female", "Single"));
        personList.add(new Person("Mike","Male", "Single"));
        personList.add(new Person("Bobby","Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println("女性: ");
        printPersons(male.meetCriteria(personList));

        System.out.println("男性: ");
        printPersons(female.meetCriteria(personList));

        System.out.println("单身、女性: ");
        printPersons(singleMale.meetCriteria(personList));

        System.out.println("单身或男性: ");
        printPersons(singleOrFemale.meetCriteria(personList));
    }

    private static void printPersons(List<Person> persons){
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    +", Gender : " + person.getGender()
                    +", Marital Status : " + person.getMaritalStatus()
                    +" ]");
        }
    }
}
