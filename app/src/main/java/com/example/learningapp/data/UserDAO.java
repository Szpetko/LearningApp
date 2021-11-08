package com.example.learningapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learningapp.model.User;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM users WHERE username = :username and password = :password")
    User getUser(String username, String password);

    @Insert
    void registerUser(User user);
}
