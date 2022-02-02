package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class test_end_screen_activity extends AppCompatActivity {

    //Variables
    private int userGrade, userId;

    //References to Buttons
    private TextView tv_info, tv_grade;
    private Button btn_finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_end_screen_activity);

        //No Bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //Setting User Grade
        userId = getIntent().getIntExtra("id",0);
        userGrade = getIntent().getIntExtra("grade",0);

        //References to Buttons
        tv_info = findViewById(R.id.tv_info);
        tv_grade = findViewById(R.id.tv_grade);
        btn_finish = findViewById(R.id.btn_finish);


        //Setting Text
        tv_info.setText(infoSetter(userGrade));
        tv_grade.setText("Your grade is: " + gradeSetter(userGrade));

        //Button Listeners
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Opening Chapters Activity
                openChaptersActivity(userId);
            }
        });

    }


    private String infoSetter(int gradeProc)
    {
        if (gradeProc >= 0 && gradeProc <= 49) return "You need repetition! Next time you will definitely do better.";
        else if (gradeProc >= 50 && gradeProc <= 59) return "You barely passed.";
        else if (gradeProc >= 60 && gradeProc <= 69) return "You did pretty well! But you can do better.";
        else if (gradeProc >= 70 && gradeProc <= 79) return "You did good, but you can definitely improve.";
        else if (gradeProc >= 80 && gradeProc <= 89) return "You did almost perfect.";
        else if (gradeProc >= 90 && gradeProc <= 100) return "Well done! You answered everything right.";
        else return "none";
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

    public void openChaptersActivity(int id){
        Intent intent = new Intent(this, chapters_activity.class).putExtra("id", id);
        startActivity(intent);
        finish();
    }
}