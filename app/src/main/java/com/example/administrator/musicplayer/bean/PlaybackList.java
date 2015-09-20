package com.example.administrator.musicplayer.bean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/9/14.
 */
public class PlaybackList {
    private int iconId;
    private String playbacklistname;
    private ArrayList<Song> songlist;

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getPlaybacklistname() {
        return playbacklistname;
    }

    public void setPlaybacklistname(String playbacklistname) {
        this.playbacklistname = playbacklistname;
    }

    public ArrayList<Song> getSonglist() {
        return songlist;
    }

    public void setSonglist(ArrayList<Song> songlist) {
        this.songlist = songlist;
    }
}
