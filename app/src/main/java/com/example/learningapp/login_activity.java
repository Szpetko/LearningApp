package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learningapp.data.UserDAO;
import com.example.learningapp.data.UserDatabase;
import com.example.learningapp.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class login_activity extends AppCompatActivity {

    //Database
    private UserDAO userDAO;

    //References to Buttons
    private FloatingActionButton fabtn_back;
    private EditText et_username, et_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //No Bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //References to Buttons
        fabtn_back = findViewById(R.id.fabtn_back);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);


        //Database
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").allowMainThreadQueries().build().getUserDao();



        //Button Listeners
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting Text from EditTexts
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                User user = userDAO.getUser(username,password);
                if (user != null){
                    //Logging Successful

                    //Clearing Text
                    et_username.getText().clear();
                    et_password.getText().clear();

                    //Opening Next Activity
                    openHomeActivity(user.getId());
                }
                else {
                    //Logging Failed
                    Toast.makeText(login_activity.this, "Unregistered user or incorrect password.", Toast.LENGTH_SHORT).show();
                }


            }
        });

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


    public void openHomeActivity(int id){
        Intent intent = new Intent(this, home_activity.class).putExtra("id", id);
        startActivity(intent);
    }
}