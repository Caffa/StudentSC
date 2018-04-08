package com.example.caffae.studentsc;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.caffae.studentsc.Classroom.AvailableClassroomFragment;
import com.example.caffae.studentsc.Classroom.DatabaseClassroom;
import com.example.caffae.studentsc.Feedback.RatingLecturer.FeedbackPageFragment;

import com.example.caffae.studentsc.Forum.Forum;
import com.example.caffae.studentsc.Grades.GradesPageFragment;

import java.util.zip.Inflater;

public class StudentMainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private DatabaseClassroom databaseClassroom;
    public static String ongoingQuiz = "";
    public static String ongoingBroadcast = "";
    Context mContext;
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager manager;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.container, new Forum()).commit();
                    return true;
                case R.id.navigation_dashboard:

                    manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.container, new AvailableClassroomFragment()).commit();
                    ongoingQuiz = databaseClassroom.getOngoing()[0].substring(1, databaseClassroom.getOngoing()[0].length() - 1);
                    ongoingBroadcast = databaseClassroom.getOngoing()[1].substring(1, databaseClassroom.getOngoing()[1].length() - 1);

                    System.out.println("Ongoing" + ongoingBroadcast);
                    //mTextMessage.setText(R.string.title_dashboard);
                    // manager = getSupportFragmentManager();
                    // manager.beginTransaction().replace(R.id.container, new AddQuestionFragment()).commit();

                    return true;
                case R.id.navigation_notifications:
                    manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.container, new FeedbackPageFragment()).commit();
                    return true;
                case R.id.navigation_grades:
                    manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.container, new GradesPageFragment()).commit();
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        mContext = StudentMainActivity.this;
        try {
            databaseClassroom = new DatabaseClassroom(mContext);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        databaseClassroom.fetchOngoingQuiz();
        databaseClassroom.fetchOngoingBroadcast();
        BottomNavigationView navigation = findViewById(R.id.navigationBarSA);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }


    class fetchOngoing{
        public void fetch() throws InterruptedException {
            databaseClassroom = new DatabaseClassroom(getApplicationContext());
            databaseClassroom.fetchOngoingQuiz();
            databaseClassroom.fetchOngoingBroadcast();
        }
    }

}
