package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapp.classes.CQuestions;
import com.example.learningapp.data.UserDAO;
import com.example.learningapp.data.UserDatabase;
import com.example.learningapp.model.Stats;
import com.example.learningapp.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

public class theory_activity extends AppCompatActivity {


    //Database
    private UserDAO userDAO;

    //Variables
    private int userId, chapterNum;
    private String chapter = "";
    private TextToSpeech textToSpeech;

    //References to Buttons
    private TextView tv_theory, tv_chTitle;
    private Button btn_finish, btn_play;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theory_activity);

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

        //References to Buttons
        tv_theory = findViewById(R.id.tv_question);
        btn_finish = findViewById(R.id.btn_next);
        tv_chTitle = findViewById(R.id.tv_counter);
        btn_play = findViewById(R.id.btn_play);



        //Reading Text form Files
        String fileName = "Theory" + chapterNum + ".txt";
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(fileName)));
            String line;
            while ((line = reader.readLine()) != null){
                chapter = chapter + "\n" + line;
            }
            reader.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }


        //Setting TextViews
        tv_theory.setText(chapter);
        tv_chTitle.setText("Chapter " + chapterNum);



        //TextToSpeech object
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });



        //Setting Learning Style
        if (user.getLr_style().equals("Optical")){
            btn_play.setVisibility(View.GONE);
            //Just showing text
        }
        else if(user.getLr_style().equals("Acoustic")){
            tv_theory.setVisibility(View.GONE);
            //Just showing audio play button
        }
        else if (user.getLr_style().equals("Kinematic")){
            //Showing both text and audio button
        }

        //Button Listeners
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Checking chapter number and adding progress
                if (chapterNum == 1) addProgress(1);
                else if (chapterNum == 2)addProgress(3);
                else if (chapterNum == 3)addProgress(5);
                else {
                    //Error
                    Toast.makeText(theory_activity.this, "Something went wrong!!! Progress can't be added.", Toast.LENGTH_SHORT).show();

                    //Opening chapters activity
                    openChaptersActivity(userId);
                }

            }
        });


        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Boolean isPlaying = false;
                if (!textToSpeech.isSpeaking()){
                    textToSpeech.speak(chapter,TextToSpeech.QUEUE_FLUSH,null);
                    btn_play.setText("Stop");
                }
                else {
                    textToSpeech.stop();
                    btn_play.setText("Play");
                }

            }
        });



    }


    public void openChaptersActivity(int id){
        Intent intent = new Intent(this, chapters_activity.class).putExtra("id", id);
        startActivity(intent);
        finish();
    }

    public void addProgress(int limit){

        //Database
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").allowMainThreadQueries().build().getUserDao();
        User user = userDAO.getUserById(userId);

        Integer userProgress = user.getProgress();
        if (userProgress < limit)
        {
            Integer tempProgress = userProgress + 1;
            user.setProgress(tempProgress);
            userDAO.updateUser(user);
        }
        openChaptersActivity(userId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textToSpeech.shutdown();
    }
}