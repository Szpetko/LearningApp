package com.example.learningapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "lr_style")
    private String lr_style;

    @ColumnInfo(name = "progress")
    private int progress;

    //Constructor
    public User(String username, String password, String lr_style, int progress) {
        this.username = username;
        this.password = password;
        this.lr_style = lr_style;
        this.progress = progress;
    }

    //Getters and Setters
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(@NotNull int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLr_style() {
        return lr_style;
    }

    public void setLr_style(String lr_style) {
        this.lr_style = lr_style;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }



    //toString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lr_style='" + lr_style + '\'' +
                ", progress=" + progress +
                '}';
    }
}
