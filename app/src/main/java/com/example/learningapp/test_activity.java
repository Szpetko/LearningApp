package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.learningapp.data.UserDAO;
import com.example.learningapp.data.UserDatabase;
import com.example.learningapp.model.Stats;
import com.example.learningapp.model.User;

public class test_activity extends AppCompatActivity {


    //Database
    private UserDAO userDAO;

    //Variables
    private int userId, chapterNum;

    //References to Buttons
    private TextView tv_counter, tv_question;
    private RadioButton rbtn_answer_a, rbtn_answer_b, rbtn_answer_c, rbtn_answer_d;
    private Button btn_submit, btn_previous, btn_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        //No Bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //Setting User ID
        userId = getIntent().getIntExtra("id",0);
        chapterNum = getIntent().getIntExtra("chNum",0);

        //Database
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").allowMainThreadQueries().build().getUserDao();
        User user = userDAO.getUserById(userId);
        Stats userStats = userDAO.getStatsByUserId(userId);


        //References to Buttons
        tv_counter = findViewById(R.id.tv_counter);
        tv_question = findViewById(R.id.tv_question);
        rbtn_answer_a = findViewById(R.id.rbtn_answer_a);
        rbtn_answer_b = findViewById(R.id.rbtn_answer_b);
        rbtn_answer_c = findViewById(R.id.rbtn_answer_c);
        rbtn_answer_d = findViewById(R.id.rbtn_answer_d);
        btn_submit = findViewById(R.id.btn_submit);
        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);


        //Button Listeners
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}