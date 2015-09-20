package com.example.administrator.musicplayer.activitys;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.adapter.SonglistAdapter;
import com.example.administrator.musicplayer.application.MusicPlayerApplication;
import com.example.administrator.musicplayer.bean.Song;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/9/2.
 */
public class SongPlayingActivity extends Activity {

    @Bind(R.id.tv_playing_title)
    TextView tv_playing_title;
    @Bind(R.id.tv_playing_songname)
    TextView tv_playing_songname;
    @Bind(R.id.iv_playing_albumimg)
    ImageView iv_playing_albumimg;
    @Bind(R.id.ibtn_playing_playorpause)
    ImageButton ibtn_playing_playorpause;
    @Bind(R.id.ibtn_playing_deletefromlist)
    ImageButton ibtn_playing_peletefromlist;
    @Bind(R.id.ibtn_playing_showlyric)
    ImageButton ibtn_playing_showlyric;
    @Bind(R.id.ibtn_playing_showlist)
    ImageButton ibtn_playing_showlist;
    @Bind(R.id.ibtn_playing_like)
    ImageButton ibtn_playing_like;
    @Bind(R.id.ibtn_playing_previous)
    ImageButton ibtn_playing_previous;
    @Bind(R.id.ibtn_playing_next)
    ImageButton ibtn_playing_next;
    Intent intent;

    MusicPlayerApplication musicPlayerApplication;

    public SongPlayingActivity() {
        this.musicPlayerApplication = MusicPlayerApplication.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_playing);
        ButterKnife.bind(this);
        intent = new Intent();
        intent.setAction("com.example.administrator.service.AUDIOPLAY_SERVICE");
        startService(intent);
        initView();
    }

    private void initView(){
        Song CurSong = musicPlayerApplication.getCurSong();
        String title = "";
        String path = "";
        title = CurSong.getName()+"-"+CurSong.getArtist();
        path = CurSong.getPath();
        Log.i("MainActivity", "fromMainActivity" + path);
        tv_playing_songname.setText(title);

        ibtn_playing_playorpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });

        ibtn_playing_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                musicPlayerApplication.getMediaPlayer().stop();
                musicPlayerApplication.setCurposition(musicPlayerApplication.getCurposition() - 1);
                Song CurSong = musicPlayerApplication.getCurSong();

                previous();

                String playingtitle = CurSong.getName() + "-" + CurSong.getArtist();
                tv_playing_songname.setText(playingtitle);
                getIntent().putExtra("Playingtitle", playingtitle);
            }
        });

        ibtn_playing_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicPlayerApplication.getMediaPlayer().stop();
                musicPlayerApplication.setCurposition(musicPlayerApplication.getCurposition() + 1);
                Song CurSong = musicPlayerApplication.getCurSong();
                String playingtitle = CurSong.getName() + "-" + CurSong.getArtist();
                tv_playing_songname.setText(playingtitle);

                next();

                Intent intent = new Intent();
                intent.putExtra("PlayingTitle", playingtitle);
                setResult(RESULT_OK, intent);
            }
        });
    }

    private void pause(){
        if (musicPlayerApplication.getMediaPlayer().isPlaying()) {
            musicPlayerApplication.getMediaPlayer().pause();
            musicPlayerApplication.ChangetoPauseState();
        } else if (musicPlayerApplication.getState() == musicPlayerApplication.getPAUSE()) {
            musicPlayerApplication.getMediaPlayer().start();
            musicPlayerApplication.ChangetoPlayingState();
        }
    }

    private void previous(){
        try {
            if (musicPlayerApplication.getMediaPlayer() == null) {
                musicPlayerApplication.setMediaPlayer(new MediaPlayer());
                musicPlayerApplication.getMediaPlayer().setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        musicPlayerApplication.setMediaPlayer(mp);
                        musicPlayerApplication.ChangetoPlayingState();
                    }
                });
                musicPlayerApplication.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        musicPlayerApplication.setCurposition(musicPlayerApplication.getCurposition() - 1);
                        previous();
                    }
                });
            } else {
                musicPlayerApplication.getMediaPlayer().reset();
            }
            musicPlayerApplication.getMediaPlayer().setDataSource(musicPlayerApplication.getCurSong().getPath());
            musicPlayerApplication.getMediaPlayer().prepareAsync();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void next(){
        try {
            if (musicPlayerApplication.getMediaPlayer() == null) {
                musicPlayerApplication.setMediaPlayer(new MediaPlayer());
                musicPlayerApplication.getMediaPlayer().setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        musicPlayerApplication.setMediaPlayer(mp);
                        musicPlayerApplication.ChangetoPlayingState();
                    }
                });
                musicPlayerApplication.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        musicPlayerApplication.setCurposition(musicPlayerApplication.getCurposition() + 1);
                        next();
                    }
                });
            } else {
                musicPlayerApplication.getMediaPlayer().reset();
            }
            musicPlayerApplication.getMediaPlayer().setDataSource(musicPlayerApplication.getCurSong().getPath());
            musicPlayerApplication.getMediaPlayer().prepareAsync();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
