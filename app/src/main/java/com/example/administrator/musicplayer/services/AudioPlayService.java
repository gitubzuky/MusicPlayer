package com.example.administrator.musicplayer.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.musicplayer.application.MusicPlayerApplication;
import com.example.administrator.musicplayer.bean.Song;

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
        play();
        return super.onStartCommand(intent, flags, startId);
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
        if (musicPlayerApplication.getModel() == musicPlayerApplication.ORDER) {
            musicPlayerApplication.setCurposition(musicPlayerApplication.getCurposition() + 1);
        }
        else if (musicPlayerApplication.getModel() == musicPlayerApplication.SHUFFLE) {
            if (musicPlayerApplication.getRandomtag() < 0 || musicPlayerApplication.getRandomtag() >= musicPlayerApplication.getCurSongList().size()) {
                musicPlayerApplication.setRandomtag(0);
            } else {
                musicPlayerApplication.setRandomtag(musicPlayerApplication.getRandomtag() + 1);
                musicPlayerApplication.setCurposition(musicPlayerApplication.getRandom()[musicPlayerApplication.getRandomtag()]);
            }
        }
//        musicPlayerApplication.setCurposition(musicPlayerApplication.getCurposition() + 1);
        play();
//        Song cursong = musicPlayerApplication.getCurSong();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("CurSong", cursong);
        Intent intent_songplaying = new Intent();
        intent_songplaying.setAction("com.example.broadcastreceivers.SONGPLAYINGACTIVITY_RECEIVER");
        sendBroadcast(intent_songplaying);

//        Intent intent_main = new Intent();
//        intent_main.setAction("com.example.broadcastreceivers.MAINACTIVITY_RECEIVER");
////        intent.putExtras(bundle);
//        sendBroadcast(intent_main);
    }
}
