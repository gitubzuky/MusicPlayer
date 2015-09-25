package com.example.administrator.musicplayer.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.administrator.musicplayer.broadcastreceivers.ActivityReceiver;

/**
 * Created by Administrator on 2015/9/25.
 */
public abstract class ReceiverActivity extends Activity {
    ActivityReceiver activityReceiver;
    public static final String ACTION_MAINACTIVITY = "com.example.broadcastreceivers.MAINACTIVITY_RECEIVER";
    public static final String ACTION_SONGPLAYINGACTIVITY = "com.example.broadcastreceivers.SONGPLAYINGACTIVITY_RECEIVER";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_MAINACTIVITY);
        intentFilter.addAction(ACTION_SONGPLAYINGACTIVITY);
        activityReceiver = new ActivityReceiver();
        registerReceiver(activityReceiver, intentFilter);
        //回调Receiver中的ReceiverListener接口
        activityReceiver.setReceiveListener(new ActivityReceiver.ActivityReceiveListener() {
            @Override
            public void onActivityReceive(Intent intent) {
                if (intent.getAction().toString().equals(ACTION_MAINACTIVITY)) {
                    doMainActivityAction();
                } else if (intent.getAction().toString().equals(ACTION_SONGPLAYINGACTIVITY)) {
                    doSongplayingActivityAction();
                }
            }
        });
    }

    //MainActivity接收到广播的时候执行
    protected abstract void doMainActivityAction();

    //SongplayingActivity接收到广播的时候执行
    protected abstract void doSongplayingActivityAction();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
