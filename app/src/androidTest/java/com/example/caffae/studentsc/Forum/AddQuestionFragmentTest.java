package com.example.caffae.studentsc.Forum;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.caffae.studentsc.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by Caffae on 10/3/18.
 */

@RunWith(AndroidJUnit4.class)
public class AddQuestionFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Before
    public void init(){
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

}