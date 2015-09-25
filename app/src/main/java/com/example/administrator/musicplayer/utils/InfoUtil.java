package com.example.administrator.musicplayer.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.application.MusicPlayerApplication;
import com.example.administrator.musicplayer.bean.PlaybackList;
import com.example.administrator.musicplayer.bean.Song;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/9/7.
 */
public class InfoUtil{
    static String[] contenturi = new String[]{
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DATA};

    /**
     * 获取系统所有歌曲信息
     * @param context
     * @return
     */
    private static ArrayList<Song> GetAllSongInfo(Context context){
        ArrayList<Song> songList = new ArrayList<Song>();
        //取得ContentResolver对象
        ContentResolver cr = context.getContentResolver();
        //查询所有音乐信息
        Cursor cur = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contenturi, null, null, null);
        //将数据存入ArrayList中
        if(cur != null){
            //移动游标到第一个
            cur.moveToFirst();
            for(int i = 0; i < cur.getCount(); i++){
                Song songInfo = new Song();
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

    /**
     * 获取默认列表歌曲清单
     * @param context
     * @return
     */
    public static ArrayList<Song> CreateSongList(Context context){
        return GetAllSongInfo(context);
    }

    /**
     * 获取列表歌曲清单（测试）
     * @return
     */
    public static ArrayList<Song> CreateSongList(){
        return new ArrayList<Song>();
    }

    /**
     * 向对应列表添加歌曲
     * @param song
     * @param playbacklistName
     * @param AllPlaybackList
     */
    public static void AddsongToSongList(Song song, String playbacklistName,ArrayList<PlaybackList> AllPlaybackList){
        for (PlaybackList temp : AllPlaybackList) {
            if (temp.getPlaybacklistname().equals(playbacklistName)){
                temp.getSonglist().add(song);
            }
        }
    }

    /**
     * 创建默认列表
     * @param context
     * @return
     */
    public static PlaybackList CreatePlaybackList(Context context){
        PlaybackList playbackList = new PlaybackList();
        playbackList.setPlaybacklistname("默认列表");
        playbackList.setSonglist(CreateSongList(context));
        return playbackList;
    }

    /**
     * 创建新列表
     * @return
     */
    public static PlaybackList CreatePlaybackList(String playbacklistname){
        PlaybackList playbackList = new PlaybackList();
        playbackList.setPlaybacklistname(playbacklistname);
        playbackList.setSonglist(CreateSongList());
        playbackList.setIconId(R.drawable.icon_navigation_local);
        return playbackList;
    }

    /**
     * 初始化数据
     * @param context
     * @return
     */
    public static ArrayList<PlaybackList> InitPlaybackListInfo(Context context){

        ArrayList<PlaybackList> DefaultPlaybackLists = new ArrayList<PlaybackList>();
        PlaybackList playbackList = CreatePlaybackList(context);
        playbackList.setIconId(R.drawable.icon_defaultlist);
        DefaultPlaybackLists.add(playbackList);
        playbackList = CreatePlaybackList("我喜欢的");
        playbackList.setIconId(R.drawable.icon_likelist);
        DefaultPlaybackLists.add(playbackList);



        return DefaultPlaybackLists;
    }

    /**
     * 新建列表
     * @param playbacklistname
     * @param AllPlaybackList
     */
    public static void NewPlaybacklistToAll(String playbacklistname, ArrayList<PlaybackList> AllPlaybackList){
        PlaybackList playbackList = CreatePlaybackList(playbacklistname);
        AllPlaybackList.add(playbackList);
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
