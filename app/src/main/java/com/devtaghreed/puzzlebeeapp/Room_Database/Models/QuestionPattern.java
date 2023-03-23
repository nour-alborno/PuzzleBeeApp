package com.devtaghreed.puzzlebeeapp.Room_Database.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuestionPattern {

    @PrimaryKey
    int idPattern;
    String patternName;

    public QuestionPattern(int idPattern, String patternName) {
        this.idPattern = idPattern;
        this.patternName = patternName;
    }

    public int getIdPattern() {
        return idPattern;
    }

    public void setIdPattern(int idPattern) {
        this.idPattern = idPattern;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }
}
