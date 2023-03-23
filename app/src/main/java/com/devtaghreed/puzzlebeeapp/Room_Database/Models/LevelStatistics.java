package com.devtaghreed.puzzlebeeapp.Room_Database.Models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelData;

@Entity (foreignKeys = {@ForeignKey(entity = LevelData.class, parentColumns = "idLevel", childColumns = "LevelId",
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class LevelStatistics {

    @PrimaryKey(autoGenerate = true)
    int id;

    int LevelId;
    int numOfRiddles;
    int numCorrectAns;
    int numIncorrectAns;
    int levelPoints;


    public LevelStatistics(int LevelId, int numOfRiddles, int numCorrectAns, int numIncorrectAns, int levelPoints) {
        this.LevelId = LevelId;
        this.numOfRiddles = numOfRiddles;
        this.numCorrectAns = numCorrectAns;
        this.numIncorrectAns = numIncorrectAns;
        this.levelPoints = levelPoints;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevelId() {
        return LevelId;
    }

    public void setLevelId(int levelId) {
        LevelId = levelId;
    }

    public int getNumOfRiddles() {
        return numOfRiddles;
    }

    public void setNumOfRiddles(int numOfRiddles) {
        this.numOfRiddles = numOfRiddles;
    }

    public int getNumCorrectAns() {
        return numCorrectAns;
    }

    public void setNumCorrectAns(int numCorrectAns) {
        this.numCorrectAns = numCorrectAns;
    }

    public int getNumIncorrectAns() {
        return numIncorrectAns;
    }

    public void setNumIncorrectAns(int numIncorrectAns) {
        this.numIncorrectAns = numIncorrectAns;
    }

    public int getLevelPoints() {
        return levelPoints;
    }

    public void setLevelPoints(int levelPoints) {
        this.levelPoints = levelPoints;
    }
}
