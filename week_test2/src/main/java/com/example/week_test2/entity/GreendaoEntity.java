package com.example.week_test2.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class GreendaoEntity {

    @Id(autoincrement = true)
    private Long id;

    private String json;

    private String key;//key区分哪个json的

    @Generated(hash = 1063073890)
    public GreendaoEntity(Long id, String json, String key) {
        this.id = id;
        this.json = json;
        this.key = key;
    }

    @Generated(hash = 393499003)
    public GreendaoEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}

