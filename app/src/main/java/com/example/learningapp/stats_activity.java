package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.learningapp.data.UserDAO;
import com.example.learningapp.data.UserDatabase;
import com.example.learningapp.model.Stats;
import com.example.learningapp.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class stats_activity extends AppCompatActivity {


    //Database
    private UserDAO userDAO;

    //Variables
    private int userId;


    //References to Buttons
    private FloatingActionButton fabtn_back;
    private TextView tv_ch1_theory, tv_ch1_test, tv_ch1_grade,
            tv_ch2_theory, tv_ch2_test, tv_ch2_grade,
            tv_ch3_theory, tv_ch3_test, tv_ch3_grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_activity);

        //No Bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //References to Buttons
        fabtn_back = findViewById(R.id.fabtn_back);
        tv_ch1_theory=findViewById(R.id.tv_ch1_theory);
        tv_ch1_test=findViewById(R.id.tv_ch1_test);
        tv_ch1_grade=findViewById(R.id.tv_ch1_grade);
        tv_ch2_theory=findViewById(R.id.tv_ch2_theory);
        tv_ch2_test=findViewById(R.id.tv_ch2_test);
        tv_ch2_grade=findViewById(R.id.tv_ch2_grade);
        tv_ch3_theory=findViewById(R.id.tv_ch3_theory);
        tv_ch3_test=findViewById(R.id.tv_ch3_test);
        tv_ch3_grade=findViewById(R.id.tv_ch3_grade);


        //Setting User ID
        userId = getIntent().getIntExtra("id",0);

        //Database
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").allowMainThreadQueries().build().getUserDao();
        User user = userDAO.getUserById(userId);
        Stats userStats = userDAO.getStatsByUserId(userId);


        //Setting TextViews
        tv_ch1_theory.setText("Theory reads: " + userStats.getCh1_theory());
        tv_ch1_test.setText("Test approaches: " + userStats.getCh1_test());
        tv_ch1_grade.setText("Final grade: " + gradeSetter(userStats.getCh1_grade()));
        tv_ch2_theory.setText("Theory reads: " + userStats.getCh2_theory());
        tv_ch2_test.setText("Test approaches: " + userStats.getCh2_test());
        tv_ch2_grade.setText("Final grade: " + gradeSetter(userStats.getCh2_grade()));
        tv_ch3_theory.setText("Theory reads: " + userStats.getCh3_theory());
        tv_ch3_test.setText("Test approaches: " + userStats.getCh3_test());
        tv_ch3_grade.setText("Final grade: " + gradeSetter(userStats.getCh3_grade()));

        //Go Back Button
        fabtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });


    }


    private String gradeSetter(int gradeProc)
    {
        if (gradeProc >= 0 && gradeProc <= 49) return "F";
        else if (gradeProc >= 50 && gradeProc <= 59) return "E";
        else if (gradeProc >= 60 && gradeProc <= 69) return "D";
        else if (gradeProc >= 70 && gradeProc <= 79) return "C";
        else if (gradeProc >= 80 && gradeProc <= 89) return "B";
        else if (gradeProc >= 90 && gradeProc <= 100) return "A";
        else return "none";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}