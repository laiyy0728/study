package com.laiyy.batch.skiplistener;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

/**
 * @author laiyy
 * @date 2018/11/27 9:38
 * @description
 */
@Component
public class MySkipListener implements SkipListener<String, String> {

    /**
     * 读取跳过
     * @param throwable 发生的异常
     */
    @Override
    public void onSkipInRead(Throwable throwable) {

    }

    /**
     * 写入错误
     * @param s 写入的数据
     * @param throwable 发生的异常
     */
    @Override
    public void onSkipInWrite(String s, Throwable throwable) {

    }

    /**
     * 在处理流程中出现的异常
     * @param s 出现异常的数据
     * @param throwable 出现的异常
     */
    @Override
    public void onSkipInProcess(String s, Throwable throwable) {
        System.out.println(s + " ----> " + throwable.getLocalizedMessage());
    }
}
