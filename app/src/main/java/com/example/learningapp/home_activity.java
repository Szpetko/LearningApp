package com.example.learningapp;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapp.data.UserDAO;
import com.example.learningapp.data.UserDatabase;
import com.example.learningapp.model.Stats;
import com.example.learningapp.model.User;
import com.example.learningapp.model.UserAndStats;

import java.util.List;

public class home_activity extends AppCompatActivity {

    //Database
    private UserDAO userDAO;


    private int userId;
    private int currentProgress;



    //References to Buttons
    private TextView tv_lrStyle, tv_name, tv_progressProc;
    private Button btn_continue, btn_stats, btn_logout;
    private ProgressBar pb_progress;


    //Getters and Setters
    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        //No Bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        //References to Buttons
        tv_lrStyle = findViewById(R.id.tv_lrStyle);
        tv_name = findViewById(R.id.tv_name);
        tv_progressProc = findViewById(R.id.tv_progressProc);
        btn_continue = findViewById(R.id.btn_continue);
        btn_stats = findViewById(R.id.btn_stats);
        btn_logout = findViewById(R.id.btn_logout);
        pb_progress = findViewById(R.id.pb_progress);

        //Setting User ID
        userId = getIntent().getIntExtra("id",0);

        //Database
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").allowMainThreadQueries().build().getUserDao();
        User user = userDAO.getUserById(userId);



        //Setting TextViews
        tv_lrStyle.setText("Learning Style: None");
        tv_name.setText("Name");

        //Setting ProgressBar
        float userProgress = user.getProgress();
        currentProgress = Math.round((userProgress / 50) * 100);
        tv_progressProc.setText(currentProgress + "%");
        pb_progress.setProgress(currentProgress);
        pb_progress.setMax(100);


        //Loading from Database
        if (user != null){

            //Successfully taking things from database
            tv_lrStyle.setText("Learning Style: " + user.getLr_style());
            tv_name.setText(user.getUsername());
        }
        else {
            //Error taking things from database
            Toast.makeText(home_activity.this, "Something went wrong! Can not load user!", Toast.LENGTH_SHORT).show();
        }






        //Button Listeners
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start or Continue the learning

                user.setProgress(user.getProgress()+1);
                userDAO.updateUser(user);
                //currentProgress = user.getProgress()/20 * 100;
                float userProgress = user.getProgress();
                currentProgress = Math.round((userProgress / 50) * 100);
                //Toast.makeText(home_activity.this, "Progress: " + user.getProgress() + " | " + currentProgress, Toast.LENGTH_SHORT).show();
                pb_progress.setProgress(currentProgress);
                pb_progress.setMax(100);
                tv_progressProc.setText(currentProgress + "%");
            }
        });

        btn_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Opening the tab with Statistics


                Stats userStats = userDAO.getStatsByUserId(userId);
                Toast.makeText(home_activity.this, "cos: " + userStats.toString(), Toast.LENGTH_SHORT).show();


            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setProgress(user.getProgress()-1);
                userDAO.updateUser(user);
                //currentProgress = user.getProgress()/20 * 100;
                float userProgress = user.getProgress();
                currentProgress = Math.round((userProgress / 50) * 100);
                //Toast.makeText(home_activity.this, "Progress: " + user.getProgress() + " | " + currentProgress, Toast.LENGTH_SHORT).show();
                pb_progress.setProgress(currentProgress);
                pb_progress.setMax(100);
                tv_progressProc.setText(currentProgress + "%");


                //Logging out
            }
        });


    }


}