package com.laiyy.design.demo6.impl;

import com.laiyy.design.demo6.AdvancedMediaPlayer;

/**
 * @author laiyy
 * @date 2018/7/10 16:17
 * @description
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // do nothing
    }
}
