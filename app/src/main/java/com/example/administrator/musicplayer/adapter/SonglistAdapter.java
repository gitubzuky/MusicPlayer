package com.example.administrator.musicplayer.adapter;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.bean.SongInfo;
import com.example.administrator.musicplayer.utils.SongUtil;

import java.net.ContentHandler;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/2.
 */
public class SonglistAdapter extends BaseAdapter {
    Context context;

    //歌曲信息列表
    ArrayList<SongInfo> songList;
    LayoutInflater layoutInflater;



    public SonglistAdapter(Context context, ArrayList<SongInfo> songList) {
        this.context = context;
        this.songList = songList;
        layoutInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int position) {
        return songList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_songlist, null);
            holder.tv_item_songlist_songname = (TextView)convertView.findViewById(R.id.tv_item_songlist_songname);
            holder.tv_item_songlist_singername = (TextView)convertView.findViewById(R.id.tv_item_songlist_singername);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tv_item_songlist_songname.setText(songList.get(position).getName());
        holder.tv_item_songlist_singername.setText(songList.get(position).getArtist());
        return convertView;
    }

    public class ViewHolder{
        public TextView tv_item_songlist_songname;
        public TextView tv_item_songlist_singername;
    }
}
