package com.example.administrator.musicplayer.bean;

/**
 * Created by Administrator on 2015/9/2.
 */
public class SongInfo {
    private String name;
    private String singername;

    public SongInfo(String name, String singername) {
        this.name = name;
        this.singername = singername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }
}
