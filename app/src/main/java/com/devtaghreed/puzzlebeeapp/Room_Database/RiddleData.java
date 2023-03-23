package com.devtaghreed.puzzlebeeapp.Room_Database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.devtaghreed.puzzlebeeapp.Room_Database.Models.LevelData;

import java.io.Serializable;

@Entity (foreignKeys = {@ForeignKey(entity = LevelData.class, parentColumns = "idLevel", childColumns = "levelId",
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class RiddleData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;

    int idRiddle;
    int patternId;
    int levelId;
    int points;

    String title;
    String ans1;
    String ans2;
    String ans3;
    String ans4;
    String correctAns;
    int duration;
    String hint;
    String patternName;

    public RiddleData(int idRiddle, int patternId, int levelId, int points, String title, String ans1, String ans2, String ans3, String ans4, String correctAns, int duration, String hint, String patternName) {
        this.idRiddle = idRiddle;
        this.patternId = patternId;
        this.levelId = levelId;
        this.points = points;
        this.title = title;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.correctAns = correctAns;
        this.duration = duration;
        this.hint = hint;
        this.patternName = patternName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRiddle() {
        return idRiddle;
    }

    public void setIdRiddle(int idRiddle) {
        this.idRiddle = idRiddle;
    }

    public int getPatternId() {
        return patternId;
    }

    public void setPatternId(int patternId) {
        this.patternId = patternId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
