//package com.laiyy.batch.skip;
//
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.stereotype.Component;
//
///**
// * @author laiyy
// * @date 2018/11/26 16:40
// * @description
// */
//@Component
//public class SkipProcessor implements ItemProcessor<String, String> {
//
//    private int attemptCount = 0;
//
//    @Override
//    public String process(String item) throws Exception {
//        System.out.println("processing item :" + item);
//        if ("26".equalsIgnoreCase(item)) {
//            attemptCount++;
//            if (attemptCount >= 3) {
//                System.out.println("Retried " + attemptCount + "times success");
//                return String.valueOf(Integer.valueOf(item) * -1);
//            }
//            System.out.println("Processed the " + attemptCount + " times fail");
//            throw new RuntimeException("Process failed. Attempt: " + attemptCount);
//        }
//        return String.valueOf(Integer.valueOf(item) * -1);
//    }
//}
