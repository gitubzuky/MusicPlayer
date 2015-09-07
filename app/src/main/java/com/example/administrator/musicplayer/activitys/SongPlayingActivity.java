package com.example.administrator.musicplayer.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.adapter.SonglistAdapter;
import com.example.administrator.musicplayer.bean.SongInfo;

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
    private SonglistAdapter songlistAdapter;
    private ArrayList<SongInfo> songInfos;

    public SongPlayingActivity() {
        songInfos = new ArrayList<SongInfo>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String songname = "";
        setContentView(R.layout.layout_playing);
        ButterKnife.bind(this);
        songname = getIntent().getStringExtra("playingtitle");
        tv_playing_songname.setText(songname);
    }


}
