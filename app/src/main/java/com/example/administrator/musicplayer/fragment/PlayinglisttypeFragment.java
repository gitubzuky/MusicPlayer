package com.example.administrator.musicplayer.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.activitys.MainActivity;
import com.example.administrator.musicplayer.adapter.PlaylisttypeAdapter;

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
//    MainActivity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
//        this.activity = (MainActivity)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        playlisttype = inflater.inflate(R.layout.layout_playlisttype, container, false);
        ButterKnife.bind(this, playlisttype);
        PlaylisttypeAdapter playlisttypeAdapter = new PlaylisttypeAdapter(context, 147, 20);
        lv_Playlisttype.setAdapter(playlisttypeAdapter);
        lv_Playlisttype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < 2) {
                    MainActivity activity = (MainActivity) getActivity();
//                    activity.replaceFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    if (activity.songlistfragment == null) {
                        activity.songlistfragment = new SonglistFragment();
                    }
                    ft.replace(R.id.fm_content, activity.songlistfragment);
                    ft.commit();
                    Toast.makeText(context, "click"+position, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return playlisttype;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
