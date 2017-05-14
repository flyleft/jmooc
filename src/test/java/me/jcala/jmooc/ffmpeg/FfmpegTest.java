package me.jcala.jmooc.ffmpeg;

import org.junit.Test;

import java.io.IOException;

public class FfmpegTest {

    @Test
    public void testFfmpeg(){
        String videoRealPath = "G:/home/jcala/jmooc/videos/1.mp4";
        String imageRealPath ="G:/home/jcala/jmooc/pics/1.jpg";
        try {
            Runtime.getRuntime().exec("cmd /c start G://home/jcala/jmooc/ffmpeg/ffmpeg.bat " + videoRealPath + " " + imageRealPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
