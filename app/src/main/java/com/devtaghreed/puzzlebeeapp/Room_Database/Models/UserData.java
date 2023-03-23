package com.devtaghreed.puzzlebeeapp.Room_Database.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserData {

    @PrimaryKey
    int id;
    String name;
    String email;
    int county;
    String gender;
    int age;
    String DoB;


    public UserData(int id, String name, String email, int county, String gender, int age, String DoB) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.county = county;
        this.gender = gender;
        this.age = age;
        this.DoB = DoB;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCounty() {
        return county;
    }

    public void setCounty(int county) {
        this.county = county;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
