package com.example.administrator.musicplayer.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.adapter.SonglistAdapter;
import com.example.administrator.musicplayer.bean.SongInfo;
import com.example.administrator.musicplayer.interfaces.SonglistOnItemClickListener;
import com.example.administrator.musicplayer.utils.SongUtil;

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
    private ArrayList<SongInfo> songList;
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
        songlistAdapter = new SonglistAdapter(context, songList);
        lv_Songlist.setAdapter(songlistAdapter);
        lv_Songlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(getActivity() instanceof SonglistOnItemClickListener){
                    ((SonglistOnItemClickListener)getActivity()).OnSonglistItemClick(view, position);
                }
            }
        });
    }

    private ArrayList<SongInfo> initData() {
        songList = new ArrayList<SongInfo>();
        songList = SongUtil.GetAllSongInfo(context);
        return songList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
