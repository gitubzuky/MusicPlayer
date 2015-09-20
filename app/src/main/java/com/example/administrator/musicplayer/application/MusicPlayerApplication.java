package com.example.administrator.musicplayer.application;

import android.app.Application;
import android.media.MediaPlayer;

import com.example.administrator.musicplayer.bean.PlaybackList;
import com.example.administrator.musicplayer.bean.Song;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/9/17.
 */
public class MusicPlayerApplication extends Application {
    private static MusicPlayerApplication application;
    private MediaPlayer mediaPlayer;
    private int curposition;
    private ArrayList<Song> CurSongList;
    private static final int PLAYING = 0;
    private static final int PAUSE = 1;
    private static final int STOP = 2;
    private static final int IDLE = 3;
    int state;

    private ArrayList<PlaybackList> AllPlaybackList = null;

    @Override
    public void onCreate() {
        super.onCreate();
        state = IDLE;

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

    public static int getPLAYING() {
        return PLAYING;
    }

    public static int getPAUSE() {
        return PAUSE;
    }

    public static int getSTOP() {
        return STOP;
    }

    public static int getIDLE() {
        return IDLE;
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

    public ArrayList<Song> getCurSongList() {
        return CurSongList;
    }

    public void setCurSongList(ArrayList<Song> curSongList) {
        CurSongList = curSongList;
    }

    public Song getCurSong(){
        if(curposition >= 0 && curposition < getCurSongList().size()){
            return CurSongList.get(curposition);
        }
        else{
            return null;
        }
    }

    public ArrayList<PlaybackList> getAllPlaybackList() {
        return AllPlaybackList;
    }

    public void setAllPlaybackList(ArrayList<PlaybackList> allPlaybackList) {
        AllPlaybackList = allPlaybackList;
    }
}
