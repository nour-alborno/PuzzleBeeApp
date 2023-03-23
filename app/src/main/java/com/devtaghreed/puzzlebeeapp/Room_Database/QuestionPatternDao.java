package com.devtaghreed.puzzlebeeapp.Room_Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.devtaghreed.puzzlebeeapp.Room_Database.Models.QuestionPattern;

import java.util.List;

@Dao
public interface QuestionPatternDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQPattern(QuestionPattern questionPattern);

    @Query("select * from QuestionPattern")
    public LiveData<List<QuestionPattern>> getQuestionPattern();
}
