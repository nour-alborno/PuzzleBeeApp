package com.devtaghreed.puzzlebeeapp.Room_Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelData;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelStatistics;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.UserData;

import java.util.List;

public class MyRepository {


    private LevelDataDao levelDataDao;
    private LevelStatisticsDao levelStatisticsDao;
    private RiddleDataDao riddleDataDao;
    private UserDataDao userDataDao;


    MyRepository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);

        levelDataDao = db.levelDataDao();
        riddleDataDao = db.riddleDataDao();
        userDataDao = db.userDataDao();
        levelStatisticsDao = db.levelStatisticsDao();


    }


    void insertLevelData(LevelData levelData) {
        RoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDataDao.insertLevelData(levelData);
            }
        });
    }


    void insertRiddleData(RiddleData riddleData) {
        RoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                riddleDataDao.insertRiddleData(riddleData);
            }
        });
    }

    void insertUserData(UserData userData) {
        RoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDataDao.insertUserData(userData);
            }
        });
    }


    void insertStatistics(LevelStatistics levelStatistics) {
        RoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelStatisticsDao.insertStatistics(levelStatistics);
            }
        });
    }

    LiveData<List<LevelData>> getLevels() {

        return levelDataDao.getLevels();
    }

    RiddleData getQuistion(int riddle_id, int id_level) {

        return riddleDataDao.getQuistion(riddle_id, id_level);
    }


    LiveData<List<RiddleData>> getAllQuestions(int levelId) {
        return riddleDataDao.getAllQuestions(levelId);
    }

    LiveData<UserData> getUserData() {
        return userDataDao.getUserData();
    }

    LiveData<List<LevelStatistics>> getLevelStatistics() {
        return levelStatisticsDao.getLevelStatistics();
    }

    void updateUserData(UserData userData) {
        RoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDataDao.updateUserData(userData);
            }
        });
    }

    void deleteAll(LevelStatistics levelStatistics){
        RoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelStatisticsDao.deleteAll(levelStatistics);
            }
        });
    }

    void DeleteAllStatistics(List<LevelStatistics> levelStatistics){
        RoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelStatisticsDao.DeleteAllStatistics(levelStatistics);
            }
        });
    }


}
