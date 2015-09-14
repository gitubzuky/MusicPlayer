package com.example.administrator.musicplayer.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.administrator.musicplayer.bean.SongInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/7.
 */
public class SongUtil {
    static String[] contenturi = new String[]{
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DATA};

    public static ArrayList<SongInfo> GetAllSongInfo(Context context){
        ArrayList<SongInfo> songList = new ArrayList<SongInfo>();
        //取得ContentResolver对象
        ContentResolver cr = context.getContentResolver();
        //查询所有音乐信息
        Cursor cur = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contenturi, null, null, null);
        //将数据存入ArrayList中
        if(cur != null){
            //移动游标到第一个
            cur.moveToFirst();
            int j = 1;
            for(int i = 0; i < cur.getCount(); i++){
                SongInfo songInfo = new SongInfo();
                songInfo.setId(j++);
                songInfo.setName(cur.getString(0));
                songInfo.setAlbum(cur.getString(1));
                songInfo.setArtist(cur.getString(2));
                songInfo.setDuration(cur.getInt(3));
                songInfo.setSize(cur.getInt(4));
                songInfo.setId(cur.getInt(5));
                songInfo.setPath(cur.getString(6));
                songList.add(songInfo);
                cur.moveToNext();
            }
        }
        return songList;
    }

    public static String timeToString(long duration) {
        if (duration < 0)
            return "00:00";
        StringBuffer sb = new StringBuffer();
        long m = duration / (60 * 1000);
        sb.append(m < 10 ? "0" + m : m);
        sb.append(":");
        long s = (duration % (60 * 1000)) / 1000;
        sb.append(s < 10 ? "0" + s : s);
        return sb.toString();
    }
}
