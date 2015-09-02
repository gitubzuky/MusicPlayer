package com.example.administrator.musicplayer.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.adapter.SonglistAdapter;
import com.example.administrator.musicplayer.bean.SongInfo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/9/2.
 */
public class SonglistActivity extends Activity {
    @Bind(R.id.lv_songlist)
    ListView lv_songlist;

    private SonglistAdapter songlistAdapter;
    private ArrayList<SongInfo> songInfos;

    public SonglistActivity() {
        songInfos = new ArrayList<SongInfo>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_songlist);
        ButterKnife.bind(this);

        songInfos = initData();

        songlistAdapter = new SonglistAdapter(SonglistActivity.this, songInfos);
        lv_songlist.setAdapter(songlistAdapter);
    }

    private ArrayList<SongInfo> initData() {
        SongInfo songInfo1 = new SongInfo("海阔天空", "Beyond");
        songInfos.add(songInfo1);

        SongInfo songInfo2 = new SongInfo("董小姐", "宋冬野");
        songInfos.add(songInfo2);

        SongInfo songInfo3 = new SongInfo("大地","Beyond");
        songInfos.add(songInfo3);

        SongInfo songInfo4 = new SongInfo("龙卷风", "周杰伦");
        songInfos.add(songInfo4);

        return songInfos;
    }
}
