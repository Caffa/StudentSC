package com.example.caffae.studentsc.Forum;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;


import com.example.caffae.studentsc.Class.AvailableClassroomFragment;
import com.example.caffae.studentsc.Feedback.FeedbackPageFragment;
import com.example.caffae.studentsc.R;

public class StudentMainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager manager;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.container, new Forum()).commit();
                    return true;
                case R.id.navigation_dashboard:

                    mTextMessage.setText("Class");
                    manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.container, new AvailableClassroomFragment()).commit();

                    //mTextMessage.setText(R.string.title_dashboard);
                   // manager = getSupportFragmentManager();
                   // manager.beginTransaction().replace(R.id.container, new AddQuestionFragment()).commit();

                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText("Feedback");
                    manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.container, new FeedbackPageFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationBarSA);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
