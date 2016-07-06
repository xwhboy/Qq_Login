package com.example.xwh12.qq_login.Entity;

import java.util.Map;

/**
 * Created by xwh12 on 2016/7/7.
 */
public class Lrc {
    private String title;
    private String artist;
    private String album;
    private String lrcMaker;
    //时间推迟或提前
    private String offset;
    //保存歌词信息和时间点
    private Map<Long,String> info;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public String getlrcMaker() {
        return lrcMaker;
    }
    public void setlrcMaker(String lrcMaker) {
        this.lrcMaker = lrcMaker;
    }
    public String getOffset() {
        return offset;
    }
    public void setOffset(String offset) {
        this.offset = offset;
    }
    public Map<Long, String> getInfo() {
        return info;
    }
    public void setInfo(Map<Long, String> info) {
        this.info = info;
    }
}
