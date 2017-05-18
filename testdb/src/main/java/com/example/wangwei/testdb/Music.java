package com.example.wangwei.testdb;

import org.xutils.common.util.LogUtil;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangwei on 17/3/8.
 */
@Table(name="music")
public class Music {
    @Column(name = "id", isId = true)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "singer")
    private String singer;
    @Column(name = "type")
    private String type;

    private List<String> types;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        String[] types = type.split(",");
        setTypes(Arrays.asList(types));
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        LogUtil.i("types");
        this.types = types;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
