package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.learningapp.data.UserDAO;
import com.example.learningapp.data.UserDatabase;
import com.example.learningapp.model.Stats;
import com.example.learningapp.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class chapters_activity extends AppCompatActivity {

    //Database
    private UserDAO userDAO;

    //Variables
    private int userId;


    //References to Buttons
    private FloatingActionButton fabtn_back;
    private Button btn_Theory1, btn_Theory2, btn_Theory3, btn_Test1, btn_Test2, btn_Test3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapters_activity);

        //No Bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }



        //References to Buttons
        fabtn_back = findViewById(R.id.fabtn_back);
        btn_Theory1 = findViewById(R.id.btn_next);
        btn_Theory2 = findViewById(R.id.btn_Theory2);
        btn_Theory3 = findViewById(R.id.btn_Theory3);
        btn_Test1 = findViewById(R.id.btn_Test1);
        btn_Test2 = findViewById(R.id.btn_Test2);
        btn_Test3 = findViewById(R.id.btn_Test3);


        //Setting User ID
        userId = getIntent().getIntExtra("id",0);

        //Database
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").allowMainThreadQueries().build().getUserDao();
        User user = userDAO.getUserById(userId);
        Stats userStats = userDAO.getStatsByUserId(userId);

        //Button Listeners
        btn_Theory1.setBackground(getResources().getDrawable(R.drawable.purple_btn));
        btn_Theory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Adding Approach
                Integer counter = userStats.getCh1_theory() + 1;
                userStats.setCh1_theory(counter);
                userDAO.updateStats(userStats);

                //Opening Next Activity
                openTheoryActivity(userId,1);
            }
        });


        if (user.getProgress()>=1){
            btn_Test1.setBackground(getResources().getDrawable(R.drawable.purple_btn));
            btn_Test1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Opening Next Activity
                    openTestActivity(userId, 1);

                }
            });
        }
        if (user.getProgress()>=2){
            btn_Theory2.setBackground(getResources().getDrawable(R.drawable.purple_btn));
            btn_Theory2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Adding Approach
                    Integer counter = userStats.getCh2_theory() + 1;
                    userStats.setCh2_theory(counter);
                    userDAO.updateStats(userStats);

                    //Opening Next Activity
                    openTheoryActivity(userId,2);
                }
            });
        }
        if (user.getProgress()>=3){
            btn_Test2.setBackground(getResources().getDrawable(R.drawable.purple_btn));
            btn_Test2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Opening Next Activity
                    openTestActivity(userId, 2);
                }
            });
        }
        if (user.getProgress()>=4){
            btn_Theory3.setBackground(getResources().getDrawable(R.drawable.purple_btn));
            btn_Theory3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Adding Approach
                    Integer counter = userStats.getCh3_theory() + 1;
                    userStats.setCh3_theory(counter);
                    userDAO.updateStats(userStats);

                    //Opening Next Activity
                    openTheoryActivity(userId,3);
                }
            });
        }
        if (user.getProgress()>=5){
            btn_Test3.setBackground(getResources().getDrawable(R.drawable.purple_btn));
            btn_Test3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Opening Next Activity
                    openTestActivity(userId, 3);
                }
            });
        }


        //Go Back Button
        fabtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Going to the Previous Activity
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void openTheoryActivity(int id, int chNum){
        Intent intent = new Intent(this, theory_activity.class).putExtra("id", id).putExtra("chNum",chNum);
        startActivity(intent);
        finish();
    }

    public void openTestActivity(int id, int chNum){
        Intent intent = new Intent(this, test_activity.class).putExtra("id", id).putExtra("chNum",chNum);
        startActivity(intent);
        finish();
    }
}