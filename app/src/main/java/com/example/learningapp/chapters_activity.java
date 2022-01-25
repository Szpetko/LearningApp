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
                Toast.makeText(chapters_activity.this, "Theory 1 working", Toast.LENGTH_SHORT).show();
                Integer counter = userStats.getCh1_theory() + 1;
                userStats.setCh1_theory(counter);
                userDAO.updateStats(userStats);
                openTheoryActivity(userId,1);
            }
        });


        if (user.getProgress()>=1){
            btn_Test1.setBackground(getResources().getDrawable(R.drawable.purple_btn));
            btn_Test1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(chapters_activity.this, "Test 1 working", Toast.LENGTH_SHORT).show();
                    Integer userProgress = user.getProgress();
//                    if (userProgress < 4)
//                    {
//                        Integer tempProgress = userProgress + 1;
//                        user.setProgress(tempProgress);
//                        userDAO.updateUser(user);
//                    }
                    openTestActivity(userId);

                }
            });
        }
        if (user.getProgress()>=2){
            btn_Theory2.setBackground(getResources().getDrawable(R.drawable.purple_btn));
            btn_Theory2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(chapters_activity.this, "Theory 2 working", Toast.LENGTH_SHORT).show();
                    Integer counter = userStats.getCh2_theory() + 1;
                    userStats.setCh2_theory(counter);
                    userDAO.updateStats(userStats);
                    openTheoryActivity(userId,2);
                }
            });
        }
        if (user.getProgress()>=3){
            btn_Test2.setBackground(getResources().getDrawable(R.drawable.purple_btn));
            btn_Test2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(chapters_activity.this, "Test 2 working", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (user.getProgress()>=4){
            btn_Theory3.setBackground(getResources().getDrawable(R.drawable.purple_btn));
            btn_Theory3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(chapters_activity.this, "Theory 3 working", Toast.LENGTH_SHORT).show();
                    Integer counter = userStats.getCh3_theory() + 1;
                    userStats.setCh3_theory(counter);
                    userDAO.updateStats(userStats);
                    openTheoryActivity(userId,3);
                }
            });
        }
        if (user.getProgress()>=5){
            btn_Test3.setBackground(getResources().getDrawable(R.drawable.purple_btn));
            btn_Test3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(chapters_activity.this, "Test 3 working", Toast.LENGTH_SHORT).show();
                }
            });
        }


        //Go Back Button
        fabtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity(userId);
            }
        });

    }

    public void openHomeActivity(int id){
        Intent intent = new Intent(this, home_activity.class).putExtra("id", id);
        startActivity(intent);
    }

    public void openTheoryActivity(int id, int chNum){
        Intent intent = new Intent(this, theory_activity.class).putExtra("id", id).putExtra("chNum",chNum);
        startActivity(intent);
    }

    public void openTestActivity(int id){
        Intent intent = new Intent(this, test_activity.class).putExtra("id", id);
        startActivity(intent);
    }
}