package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.learningapp.data.UserDAO;
import com.example.learningapp.data.UserDatabase;
import com.example.learningapp.model.Stats;
import com.example.learningapp.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class signup_activity extends AppCompatActivity {

    //Database
    private UserDAO userDAO;

    //Variables
    private Boolean isStyleSelected;
    private String lrStyle;

    //References to Buttons
    private FloatingActionButton fabtn_back;
    private Spinner sp_lrStyle;
    private EditText et_username, et_password, et_rePassword;
    private Button btn_signup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        //No Bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //References to Buttons
        fabtn_back = findViewById(R.id.fabtn_back);
        sp_lrStyle = findViewById(R.id.sp_lrStyle);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_rePassword = findViewById(R.id.et_repassword);
        btn_signup = findViewById(R.id.btn_signup);


        //Database
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").allowMainThreadQueries().build().getUserDao();

        //Button Listeners
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting Text from EditTexts
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String rePassword = et_rePassword.getText().toString().trim();

                //Checking Match of the passwords
                if (password.equals(rePassword)){
                    User user = new User(username,password,lrStyle,0);

                    //Checking If Fields Are Filled
                    if (invalidInput(user)){

                        //Register Success
                        userDAO.registerUser(user);
                        user = userDAO.getUser(username,password);
                        Stats stats = new Stats(user.getId(),0,0,0,0,0,0,0,0,0);
                        userDAO.insertStats(stats);
                        Toast.makeText(getApplicationContext(), "User Registered!", Toast.LENGTH_SHORT).show();

                        //Going to the Previous Activity
                        onBackPressed();
                    }
                    else{

                        //Register Fail
                        Toast.makeText(signup_activity.this, "Fill all fields!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {

                    Toast.makeText( signup_activity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Go Back Button
        fabtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.learning_styles, R.layout.spnr_style);
        adapter.setDropDownViewResource(R.layout.sprn_drpdn_style);
        sp_lrStyle.setAdapter(adapter);


        sp_lrStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    //Learning Style not Selected
                    isStyleSelected = false;
                }
                else {
                    //Learning Style Selected
                    isStyleSelected = true;
                    lrStyle = (String) parent.getItemAtPosition(position);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(signup_activity.this, "Nothing is selected", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private Boolean invalidInput(User user){
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || isStyleSelected != true){
            return false;
        }
        else {
            return true;
        }
    }


}