package com.laiyy.design.demo6.player;

import com.laiyy.design.demo6.MediaPlayer;
import com.laiyy.design.demo6.adapter.MediaAdapter;

/**
 * @author laiyy
 * @date 2018/7/10 16:22
 * @description
 */
public class AudioPlayer implements MediaPlayer {

    public static final String MP3 = "mp3";



    @Override
    public void play(String audioType, String fileName) {
        MediaAdapter mediaAdapter;

        // 播放 mp3
        if (MP3.equals(audioType)) {
            System.out.println("playing mp3 file. Name: " + fileName);
        }

        // 其他格式
        else if (MediaAdapter.MP4.equals(audioType) || MediaAdapter.VLC.equals(audioType)) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("invalid media [" + audioType + "] format not support");
        }
    }
}
