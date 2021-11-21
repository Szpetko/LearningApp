package com.example.learningapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserAndStats {
    @Embedded public User user;
    @Relation(parentColumn = "id", entityColumn = "userId") public Stats stats;
}
