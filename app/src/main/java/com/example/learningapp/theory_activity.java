package com.example.learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapp.data.UserDAO;
import com.example.learningapp.data.UserDatabase;
import com.example.learningapp.model.Stats;
import com.example.learningapp.model.User;

public class theory_activity extends AppCompatActivity {


    //Database
    private UserDAO userDAO;

    //Variables
    private int userId, chapterNum;
    private String chapter1, chapter2, chapter3;

    //References to Buttons
    private TextView tv_theory, tv_chTitle;
    private Button btn_finish;



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
        Stats userStats = userDAO.getStatsByUserId(userId);

        //References to Buttons
        tv_theory = findViewById(R.id.tv_question);
        btn_finish = findViewById(R.id.btn_next);
        tv_chTitle = findViewById(R.id.tv_counter);


        //Setting Chapter Text
        chapter1 = "The Palaeolithic period is generally understudied in Greece, because research has traditionally focused on the later parts of prehistory (Neolithic, Bronze Age) and the Classical times. Nevertheless, significant advances have been achieved during the last years and the record has been enriched with new material, collected mostly in the framework of regional surveys but also through systematic or rescue excavations. Not only new caves and rockshelters, but also recently discovered and important open-air sites are now being excavated. The Apidima Cave in Mani, southern Greece, contains the oldest remains of anatomically modern humans outside of Africa, dated to 210,000 years ago. The known anthropological and archaeological finds so far allow the division of the Palaeolithic in the Greek area into Lower (350,000-100,000), Middle (100,000-35,000) and Upper Palaeolithic (35,000-11,000 BP). Human habitation has been traced to caves, rockshelters and open sites. There are, to date, few sites of the Lower Palaeolithic whereas there are more of the Middle and Upper Palaeolithic. This is partly due to the intense tectonic activity in the Greek area and the rise and fall of the Aegean which destroyed every trace of habitation from some geographical regions.\n" +
                "\n" +
                "Palaeolithic finds from Greece were first reported in 1867 whereas the first organized research on Palaeolithic sites were conducted between 1927 and 1931 by the Austrian archaeologist Adalbert Markovits. The first excavation of a Palaeolithic site took place in 1942 at Seidi Cave in Boeotia by the German archaeologist Rudolf Stampfuss. More systematic research, however, in Greece was conducted during the 60's in Epirus, Macedonia, Thessaly and the Peloponnese by English, American and German research groups.";

        chapter2 = "Ancient Greece refers to a period of Greek history that lasted from the Dark Ages to the end of antiquity (c. AD 600). In common usage, it refers to all Greek history before the Roman Empire, but historians use the term more precisely. Some writers include the periods of the Minoan and Mycenaean civilizations, while others argue that these civilizations were so different from later Greek cultures that they should be classed separately. Traditionally, the Ancient Greek period was taken to begin with the date of the first Olympic Games in 776 BC, but most historians now extend the term back to about 1000 BC.\n" +
                "\n" +
                "The traditional date for the end of the Classical Greek period is the death of Alexander the Great in 323 BC. The period that follows is classed as Hellenistic. Not everyone treats the Classical Greek and Hellenic periods as distinct; however, and some writers treat the Ancient Greek civilization as a continuum running until the advent of Christianity in the 3rd century AD.\n" +
                "\n" +
                "Ancient Greece is considered by most historians to be the foundational culture of Western civilization. Greek culture was a powerful influence in the Roman Empire, which carried a version of it to many parts of Europe. Ancient Greek civilization has been immensely influential on the language, politics, educational systems, philosophy, art, and architecture of the modern world, particularly during the Renaissance in Western Europe and again during various neo-classical revivals in 18th and 19th-century Europe and the Americas.";

        chapter3 = "Militarily, Greece itself declined to the point that the Romans conquered the land (168 BC onwards), though Greek culture would in turn conquer Roman life. Although the period of Roman rule in Greece is conventionally dated as starting from the sacking of Corinth by the Roman Lucius Mummius in 146 BC, Macedonia had already come under Roman control with the defeat of its king, Perseus, by the Roman Aemilius Paullus at Pydna in 168 BC.\n" +
                "\n" +
                "The Romans divided the region into four smaller republics, and in 146 BC Macedonia officially became a province, with its capital at Thessalonica. The rest of the Greek city-states gradually and eventually paid homage to Rome ending their de jure autonomy as well. The Romans left local administration to the Greeks without making any attempt to abolish traditional political patterns. The agora in Athens continued to be the center of civic and political life.\n" +
                "\n" +
                "Caracalla's decree in AD 212, the Constitutio Antoniniana, extended citizenship outside Italy to all free adult men in the entire Roman Empire, effectively raising provincial populations to equal status with the city of Rome itself. The importance of this decree is historical, not political. It set the basis for integration where the economic and judicial mechanisms of the state could be applied throughout the Mediterranean as was once done from Latium into all Italy. In practice of course, integration did not take place uniformly. Societies already integrated with Rome, such as Greece, were favored by this decree, in comparison with those far away, too poor, or just too alien such as Britain, Palestine, or Egypt.\n" +
                "\n" +
                "Caracalla's decree did not set in motion the processes that led to the transfer of power from Italy and the West to Greece and the East, but rather accelerated them, setting the foundations for the millennium-long rise of Greece, in the form of the Eastern Roman Empire, as a major power in Europe and the Mediterranean in the Middle Ages.";


        //Setting TextViews
        if (chapterNum == 1){
            tv_theory.setText(chapter1);
            tv_chTitle.setText("Chapter 1");
        }
        else if (chapterNum == 2){
            tv_theory.setText(chapter2);
            tv_chTitle.setText("Chapter 2");
        }
        else if (chapterNum == 3){
            tv_theory.setText(chapter3);
            tv_chTitle.setText("Chapter 3"); }
        else {
            tv_theory.setText("Error, sorry something went wrong");
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

}