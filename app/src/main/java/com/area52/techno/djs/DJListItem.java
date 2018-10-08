package com.area52.techno.djs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DJListItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name="title")
    private String title;

    public DJListItem(){

    }

    public DJListItem(String time, String title) {
        this.time = time;
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
