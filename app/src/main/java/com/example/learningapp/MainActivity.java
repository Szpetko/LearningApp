package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.learningapp.data.UserDAO;
import com.example.learningapp.data.UserDatabase;

public class MainActivity extends AppCompatActivity {

    //Database
    UserDAO user_db;
    UserDatabase user_dataBase;


    //References to Buttons
    private Button btn_logPage;
    private Button btn_singPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);

        //No Bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //Database
        user_dataBase = Room.databaseBuilder(this,UserDatabase.class, "User").allowMainThreadQueries().build();
        user_db = user_dataBase.getUserDao();

        //References to Buttons
        btn_logPage = findViewById(R.id.btn_continue);
        btn_singPage = findViewById(R.id.btn_logout);


        //Button Listeners
        btn_logPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Opening Login Activity
                openLoginActivity();
            }
        });

        btn_singPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Opening Signup Activity
                openSignupActivity();
            }
        });

    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, login_activity.class);
        startActivity(intent);
    }
    public void openSignupActivity(){
        Intent intent = new Intent(this, signup_activity.class);
        startActivity(intent);
    }
}