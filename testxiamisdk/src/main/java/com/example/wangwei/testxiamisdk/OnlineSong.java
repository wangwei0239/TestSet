package com.example.wangwei.testxiamisdk;

/**
 * Created by Hector on 16/7/15.
 */

public class OnlineSong extends BaseSong {

    public OnlineSong() {
    }

    public OnlineSong(BaseSong baseSong, String listen_file) {
        this.setSong_id(baseSong.getSong_id());
        this.setSong_name(baseSong.getSong_name());
        this.setSingers(baseSong.getSingers());
        this.setAlbum_logo(baseSong.getAlbum_logo());
        this.setLength(baseSong.getLength());
        this.setPurview_roles(baseSong.getPurview_roles());
        this.listen_file = listen_file;
    }

    private String listen_file;

    public String getListen_file() {
        return listen_file;
    }

    public void setListen_file(String listen_file) {
        this.listen_file = listen_file;
    }
}
