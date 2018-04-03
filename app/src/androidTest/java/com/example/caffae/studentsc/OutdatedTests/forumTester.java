package com.example.caffae.studentsc.OutdatedTests;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.caffae.studentsc.MainActivity;
import com.example.caffae.studentsc.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by Caffae on 4/4/18.
 */


@RunWith(AndroidJUnit4.class)
public class forumTester {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
//    private MainActivity mA = null;
//
//    private Forum fm = null;

//    @Before
//    public void setUp() throws Exception {
//        mA = activityActivityTestRule.getActivity();
//        fm =
//    }

    @Before
    public void init(){
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();

        //need to init classroom too


    }

    @Test
    public void RecyclerViewWorks(){
//        Espresso.onView(withId)

        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Selected ")))
                .check(matches(isDisplayed())); //just selected though
    }
}
