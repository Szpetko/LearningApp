package com.example.learningapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.PropertyKey;

@Entity(tableName = "stats")
public class Stats {

    @PrimaryKey(autoGenerate = true)
    private int statsId;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "ch1_theory")
    private int ch1_theory;

    @ColumnInfo(name = "ch1_test")
    private int ch1_test;

    @ColumnInfo(name = "ch1_grade")
    private int ch1_grade;

    @ColumnInfo(name = "ch2_theory")
    private int ch2_theory;

    @ColumnInfo(name = "ch2_test")
    private int ch2_test;

    @ColumnInfo(name = "ch2_grade")
    private int ch2_grade;

    @ColumnInfo(name = "ch3_theory")
    private int ch3_theory;

    @ColumnInfo(name = "ch3_test")
    private int ch3_test;

    @ColumnInfo(name = "ch3_grade")
    private int ch3_grade;

    //Constructor
    public Stats(int userId, int ch1_theory, int ch1_test, int ch1_grade, int ch2_theory, int ch2_test, int ch2_grade, int ch3_theory, int ch3_test, int ch3_grade) {
        this.userId = userId;
        this.ch1_theory = ch1_theory;
        this.ch1_test = ch1_test;
        this.ch1_grade = ch1_grade;
        this.ch2_theory = ch2_theory;
        this.ch2_test = ch2_test;
        this.ch2_grade = ch2_grade;
        this.ch3_theory = ch3_theory;
        this.ch3_test = ch3_test;
        this.ch3_grade = ch3_grade;
    }


    //Getters and Setters
    public int getStatsId() {
        return statsId;
    }

    public void setStatsId(int statsId) {
        this.statsId = statsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCh1_theory() {
        return ch1_theory;
    }

    public void setCh1_theory(int ch1_theory) {
        this.ch1_theory = ch1_theory;
    }

    public int getCh1_test() {
        return ch1_test;
    }

    public void setCh1_test(int ch1_test) {
        this.ch1_test = ch1_test;
    }

    public int getCh1_grade() {
        return ch1_grade;
    }

    public void setCh1_grade(int ch1_grade) {
        this.ch1_grade = ch1_grade;
    }

    public int getCh2_theory() {
        return ch2_theory;
    }

    public void setCh2_theory(int ch2_theory) {
        this.ch2_theory = ch2_theory;
    }

    public int getCh2_test() {
        return ch2_test;
    }

    public void setCh2_test(int ch2_test) {
        this.ch2_test = ch2_test;
    }

    public int getCh2_grade() {
        return ch2_grade;
    }

    public void setCh2_grade(int ch2_grade) {
        this.ch2_grade = ch2_grade;
    }

    public int getCh3_theory() {
        return ch3_theory;
    }

    public void setCh3_theory(int ch3_theory) {
        this.ch3_theory = ch3_theory;
    }

    public int getCh3_test() {
        return ch3_test;
    }

    public void setCh3_test(int ch3_test) {
        this.ch3_test = ch3_test;
    }

    public int getCh3_grade() {
        return ch3_grade;
    }

    public void setCh3_grade(int ch3_grade) {
        this.ch3_grade = ch3_grade;
    }



    //toString
    @Override
    public String toString() {
        return "Stats{" +
                "statsId=" + statsId +
                ", userId=" + userId +
                ", ch1_theory=" + ch1_theory +
                ", ch1_test=" + ch1_test +
                ", ch1_grade=" + ch1_grade +
                ", ch2_theory=" + ch2_theory +
                ", ch2_test=" + ch2_test +
                ", ch2_grade=" + ch2_grade +
                ", ch3_theory=" + ch3_theory +
                ", ch3_test=" + ch3_test +
                ", ch3_grade=" + ch3_grade +
                '}';
    }
}
