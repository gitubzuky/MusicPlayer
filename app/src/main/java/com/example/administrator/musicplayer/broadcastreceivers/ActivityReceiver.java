package com.example.administrator.musicplayer.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2015/9/23.
 */

//Activity可以注册的BroadcastReceiver，负责监听Service传回来的广播
public class ActivityReceiver extends BroadcastReceiver {
    ActivityReceiveListener receiveListener;

    public ActivityReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        MusicPlayerApplication musicPlayerApplication = MusicPlayerApplication.getInstance();
//        Song CurSong = musicPlayerApplication.getCurSong();
//        Log.i("CurSong path", "CurSong path" + CurSong.getPath());
//        int state = intent.getIntExtra("state", -1);
//        Bundle bundle = intent.getExtras();
//        Song CurSong = bundle.getParcelable("CurSong");
//        receiveListener.onActivityReceive(CurSong);

        receiveListener.onActivityReceive(intent);


}

public interface ActivityReceiveListener {
    public void onActivityReceive(Intent intent);
}

    public void setReceiveListener(ActivityReceiveListener receiveListener){
        this.receiveListener = receiveListener;
    }
}
