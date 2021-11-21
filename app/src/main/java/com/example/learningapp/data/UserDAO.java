package com.example.learningapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.learningapp.model.Stats;
import com.example.learningapp.model.User;
import com.example.learningapp.model.UserAndStats;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM users WHERE username = :username and password = :password")
    User getUser(String username, String password);

    @Query("SELECT * FROM users WHERE id = :id")
    User getUserById(int id);

    @Insert
    void registerUser(User user);

    @Update
    void updateUser(User user);

    @Transaction
    @Query("SELECT * FROM users WHERE id = :id")
    public List<UserAndStats> getUserAndStatsById(int id);

    @Query("SELECT * FROM stats WHERE userId = :userId")
    Stats getStatsByUserId(int userId);

    @Insert
    void insertStats (Stats stats);

    @Update
    void updateStats(Stats stats);
}
