package com.example.administrator.musicplayer.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.administrator.musicplayer.application.MusicPlayerApplication;
import com.example.administrator.musicplayer.bean.Song;

/**
 * Created by Administrator on 2015/9/23.
 */

//Activity可以注册的BroadcastReceiver，负责监听Service传回来的广播
public class ServiceReceiver extends BroadcastReceiver {
    MusicPlayerApplication musicPlayerApplication;
    ServiceReceiveListener receiveListener;

    public ServiceReceiver() {
        this.musicPlayerApplication = MusicPlayerApplication.getInstance();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Song CurSong = musicPlayerApplication.getCurSong();
        int state = intent.getIntExtra("state", -1);
        receiveListener.onServiceReceive(CurSong, state);
    }

    public interface ServiceReceiveListener {
        public void onServiceReceive(Song CurSong, int state);
    }

    public void setReceiveListener(ServiceReceiveListener receiveListener){
        this.receiveListener = receiveListener;
    }
}
