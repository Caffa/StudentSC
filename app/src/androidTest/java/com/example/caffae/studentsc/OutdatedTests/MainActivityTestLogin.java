package com.example.caffae.studentsc.OutdatedTests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.caffae.studentsc.MainActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by Caffae on 10/3/18.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTestLogin {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
//
//    @Test
//    public void correctLogin(){
//        onView(withId(R.id.loginScreenID)).check(matches((isDisplayed()))); //check this is displayed
//        onView(withId(R.id.loginScreenPW)).check(matches((isDisplayed()))); //check this is displayed
//        onView(withId(R.id.loginCardView)).check(matches((isDisplayed()))); //check this is displayed
//
//        //correct login
//        onView(withId(R.id.loginScreenID)).perform(clearText(),typeText("1002222"));
//        onView(withId(R.id.loginScreenPW)).perform(clearText(),typeText("pwd"));
//        onView(withId(R.id.loginCardView)).perform(click());
//
//        //check if it goes
//        onView(withId(R.id.navigationBarSA)).check(matches((isDisplayed())));
//    }
//
//    @Test
//    public void wrongLogin(){
//        onView(withId(R.id.loginScreenID)).check(matches((isDisplayed()))); //check this is displayed
//        onView(withId(R.id.loginScreenPW)).check(matches((isDisplayed()))); //check this is displayed
//        onView(withId(R.id.loginCardView)).check(matches((isDisplayed()))); //check this is displayed
//
//        //correct login
//        onView(withId(R.id.loginScreenID)).perform(clearText(),typeText("2333"));
//        onView(withId(R.id.loginScreenPW)).perform(clearText(),typeText("pwd"));
//        onView(withId(R.id.loginCardView)).perform(click());
//
//        //check if it goes
//        onView(withId(R.id.navigationBarSA)).check(matches(not(isDisplayed())));
//    }

}