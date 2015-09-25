package com.example.administrator.musicplayer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/9/2.
 */
public class Song implements Parcelable{
    private int id;
    private String name;
    private String artist;
    private String album;
    private int duration;
    private int currentPosition;
    private int size;
    private String path;
    private int index;

    public Song() {
    }

    protected Song(Parcel in) {
        id = in.readInt();
        name = in.readString();
        artist = in.readString();
        album = in.readString();
        duration = in.readInt();
        currentPosition = in.readInt();
        size = in.readInt();
        path = in.readString();
        index = in.readInt();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(artist);
        dest.writeString(album);
        dest.writeInt(duration);
        dest.writeInt(currentPosition);
        dest.writeInt(size);
        dest.writeString(path);
        dest.writeInt(index);
    }


}
