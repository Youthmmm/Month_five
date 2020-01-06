package com.example.month_view;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class GreenDao {
    @Id
    private Long id;
    private String name;
    @Generated(hash = 512904664)
    public GreenDao(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 766040118)
    public GreenDao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
