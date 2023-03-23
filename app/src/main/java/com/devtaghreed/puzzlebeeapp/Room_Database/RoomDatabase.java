package com.devtaghreed.puzzlebeeapp.Room_Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelData;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelStatistics;
import com.devtaghreed.puzzlebeeapp.Room_Database.Models.UserData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RiddleData.class, UserData.class, LevelData.class, LevelStatistics.class}, version = 1, exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {


    public abstract LevelDataDao levelDataDao();
    public abstract UserDataDao userDataDao();
    public abstract RiddleDataDao riddleDataDao();
    public abstract LevelStatisticsDao levelStatisticsDao();


    private static volatile RoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class, "puzzle_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

