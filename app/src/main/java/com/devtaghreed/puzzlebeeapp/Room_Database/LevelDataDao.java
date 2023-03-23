package com.devtaghreed.puzzlebeeapp.Room_Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelData;

import java.util.List;

@Dao
public interface LevelDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLevelData(LevelData levelData);


    @Query("select * From LevelData")
    LiveData<List<LevelData>> getLevels();

}
