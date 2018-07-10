package com.laiyy.design.demo6.adapter;

import com.laiyy.design.demo6.AdvancedMediaPlayer;
import com.laiyy.design.demo6.MediaPlayer;
import com.laiyy.design.demo6.impl.Mp4Player;
import com.laiyy.design.demo6.impl.VlcPlayer;

/**
 * @author laiyy
 * @date 2018/7/10 16:20
 * @description
 */
public class MediaAdapter implements MediaPlayer {

    public static final String VLC = "vlc";

    public static final String MP4 = "MP4";

    AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if (VLC.equals(audioType)) {
            advancedMediaPlayer = new VlcPlayer();
        } else if (MP4.equals(audioType)){
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (VLC.equals(audioType)) {
            advancedMediaPlayer.playVlc(fileName);
        } else if (MP4.equals(audioType)) {
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}
