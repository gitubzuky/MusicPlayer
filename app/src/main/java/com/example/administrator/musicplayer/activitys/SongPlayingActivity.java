package com.example.administrator.musicplayer.activitys;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.application.MusicPlayerApplication;
import com.example.administrator.musicplayer.bean.Song;
import com.example.administrator.musicplayer.utils.OtherUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/9/2.
 */
public class SongPlayingActivity extends ReceiverActivity {

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
    ImageButton ibtn_playing_model;
    @Bind(R.id.ibtn_playing_like)
    ImageButton ibtn_playing_like;
    @Bind(R.id.ibtn_playing_previous)
    ImageButton ibtn_playing_previous;
    @Bind(R.id.ibtn_playing_next)
    ImageButton ibtn_playing_next;

    Intent startserviceintent;
    Song CurSong;
    Intent resultintent;

    MusicPlayerApplication musicPlayerApplication;

    public SongPlayingActivity() {
        this.musicPlayerApplication = MusicPlayerApplication.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_playing);
        ButterKnife.bind(this);
        if (musicPlayerApplication.getMediaPlayer() == null) {
            startserviceintent = new Intent();
            startserviceintent.setAction("com.example.administrator.service.AUDIOPLAY_SERVICE");
            startService(startserviceintent);
        }
        if (!getIntent().getBooleanExtra("bottomclick", false)) {
            startserviceintent = new Intent();
            startserviceintent.setAction("com.example.administrator.service.AUDIOPLAY_SERVICE");
            startService(startserviceintent);
        }

