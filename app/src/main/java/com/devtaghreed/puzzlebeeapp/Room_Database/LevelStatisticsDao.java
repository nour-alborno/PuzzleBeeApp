package com.devtaghreed.puzzlebeeapp.Room_Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelStatistics;

import java.util.List;

@Dao
public interface LevelStatisticsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertStatistics(LevelStatistics levelStatistics);


    @Query("select * from LevelStatistics")
    LiveData<List<LevelStatistics>> getLevelStatistics();

    @Delete
    void deleteAll(LevelStatistics levelStatistics);

    @Delete
    void DeleteAllStatistics(List<LevelStatistics> levelStatistics);


}
