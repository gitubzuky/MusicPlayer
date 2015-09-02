package com.example.administrator.musicplayer.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.bean.SongInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2015/9/2.
 */
public class SonglistAdapter extends BaseAdapter {
    Context context;
    ArrayList<SongInfo> songInfos;
    LayoutInflater layoutInflater;


    public SonglistAdapter(Context context, ArrayList<SongInfo> songInfos) {
        this.context = context;
        this.songInfos = songInfos;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return songInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_songlist, null);
            holder.tv_item_songlist_songname = (TextView)convertView.findViewById(R.id.tv_item_songlist_songname);
            holder.tv_item_songlist_singername = (TextView)convertView.findViewById(R.id.tv_item_songlist_singername);

            holder.tv_item_songlist_songname.setText(songInfos.get(position).getName());
            holder.tv_item_songlist_singername.setText(songInfos.get(position).getSingername());
        }
        return convertView;
    }

    public class ViewHolder{
        public TextView tv_item_songlist_songname;
        public TextView tv_item_songlist_singername;
    }
}
