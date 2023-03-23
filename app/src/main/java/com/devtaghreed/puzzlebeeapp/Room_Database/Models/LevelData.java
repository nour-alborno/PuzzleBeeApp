package com.devtaghreed.puzzlebeeapp.Room_Database.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LevelData {


    @PrimaryKey
    int idLevel;
    int requiredPoints;

    public LevelData(int idLevel, int requiredPoints) {
        this.idLevel = idLevel;
        this.requiredPoints = requiredPoints;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }
}
