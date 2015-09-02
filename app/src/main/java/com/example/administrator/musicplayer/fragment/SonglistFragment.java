package com.example.administrator.musicplayer.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class SonglistFragment extends Fragment {
    View songlist;
    @Bind(R.id.lv_songlist)
    ListView lv_Songlist;

    private SonglistAdapter songlistAdapter;
    private ArrayList<SongInfo> songInfos;
    Context context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        songlist = inflater.inflate(R.layout.layout_songlist, container, false);
        ButterKnife.bind(this, songlist);
        initData();
        initView();
        return songlist;
    }

    private void initView() {
        songlistAdapter = new SonglistAdapter(context, songInfos);
        lv_Songlist.setAdapter(songlistAdapter);
        Log.i("SonglistFragment", "bindsuccess");

    }

    private ArrayList<SongInfo> initData() {
        songInfos = new ArrayList<SongInfo>();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
