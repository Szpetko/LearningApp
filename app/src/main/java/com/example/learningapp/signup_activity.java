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
import com.example.learningapp.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class signup_activity extends AppCompatActivity {

    //Database
    private UserDAO userDAO;


    private Boolean isStyleSelected;
    private String lrStyle;

    //References to Buttons
    private FloatingActionButton fabtn_back;
    private Spinner sp_lrStyle;
    private EditText et_username, et_password, et_rePassword;
    private Button btn_signup;


    //Getters and Setters
    public String getLrStyle() {
        return lrStyle;
    }

    public void setLrStyle(String lrStyle) {
        this.lrStyle = lrStyle;
    }


    public Boolean getStyleSelected() {
        return isStyleSelected;
    }

    public void setStyleSelected(Boolean styleSelected) {
        isStyleSelected = styleSelected;
    }


    //toString
    @Override
    public String toString() {
        return "signup_activity{" +
                "isStyleSelected=" + isStyleSelected +
                '}';
    }


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
//                Toast.makeText(signup_activity.this, "All info: " + et_username.getText().toString()
//                        + ", " + et_password.getText().toString()
//                        + ", " + et_rePassword.getText().toString()
//                        + ", " + lrStyle, Toast.LENGTH_SHORT).show();
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String rePassword = et_rePassword.getText().toString().trim();
                //lr_style



                if (password.equals(rePassword)){
                    final User user = new User(username,password,lrStyle);
                    if (invalidInput(user)){
                        userDAO.registerUser(user);
                        Toast.makeText(getApplicationContext(), "User Registered!", Toast.LENGTH_SHORT).show();
                        openIntroActivity();
                    }
                    else{
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
                openIntroActivity();
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
                    //Do nothing
                    //ERROR "You need to select Learning Style"
                    isStyleSelected = false;
                    //Toast.makeText(signup_activity.this, "You need to select Learning Style " + getStyleSelected().toString(), Toast.LENGTH_SHORT).show();

                }
                else {
                    isStyleSelected = true;
                    lrStyle = (String) parent.getItemAtPosition(position);
                    //Toast.makeText(signup_activity.this, "Selected: " + lrStyle + " " + getStyleSelected().toString(), Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(signup_activity.this, "Nothing is selected", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void openIntroActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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