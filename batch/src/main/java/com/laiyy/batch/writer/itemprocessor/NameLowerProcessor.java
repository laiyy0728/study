//package com.laiyy.batch.writer.itemprocessor;
//
//import com.laiyy.batch.itemreadermultifile.City;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.stereotype.Component;
//
///**
// * @author laiyy
// * @date 2018/11/26 15:56
// * @description
// */
//@Component("nameLowerProcessor")
//public class NameLowerProcessor implements ItemProcessor<City, City> {
//    @Override
//    public City process(City city) throws Exception {
//        String name = city.getName().toUpperCase();
//        city.setName(name);
//        return city;
//    }
//}
