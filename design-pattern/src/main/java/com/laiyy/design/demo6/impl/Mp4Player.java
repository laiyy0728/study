package com.laiyy.design.demo6.impl;

import com.laiyy.design.demo6.AdvancedMediaPlayer;

/**
 * @author laiyy
 * @date 2018/7/10 16:18
 * @description
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // do nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}
