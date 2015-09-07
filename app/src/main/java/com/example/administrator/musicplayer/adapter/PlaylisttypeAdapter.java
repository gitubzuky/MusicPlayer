package com.example.administrator.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.musicplayer.R;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2015/9/1.
 */
public class PlaylisttypeAdapter extends BaseAdapter {
    private LayoutInflater inflater_Playlisttype;
    private Context context;
    private int defaultsongnum;
    private int likesongnum;


    /**
     * 播放列表类型适配器 @param context @param defaultsongnum  默认列表歌曲数量 @param likesongnum 喜欢列表歌曲数量
     */
    public PlaylisttypeAdapter(Context context, int defaultsongnum, int likesongnum) {
        this.inflater_Playlisttype = LayoutInflater.from(context);
        this.context = context;
        this.likesongnum = likesongnum;
        this.defaultsongnum = defaultsongnum;
    }

    @Override
    public int getCount() {
        return 3;
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
        if (convertView == null && position == 0) {
            convertView = inflater_Playlisttype.inflate(R.layout.item_playlisttype, null);
            holder = new ViewHolder(convertView);
            holder.tv_item_playlisttype_title = (TextView) convertView.findViewById(R.id.tv_item_playlisttype_title);

            holder.tv_item_playlisttype_title.setText("默认列表(" + defaultsongnum + "首)");
        }
        if (convertView == null && position == 1) {
            convertView = inflater_Playlisttype.inflate(R.layout.item_playlisttype, null);
            holder = new ViewHolder(convertView);

            holder.iv_item_playlisttype_icon.setImageResource(R.drawable.likelist_icon);
            holder.tv_item_playlisttype_title.setText("我喜欢的(" + likesongnum + "首)");
        }
        if (convertView == null && position == 2) {
            convertView = inflater_Playlisttype.inflate(R.layout.item_playlisttype, null);
            holder = new ViewHolder(convertView);

            holder.iv_item_playlisttype_icon.setImageResource(R.drawable.newlist_icon);
            holder.tv_item_playlisttype_title.setText("新建列表");
            holder.iv_item_playlisttype_redirect.setVisibility(View.GONE);
        }
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
