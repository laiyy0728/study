//package com.laiyy.batch.writer.itemprocessor;
//
//import com.laiyy.batch.itemreadermultifile.City;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.stereotype.Component;
//
///**
// * @author laiyy
// * @date 2018/11/26 15:59
// * @description
// */
//@Component("idFiltterProcessor")
//public class IdFilterProcessor implements ItemProcessor<City, City> {
//    @Override
//    public City process(City city) throws Exception {
//        if (city.getId() %2 == 0){
//            return city;
//        }
//        // 如果返回 null，相当于把对象过滤掉
//        return null;
//    }
//}
