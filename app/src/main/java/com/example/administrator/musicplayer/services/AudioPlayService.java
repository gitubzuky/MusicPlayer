package com.example.administrator.musicplayer.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.musicplayer.application.MusicPlayerApplication;

import java.io.IOException;

/**
 * Created by Administrator on 2015/9/16.
 */
public class AudioPlayService extends Service implements MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener {
    private static final String ACTION_PLAY = "com.example.action.PLAY";
    MediaPlayer AudioMediaPlayer = null;

    MusicPlayerApplication musicPlayerApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Service", "Service Create");
        musicPlayerApplication = MusicPlayerApplication.getInstance();
        musicPlayerApplication.ChangetoIdleState();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Service", "Service Start");
//        if(intent.getAction().equals(ACTION_PLAY)){
//        }
        play();
        return super.onStartCommand(intent, flags, startId);
//        return START_FLAG_RETRY;
    }

    public void play(){
        try {
            if (AudioMediaPlayer == null) {
                AudioMediaPlayer = new MediaPlayer();
                AudioMediaPlayer.setOnPreparedListener(this);
                AudioMediaPlayer.setOnCompletionListener(this);
            } else {
                AudioMediaPlayer.reset();
            }
            AudioMediaPlayer.setDataSource(musicPlayerApplication.getCurSong().getPath());
            AudioMediaPlayer.prepareAsync();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        musicPlayerApplication.setMediaPlayer(mp);
        musicPlayerApplication.ChangetoPlayingState();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Service", "Service Destroy");
        musicPlayerApplication.getMediaPlayer().stop();
        musicPlayerApplication.getMediaPlayer().release();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.stop();
        musicPlayerApplication.setCurposition(musicPlayerApplication.getCurposition() + 1);
        play();
    }
}
