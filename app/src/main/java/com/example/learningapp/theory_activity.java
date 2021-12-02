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

import com.example.learningapp.data.UserDAO;
import com.example.learningapp.data.UserDatabase;
import com.example.learningapp.model.Stats;
import com.example.learningapp.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import kotlin.collections.IntIterator;

public class theory_activity extends AppCompatActivity {


    //Database
    private UserDAO userDAO;

    //Variables
    private int userId;

    //References to Buttons
    private TextView tv_theory;
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

        //Database
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User").allowMainThreadQueries().build().getUserDao();
        User user = userDAO.getUserById(userId);
        Stats userStats = userDAO.getStatsByUserId(userId);

        //References to Buttons
        tv_theory = findViewById(R.id.tv_theory);
        btn_finish = findViewById(R.id.btn_finish);



        //Setting TextViews
        tv_theory.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut eros id quam congue molestie. Integer eleifend placerat dolor sit amet sagittis. Aenean varius pellentesque convallis. Nullam a pretium neque, in pulvinar neque. Suspendisse pretium ligula sit amet nulla tincidunt vehicula. Fusce in elit vel metus lacinia efficitur et eget libero. Pellentesque vulputate venenatis nibh ut pretium. Aenean varius velit ligula, vitae tempor ligula efficitur sit amet. Etiam sed est sed metus accumsan commodo. Fusce egestas augue augue, a lacinia tortor sollicitudin a. Duis ac lectus libero. Donec quis lorem pharetra, tempor lacus sed, maximus ligula. Phasellus vitae nisl sit amet dolor varius eleifend condimentum vitae velit. Etiam lacinia leo sapien.\n" +
                "\n" +
                "In lectus magna, ultrices sit amet scelerisque at, lacinia sit amet sem. Mauris cursus tincidunt condimentum. Etiam eu magna lobortis, tincidunt massa vitae, congue nibh. Aenean at consequat lectus, vel faucibus leo. Curabitur eget tempor erat, et bibendum quam. Etiam vitae elementum ipsum. Nunc semper pretium tincidunt.\n" +
                "\n" +
                "Quisque egestas imperdiet vehicula. Praesent aliquam interdum risus, sed consectetur leo faucibus sed. Suspendisse sit amet orci purus. Quisque aliquam, elit eget consectetur tincidunt, nisi turpis interdum nunc, a pharetra odio nulla quis leo. Donec vitae purus justo. Suspendisse vel tellus in neque tempor ornare. Cras dictum viverra nisi, non interdum dui dapibus nec. Maecenas sit amet est arcu. Suspendisse ac porta tellus. Sed euismod dolor quis commodo vulputate. Integer sit amet mi urna. Etiam vestibulum orci ut dui accumsan, suscipit aliquam nunc tincidunt. Morbi molestie tortor vitae lobortis tristique.\n" +
                "\n" +
                "Vivamus dolor orci, dictum placerat consectetur gravida, sagittis quis dui. Maecenas sed diam eget ipsum tempor ultrices. Aenean convallis sapien eu orci tincidunt elementum. Curabitur tincidunt, ipsum at scelerisque venenatis, lorem lorem vestibulum erat, sed ultrices mauris erat in libero. Donec interdum vulputate interdum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur dolor tortor, vestibulum id lorem nec, dapibus aliquet urna. Suspendisse at lectus eget urna tempus pulvinar et at dolor. Phasellus pretium orci urna, elementum dapibus erat tempus fringilla. Nullam tincidunt sodales rhoncus. Praesent luctus pellentesque nibh scelerisque interdum. Nulla semper tristique erat at tempus. Maecenas est libero, luctus et magna malesuada, rhoncus interdum nisi. Pellentesque dictum ut erat vitae sodales. Sed posuere interdum tellus, non hendrerit leo blandit ut.\n" +
                "\n" +
                "Cras iaculis felis dapibus semper pellentesque. Morbi in lacus ipsum. Nulla vel dolor ut orci porta auctor. Ut nec diam porttitor, porta elit sit amet, volutpat leo. Proin efficitur auctor tincidunt. Mauris luctus at augue eget placerat. Proin rhoncus nibh quis lacus dignissim tempor. Vivamus sit amet finibus risus. Sed pellentesque commodo risus, eget ornare sapien mollis sed. Mauris tincidunt in turpis nec aliquet. Sed pellentesque odio velit, ac cursus eros dignissim vitae. Duis malesuada et elit eget efficitur. Sed scelerisque fermentum tortor, nec feugiat massa imperdiet sit amet. Sed eu aliquam felis.");



        //Button Listeners
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer userProgress = user.getProgress();
                if (userProgress < 1)
                {
                    Integer tempProgress = userProgress + 1;
                    user.setProgress(tempProgress);
                    userDAO.updateUser(user);
                }
                openChaptersActivity(userId);
            }
        });

    }


    public void openChaptersActivity(int id){
        Intent intent = new Intent(this, home_activity.class).putExtra("id", id);
        startActivity(intent);
    }

}