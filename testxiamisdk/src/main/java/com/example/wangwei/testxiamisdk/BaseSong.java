package com.example.wangwei.testxiamisdk;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hector on 16/7/14.
 */

public class BaseSong implements Serializable {

    private int song_id;
    private String song_name;
    private String singers;
    private String album_logo;
    private int length;
    private List<PurviewRole> purview_roles;

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getSingers() {
        return singers;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public String getAlbum_logo() {
        return album_logo;
    }

    public void setAlbum_logo(String album_logo) {
        this.album_logo = album_logo;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<PurviewRole> getPurview_roles() {
        return purview_roles;
    }

    public void setPurview_roles(List<PurviewRole> purview_roles) {
        this.purview_roles = purview_roles;
    }

    class PurviewRole implements Serializable {
        private String quality;
        private List<Operation> operation_list;

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public List<Operation> getOperation_list() {
            return operation_list;
        }

        public void setOperation_list(List<Operation> operation_list) {
            this.operation_list = operation_list;
        }

        class Operation implements Serializable {
            private int purpose;
            private int upgrade_role;

            public int getPurpose() {
                return purpose;
            }

            public void setPurpose(int purpose) {
                this.purpose = purpose;
            }

            public int getUpgrade_role() {
                return upgrade_role;
            }

            public void setUpgrade_role(int upgrade_role) {
                this.upgrade_role = upgrade_role;
            }
        }
    }
}
