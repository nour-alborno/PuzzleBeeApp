package com.devtaghreed.puzzlebeeapp.Room_Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RiddleDataDao {

    @Insert
    void insertRiddleData(RiddleData riddleData);



    @Query("select * from RiddleData where levelId = :levelId order by idRiddle ASC")
    LiveData<List<RiddleData>> getAllQuestions (int levelId);

    @Query("select * From RiddleData where idRiddle = :idRiddle & levelId = :levelId ")
    RiddleData getQuistion( int levelId,int idRiddle);

}
