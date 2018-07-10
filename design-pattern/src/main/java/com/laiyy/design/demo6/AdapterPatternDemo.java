package com.laiyy.design.demo6;

import com.laiyy.design.demo6.adapter.MediaAdapter;
import com.laiyy.design.demo6.player.AudioPlayer;

/**
 * @author laiyy
 * @date 2018/7/10 16:25
 * @description
 */
public class AdapterPatternDemo {

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play(MediaAdapter.MP4, "beyond");
        audioPlayer.play(MediaAdapter.VLC, "alone");
        audioPlayer.play(AudioPlayer.MP3, "far far away");
        audioPlayer.play("avi", "mind me");
    }

}
