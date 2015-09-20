package com.example.administrator.musicplayer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.bean.PlaybackList;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2015/9/1.
 */
public class PlaylisttypeAdapter extends BaseAdapter {
    private LayoutInflater inflater_Playlisttype;
    private Context context;
    ArrayList<PlaybackList> PlaybackListData;


    /**
     * 播放列表类型适配器 @param context @param defaultsongnum  默认列表歌曲数量 @param likesongnum 喜欢列表歌曲数量
     */
    public PlaylisttypeAdapter(Context context, ArrayList<PlaybackList> PlaybackListData) {
        this.inflater_Playlisttype = LayoutInflater.from(context);
        this.context = context;
        this.PlaybackListData = PlaybackListData;
    }


    @Override
    public int getCount() {
        return PlaybackListData.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return PlaybackListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (position == 0) {
            convertView = inflater_Playlisttype.inflate(R.layout.item_playlisttype, null);
            holder = new ViewHolder(convertView);

            holder.iv_item_playlisttype_icon.setImageResource(R.drawable.icon_newlist);
            holder.tv_item_playlisttype_title.setText("新建列表");
            holder.iv_item_playlisttype_redirect.setVisibility(View.GONE);
            convertView.setTag(holder);
            return convertView;
        }
        if (convertView == null) {
            convertView = inflater_Playlisttype.inflate(R.layout.item_playlisttype, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        Log.i("position", "" + position);
        Log.i("listTypeList", "listtypelist(" + (position - 1) + "):" + PlaybackListData.get(position - 1).getPlaybacklistname());
        holder.iv_item_playlisttype_icon.setImageResource(PlaybackListData.get(position - 1).getIconId());
        holder.tv_item_playlisttype_title.setText(PlaybackListData.get(position - 1).getPlaybacklistname() + "(" + PlaybackListData.get(position - 1).getSonglist().size() + "首)");
        holder.iv_item_playlisttype_redirect.setVisibility(View.VISIBLE);
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_playlisttype.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static

//    public class ViewHolder {
//        public ImageView iv_item_playlisttype_icon;
//        public TextView tv_item_playlisttype_title;
//        public ImageView iv_item_playlisttype_redirect;
//    }

    class ViewHolder {
        @Bind(R.id.iv_item_playlisttype_icon)
        ImageView iv_item_playlisttype_icon;
        @Bind(R.id.tv_item_playlisttype_title)
        TextView tv_item_playlisttype_title;
        @Bind(R.id.iv_item_playlisttype_redirect)
        ImageView iv_item_playlisttype_redirect;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
