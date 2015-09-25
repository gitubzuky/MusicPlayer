package com.example.administrator.musicplayer.activitys;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.application.MusicPlayerApplication;
import com.example.administrator.musicplayer.bean.PlaybackList;
import com.example.administrator.musicplayer.bean.Song;
import com.example.administrator.musicplayer.broadcastreceivers.ActivityReceiver;
import com.example.administrator.musicplayer.fragment.PlayinglisttypeFragment;
import com.example.administrator.musicplayer.fragment.SonglistFragment;
import com.example.administrator.musicplayer.interfaces.PlaylistypeOnItemClickListener;
import com.example.administrator.musicplayer.interfaces.SonglistOnItemClickListener;
import com.example.administrator.musicplayer.utils.InfoUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends Activity implements PlaylistypeOnItemClickListener, SonglistOnItemClickListener {
    @Bind(R.id.fm_content)
    FrameLayout fm_Content;
    @Bind(R.id.iv_playing_album)
    ImageView iv_PlayingAlbum;
    @Bind(R.id.tv_playing_name)
    TextView tv_PlayingName;
    @Bind(R.id.ibtn_bottom_playorpause)
    ImageButton ibtn_Bottom_Playorpause;
    @Bind(R.id.bottomplaying)
    RelativeLayout bottomplaying;
    @Bind(R.id.nv_playlisttype_navigation)
    NavigationView nv_Playlisttype_Navigation;
    @Bind(R.id.dl_playlisttype)
    DrawerLayout dl_Playlisttype;

    public PlayinglisttypeFragment playlisttypefragment;
    public SonglistFragment songlistfragment;

    private ArrayList<PlaybackList> PlaybackListdata;
    Song currentsong;
    MusicPlayerApplication musicPlayerApplication;
    ActivityReceiver mainactivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        musicPlayerApplication = MusicPlayerApplication.getInstance();
        initData();
        initView();
        if (savedInstanceState == null) {
            setDefaultFragment();
        }

    }

    public void initData() {
        PlaybackListdata = InfoUtil.InitPlaybackListInfo(this);
        musicPlayerApplication.setCurSongList(PlaybackListdata.get(0).getSonglist());
        musicPlayerApplication.setCurposition(0);
        currentsong = musicPlayerApplication.getCurSong();


    }

    /**
     * 初始化界面
     */
    private void initView() {
        tv_PlayingName.setText(currentsong.getName() + "-" + currentsong.getArtist());
    }

    /**
     * 初始化Fragment
     */
    private void setDefaultFragment() {
        playlisttypefragment = new PlayinglisttypeFragment(PlaybackListdata);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.fm_content, playlisttypefragment, "Playlisttype");
        transaction.commit();
    }

//    /**
//     * 实现父类的抽象函数，MainActivity接收到广播的时候需要完成的任务
//     */
//    @Override
//    protected void doMainActivityAction() {
//        currentsong = musicPlayerApplication.getCurSong();
//        tv_PlayingName.setText(currentsong.getName() + "-" + currentsong.getArtist());
//    }
//
//    /**
//     * 实现父类的抽象函数，SongplayingActivity接收到广播的时候做的事情
//     */
//    @Override
//    protected void doSongplayingActivityAction() {
//
//    }

    /**
     * 底部布局点击事件（xml文件中定义了onClick属性）
     *
     * @param view
     */
    public void SongnameClick(View view) {
        Intent intent = new Intent(MainActivity.this, SongPlayingActivity.class);
        intent.putExtra("bottomclick", true);
        startActivityForResult(intent, 0);
    }

    /**
     * 底部暂停按钮点击事件（xml文件中定义了onClick属性）
     *
     * @param view
     */
    public void pause(View view) {
        if (musicPlayerApplication.getMediaPlayer() == null) {
            Log.i("service", "service is not running");
            Intent intent = new Intent();
            intent.setAction("com.example.administrator.service.AUDIOPLAY_SERVICE");
            startService(intent);
        } else if (musicPlayerApplication.getMediaPlayer().isPlaying()) {
            musicPlayerApplication.getMediaPlayer().pause();
            musicPlayerApplication.ChangetoPauseState();
        } else if (musicPlayerApplication.getState() == musicPlayerApplication.PAUSE) {
            musicPlayerApplication.getMediaPlayer().start();
            musicPlayerApplication.ChangetoPlayingState();
        }

    }

    @Override
    public void OnListtypeItemClick(int position) {
        Log.i("position", "" + position);
        Log.i("Playbacklistdatasize", "size = " + PlaybackListdata.size());

        if (position > 0) {
            //position-1是因为第一个item为新建列表按钮
            if (PlaybackListdata.get(position - 1).getSonglist().size() > 0) {
                musicPlayerApplication.setCurSongList(PlaybackListdata.get(position - 1).getSonglist());
                songlistfragment = new SonglistFragment(PlaybackListdata.get(position - 1).getSonglist());
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.hide(playlisttypefragment);
                ft.add(R.id.fm_content, songlistfragment, "Songlist");
                ft.addToBackStack(null);
                ft.commit();
                Toast.makeText(MainActivity.this, "click" + position, Toast.LENGTH_SHORT).show();
            } else {

            }
        }
    }

    @Override
    public void OnSonglistItemClick(View view, int position) {
        TextView tv_songname = (TextView) view.findViewById(R.id.tv_item_songlist_songname);
        TextView tv_singername = (TextView) view.findViewById(R.id.tv_item_songlist_singername);

        tv_PlayingName.setText(tv_songname.getText().toString() + "-" + tv_singername.getText().toString());

        musicPlayerApplication.setCurposition(position);

        if (musicPlayerApplication.getState() == musicPlayerApplication.PLAYING) {
            musicPlayerApplication.getMediaPlayer().stop();
        }
        Intent intent = new Intent(MainActivity.this, SongPlayingActivity.class);
        startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                String str = data.getStringExtra("PlayingTitle");//str即为回传的值
                tv_PlayingName.setText(str);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        intent.setAction("com.example.administrator.service.AUDIOPLAY_SERVICE");
        stopService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "MainActivity is Paused");
    }
}
