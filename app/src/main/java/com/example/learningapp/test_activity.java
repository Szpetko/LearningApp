package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.animation.IntArrayEvaluator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapp.classes.CQuestions;
import com.example.learningapp.data.UserDAO;
import com.example.learningapp.data.UserDatabase;
import com.example.learningapp.model.Stats;
import com.example.learningapp.model.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class test_activity extends AppCompatActivity {


    //Database
    private UserDAO userDAO;

    //Variables
    private int userId, chapterNum;
    private int counter = 0;
    private Boolean sub_enable = false;
    private int score = 0;

    //References to Buttons
    private TextView tv_counter, tv_question;
    private RadioButton rbtn_answer_a, rbtn_answer_b, rbtn_answer_c, rbtn_answer_d;
    private RadioGroup radioGroup;
    private Button btn_submit, btn_previous, btn_next;

    CQuestions qq = new CQuestions();
    List<CQuestions> questions = new ArrayList<CQuestions>();



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
        btn_next = findViewById(R.id.btn_next);
        radioGroup = findViewById(R.id.radioGroup);

        //Reading Questions form Files
        String fileName = "Test" + chapterNum + ".txt";
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(fileName)));
            String line;
            while ((line = reader.readLine()) != null){
                String tokens[] = line.split("#");
                if (tokens.length==6){
                    int correct_ans = Integer.valueOf(tokens[5]);
                    CQuestions temp = new CQuestions(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],correct_ans);
                    questions.add(temp);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }


        //Setting TextViews
        constructQuestion(qq);


        //Button Listeners
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == -1){
                    //Nothing is checked
                    sub_enable = false;
                    btn_submit.setBackground(getResources().getDrawable(R.drawable.gray_btn));
                }else{
                    //Something is checked
                    sub_enable = true;
                    btn_submit.setBackground(getResources().getDrawable(R.drawable.purple_btn));
                }
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (sub_enable){
                    CQuestions qtemp = questions.get(counter);
                    showCorrectAnswer(qtemp.getCorrect_ans());
                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    if (qtemp.getCorrect_ans() == findRadioButton(checkedId)){
                        //Answer Correct
                        score++;
                    }
                    else {
                        //Answer Wrong
                    }
                }
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Loading next question
                if (counter < (questions.size()-1)){
                    counter++;
                    constructQuestion(qq);
                    radioGroup.clearCheck();
                    resetAnswers();

                    //Changing text of the button at the last question
                    if (counter == (questions.size()-1)){
                        btn_next.setText("Finish");
                    }
                }

                //Finish test
                else if (counter >= (questions.size()-1)) {

                    //Checking Chapter Number
                    if (chapterNum == 1) {

                        //Adding grade to the database
                        if (gradeCounting(score) >= 50){
                            userStats.setCh1_grade(gradeCounting(score));
                            userDAO.updateStats(userStats);
                            addProgress(2);
                        }
                        Integer counter = userStats.getCh1_test() + 1;
                        userStats.setCh1_test(counter);
                        userDAO.updateStats(userStats);

                    }
                    else if (chapterNum == 2) {

                        //Adding grade to the database
                        if (gradeCounting(score) >= 50){
                            userStats.setCh2_grade(gradeCounting(score));
                            userDAO.updateStats(userStats);
                            addProgress(4);
                        }
                        Integer counter = userStats.getCh2_test() + 1;
                        userStats.setCh2_test(counter);
                        userDAO.updateStats(userStats);

                    }
                    else if (chapterNum == 3) {

                        //Adding grade to the database
                        if (gradeCounting(score) >= 50){
                            userStats.setCh3_grade(gradeCounting(score));
                            userDAO.updateStats(userStats);
                            addProgress(6);
                        }
                        Integer counter = userStats.getCh3_test() + 1;
                        userStats.setCh3_test(counter);
                        userDAO.updateStats(userStats);

                    }
                    else {
                        //Error
                        Toast.makeText(test_activity.this, "Something went wrong!!! Progress can't be added.", Toast.LENGTH_SHORT).show();
                    }

                    //Opening EndScreen Activity
                    openTestEndScreenActivity(userId, gradeCounting(score));


                }


            }
        });





    }

    private void constructQuestion(CQuestions qf){
        qf = questions.get(counter);

        if (qf.getAnswer_c().equals("none")|| qf.getAnswer_d().equals("none")){
            rbtn_answer_c.setEnabled(false);
            rbtn_answer_d.setEnabled(false);
        }
        else {
            rbtn_answer_a.setEnabled(true);
            rbtn_answer_b.setEnabled(true);
            rbtn_answer_c.setEnabled(true);
            rbtn_answer_d.setEnabled(true);
        }

        tv_question.setText(qf.getQuestion());
        rbtn_answer_a.setText(qf.getAnswer_a());
        rbtn_answer_b.setText(qf.getAnswer_b());
        rbtn_answer_c.setText(qf.getAnswer_c());
        rbtn_answer_d.setText(qf.getAnswer_d());
        tv_counter.setText((counter+1) + "/" + questions.size());
    }

    private int findRadioButton(int checkedId) {
        switch (checkedId) {
            case R.id.rbtn_answer_a:
                return 1;
            case R.id.rbtn_answer_b:
                return 2;
            case R.id.rbtn_answer_c:
                return 3;
            case R.id.rbtn_answer_d:
                return 4;
        }
        return 0;
    }

    private void showCorrectAnswer(int correctAns){
        rbtn_answer_a.setClickable(false);
        rbtn_answer_b.setClickable(false);
        rbtn_answer_c.setClickable(false);
        rbtn_answer_d.setClickable(false);
        btn_submit.setEnabled(false);

        rbtn_answer_a.setTextColor(getResources().getColor(R.color.red));
        rbtn_answer_b.setTextColor(getResources().getColor(R.color.red));
        rbtn_answer_c.setTextColor(getResources().getColor(R.color.red));
        rbtn_answer_d.setTextColor(getResources().getColor(R.color.red));

        if(correctAns == 1)  rbtn_answer_a.setTextColor(getResources().getColor(R.color.green));
        else if (correctAns == 2)  rbtn_answer_b.setTextColor(getResources().getColor(R.color.green));
        else if (correctAns == 3)  rbtn_answer_c.setTextColor(getResources().getColor(R.color.green));
        else if (correctAns == 4)  rbtn_answer_d.setTextColor(getResources().getColor(R.color.green));
    }

    private void resetAnswers(){
        rbtn_answer_a.setClickable(true);
        rbtn_answer_b.setClickable(true);
        rbtn_answer_c.setClickable(true);
        rbtn_answer_d.setClickable(true);
        btn_submit.setEnabled(true);

        rbtn_answer_a.setTextColor(getResources().getColor(R.color.white));
        rbtn_answer_b.setTextColor(getResources().getColor(R.color.white));
        rbtn_answer_c.setTextColor(getResources().getColor(R.color.white));
        rbtn_answer_d.setTextColor(getResources().getColor(R.color.white));
    }


    public void openTestEndScreenActivity(int id, int grade){
        Intent intent = new Intent(this, test_end_screen_activity.class).putExtra("id", id).putExtra("grade", grade);
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

    }

    public int gradeCounting(int grade){

        double temp = (grade*100/questions.size());
        return (int)temp;
    }

}