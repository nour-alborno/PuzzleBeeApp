package com.devtaghreed.puzzlebeeapp.Room_Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelData;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelStatistics;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.UserData;

import java.util.List;

public class ViewModel extends AndroidViewModel {


    private MyRepository myRepositiory;

    public ViewModel(@NonNull Application application) {
        super(application);

        myRepositiory = new MyRepository(application);
    }


    public void insertLevelData(LevelData levelData) {

        myRepositiory.insertLevelData(levelData);

    }


    public void insertRiddleData(RiddleData riddleData) {

        myRepositiory.insertRiddleData(riddleData);
    }

    public void insertUserData(UserData userData) {

        myRepositiory.insertUserData(userData);
    }

    public void insertStatistics(LevelStatistics levelStatistics) {

        myRepositiory.insertStatistics(levelStatistics);
    }

    public LiveData<List<LevelData>> getLevels() {

        return myRepositiory.getLevels();
    }

    public RiddleData getQuistion(int riddle_id, int level_id) {

        return myRepositiory.getQuistion(riddle_id, level_id);
    }

    public LiveData<List<RiddleData>> getAllQuestions(int levelId) {
        return myRepositiory.getAllQuestions(levelId);
    }

    public LiveData<UserData> getUserData() {
        return myRepositiory.getUserData();
    }

    public LiveData<List<LevelStatistics>> getLevelStatistics() {
        return myRepositiory.getLevelStatistics();
    }

    public void updateUserData(UserData userData) {

        myRepositiory.updateUserData(userData);

    }


    public void deleteAll(LevelStatistics levelStatistics) {

        myRepositiory.deleteAll(levelStatistics);

    }


  public   void DeleteAllStatistics(List<LevelStatistics> levelStatistics){

                myRepositiory.DeleteAllStatistics(levelStatistics);

    }
}