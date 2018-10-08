package com.area52.techno.djs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DatabaseInterface {

    @Query("SELECT * FROM DJListItem")
    List<DJListItem> getAllItems();

    @Insert
    void insertAll(DJListItem... DJListItems);
}
