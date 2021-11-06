package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class signup_activity extends AppCompatActivity {

    private Boolean isStyleSelected;
    private String lrStyle;

    private FloatingActionButton fabtn_back;
    private Spinner sp_lrStyle;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        fabtn_back = findViewById(R.id.fabtn_back);
        sp_lrStyle = findViewById(R.id.sp_lrStyle);

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
                    Toast.makeText(signup_activity.this, "You need to select Learning Style " + getStyleSelected().toString(), Toast.LENGTH_SHORT).show();

                }
                else {
                    isStyleSelected = true;
                    lrStyle = (String) parent.getItemAtPosition(position);
                    Toast.makeText(signup_activity.this, "Selected: " + lrStyle + " " + getStyleSelected().toString(), Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public void openIntroActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}