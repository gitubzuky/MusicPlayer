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
import com.example.administrator.musicplayer.adapter.PlaylisttypeAdapter;
import com.example.administrator.musicplayer.bean.PlaybackList;
import com.example.administrator.musicplayer.interfaces.PlaylistypeOnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/9/2.
 */
public class PlayinglisttypeFragment extends Fragment {
    View playlisttype;
    @Bind(R.id.lv_playlisttype)
    ListView lv_Playlisttype;


    Context context;
    private ArrayList<PlaybackList> PlaybackListData;

    public PlayinglisttypeFragment(ArrayList<PlaybackList> playbackListData) {
        PlaybackListData = playbackListData;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        playlisttype = inflater.inflate(R.layout.layout_playlisttype, container, false);
        ButterKnife.bind(this, playlisttype);
        PlaylisttypeAdapter playlisttypeAdapter = new PlaylisttypeAdapter(context,PlaybackListData);//第二个参数是列表类型信息
        lv_Playlisttype.setAdapter(playlisttypeAdapter);
        lv_Playlisttype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getActivity() instanceof PlaylistypeOnItemClickListener)
                {
                    ((PlaylistypeOnItemClickListener) getActivity()).OnListtypeItemClick(position);
                }
            }
        });

        return playlisttype;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        mCallbacks = null;
        ButterKnife.unbind(this);
    }
}
