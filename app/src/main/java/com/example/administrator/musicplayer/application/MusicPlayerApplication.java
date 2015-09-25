package com.example.administrator.musicplayer.application;

import android.app.Application;
import android.media.MediaPlayer;

import com.example.administrator.musicplayer.bean.PlaybackList;
import com.example.administrator.musicplayer.bean.Song;
import com.example.administrator.musicplayer.utils.InfoUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/9/17.
 */
public class MusicPlayerApplication extends Application {
    public static final int PLAYING = 0;
    public static final int PAUSE = 1;
    public static final int STOP = 2;
    public static final int IDLE = 3;
    public static final int ORDER = 4;
    public static final int SHUFFLE = 5;

    private static MusicPlayerApplication application;
    private MediaPlayer mediaPlayer;
    private int curposition;
    private ArrayList<Song> CurSongList;
    public int model;
    int state;
    int[] random;
    int randomtag;

    private ArrayList<PlaybackList> AllPlaybackList = null;

    @Override
    public void onCreate() {
        super.onCreate();
        state = IDLE;
        model = ORDER;
        randomtag = 0;

        if(null == application){
            application = this;
        }
    }

    public static MusicPlayerApplication getInstance(){
        return application;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void ChangetoPlayingState(){
        this.state = PLAYING;
    }

    public void ChangetoStopState(){
        this.state = STOP;
    }

    public void ChangetoPauseState(){
        this.state = PAUSE;
    }

    public void ChangetoIdleState(){
        this.state = IDLE;
    }

    public int getState() {
        return state;
    }

    public int getCurposition() {
        return curposition;
    }

    public void setCurposition(int curposition) {
        this.curposition = curposition;
    }

    public int[] getRandom() {
        return random;
    }

    public void setRandom(int[] random) {
        this.random = random;
    }

    public ArrayList<Song> getCurSongList() {
        return CurSongList;
    }

    public void setCurSongList(ArrayList<Song> curSongList) {
        CurSongList = curSongList;
    }

    public Song getCurSong(){
        if(curposition < 0 || curposition >= CurSongList.size()){
            curposition = 0;
            return CurSongList.get(0);
        }
        else{
            return CurSongList.get(curposition);
        }
    }

    public ArrayList<PlaybackList> getAllPlaybackList() {
        return AllPlaybackList;
    }

    public void setAllPlaybackList(ArrayList<PlaybackList> allPlaybackList) {
        AllPlaybackList = allPlaybackList;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getRandomtag() {
        return randomtag;
    }

    public void setRandomtag(int randomtag) {
        this.randomtag = randomtag;
    }
}
