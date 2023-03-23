package com.devtaghreed.puzzlebeeapp.Room_Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.devtaghreed.puzzlebeeapp.Room_Database.Models.UserData;

@Dao
public interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUserData(UserData userData);

    @Query("select * from UserData")
    LiveData<UserData> getUserData();

    @Update
    void updateUserData(UserData userData);
}