        initData();
        initView();
    }

    private void initData() {
        CurSong = musicPlayerApplication.getCurSong();

    }

    private void initView() {

        Log.i("MainActivity", "fromMainActivity" + CurSong.getPath());
        tv_playing_songname.setText(CurSong.getName() + "-" + CurSong.getArtist());

        ibtn_playing_playorpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });

        ibtn_playing_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicPlayerApplication.getModel() == musicPlayerApplication.ORDER){
                    musicPlayerApplication.getMediaPlayer().stop();
                    musicPlayerApplication.setCurposition(musicPlayerApplication.getCurposition() - 1);
                    CurSong = musicPlayerApplication.getCurSong();
                    String playingtitle = CurSong.getName() + "-" + CurSong.getArtist();
                    tv_playing_songname.setText(playingtitle);

                    play();
                }
                else if(musicPlayerApplication.getModel() == musicPlayerApplication.SHUFFLE){
                    musicPlayerApplication.getMediaPlayer().stop();
                    if(musicPlayerApplication.getRandomtag()< 0 || musicPlayerApplication.getRandomtag() >= musicPlayerApplication.getCurSongList().size()){
                        musicPlayerApplication.setRandomtag(0);
                    }
                    else{
                        musicPlayerApplication.setRandomtag(musicPlayerApplication.getRandomtag() - 1);
                        musicPlayerApplication.setCurposition(musicPlayerApplication.getRandom()[musicPlayerApplication.getRandomtag()]);
                    }
                    CurSong = musicPlayerApplication.getCurSong();
                    String playingtitle = CurSong.getName() + "-" + CurSong.getArtist();
                    tv_playing_songname.setText(playingtitle);

                    play();
                }
            }
        });

        ibtn_playing_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayerApplication.getModel() == musicPlayerApplication.ORDER) {
                    musicPlayerApplication.getMediaPlayer().stop();
                    musicPlayerApplication.setCurposition(musicPlayerApplication.getCurposition() + 1);
                    CurSong = musicPlayerApplication.getCurSong();
                    String playingtitle = CurSong.getName() + "-" + CurSong.getArtist();
                    tv_playing_songname.setText(playingtitle);

                    play();
                } else if (musicPlayerApplication.getModel() == musicPlayerApplication.SHUFFLE) {
                    musicPlayerApplication.getMediaPlayer().stop();
                    if (musicPlayerApplication.getRandomtag() < 0 || musicPlayerApplication.getRandomtag() >= musicPlayerApplication.getCurSongList().size()) {
                        musicPlayerApplication.setRandomtag(0);
                    } else {
                        musicPlayerApplication.setRandomtag(musicPlayerApplication.getRandomtag() + 1);
                        musicPlayerApplication.setCurposition(musicPlayerApplication.getRandom()[musicPlayerApplication.getRandomtag()]);
                    }
                    CurSong = musicPlayerApplication.getCurSong();
                    String playingtitle = CurSong.getName() + "-" + CurSong.getArtist();
                    tv_playing_songname.setText(playingtitle);

                    play();
                }
            }
        });

        ibtn_playing_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayerApplication.getModel() == musicPlayerApplication.ORDER) {
                    musicPlayerApplication.setModel(musicPlayerApplication.SHUFFLE);
                    musicPlayerApplication.setRandom(OtherUtil.randomArray(
                                    0,
                                    musicPlayerApplication.getCurSongList().size() - 1,
                                    musicPlayerApplication.getCurSongList().size())
                    );
                    Toast.makeText(SongPlayingActivity.this, "已切换至随机播放", Toast.LENGTH_SHORT).show();
                }
                else if (musicPlayerApplication.getModel() == musicPlayerApplication.SHUFFLE) {
                    musicPlayerApplication.setModel(musicPlayerApplication.ORDER);
                    Toast.makeText(SongPlayingActivity.this, "已切换至顺序播放", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 实现父类的抽象函数，MainActivity接收到广播的时候需要完成的任务
     */
    @Override
    protected void doMainActivityAction() {

    }

    /**
     * 实现父类的抽象函数，SongplayingActivity接收到广播的时候需要完成的任务
     */
    @Override
    protected void doSongplayingActivityAction() {
        CurSong = musicPlayerApplication.getCurSong();
        tv_playing_songname.setText(CurSong.getName() + "-" + CurSong.getArtist());
    }

    private void pause() {
        if (musicPlayerApplication.getMediaPlayer() == null) {
            Log.i("MediaPlayer", "MediaPlayer");
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

    //播放
    private void play() {
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
                        if(musicPlayerApplication.getModel() == musicPlayerApplication.ORDER){
                            musicPlayerApplication.setCurposition(musicPlayerApplication.getCurposition() + 1);
                        }
                        else if (musicPlayerApplication.getModel() == musicPlayerApplication.SHUFFLE){
                            musicPlayerApplication.setRandomtag(musicPlayerApplication.getRandomtag() + 1);
                            musicPlayerApplication.setCurposition(musicPlayerApplication.getRandom()[musicPlayerApplication.getRandomtag()]);
                        }
                        play();
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

//    //下一首功能
//    private void next() {
//        try {
//            if (musicPlayerApplication.getMediaPlayer() == null) {
//                musicPlayerApplication.setMediaPlayer(new MediaPlayer());
//                musicPlayerApplication.getMediaPlayer().setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer mp) {
//                        mp.start();
//                        musicPlayerApplication.setMediaPlayer(mp);
//                        musicPlayerApplication.ChangetoPlayingState();
//                    }
//                });
//                musicPlayerApplication.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        mp.stop();
//                        musicPlayerApplication.setCurposition(musicPlayerApplication.getCurposition() + 1);
//                        next();
//                    }
//                });
//            } else {
//                musicPlayerApplication.getMediaPlayer().reset();
//            }
//            musicPlayerApplication.getMediaPlayer().setDataSource(musicPlayerApplication.getCurSong().getPath());
//            musicPlayerApplication.getMediaPlayer().prepareAsync();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "SongplayingActivity is destory");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            resultintent = new Intent();
            resultintent.putExtra("PlayingTitle", tv_playing_songname.getText().toString());
            setResult(RESULT_OK, resultintent);
        }
        return super.onKeyDown(keyCode, event);
    }


}
