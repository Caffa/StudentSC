package com.example.caffae.studentsc;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by Caffae on 10/3/18.
 */
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mATestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mA = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(StudentMainActivity.class.getName(), null, false);


    @Before
    public void setUp() throws Exception {
        mA = mATestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        //tests if this activity is launched
        View loginTV = mA.findViewById(R.id.loginScreenID);

        Assert.assertNotNull(loginTV);
    }

//    @Test
//    public void testLaunchSecondActivityCorrectLogin(){
//        //tests if next activity is launched with correct login
//        //TODO correct login -- still has issues
//        onView(withId(R.id.loginScreenID)).perform(typeText("1002222"));
//        onView(withId(R.id.loginScreenPW)).perform(typeText("pwd"));
//
//        //test for launching of second activity on login button press
//        onView(withId(R.id.loginCardView)).perform(click());
//
//        Activity acTwo = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
//
//        Assert.assertNotNull(acTwo);
//
//        acTwo.finish();
//
//    }

//    @Test
//    public void testLaunchSecondActivityWrongLogin(){
//        //tests if next activity is launched with wrong login
//        //TODO correct login
//        onView(withId(R.id.loginScreenID)).perform(typeText("1003222"));
//        onView(withId(R.id.loginScreenPW)).perform(typeText("pwd2"));
//
//        //test for launching of second activity on login button press
//        onView(withId(R.id.loginCardView)).perform(click());
//
//        Activity acTwo = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
//
//        Assert.assertNull(acTwo); //should be null
//
////        acTwo.finish();
//
//    }



    @After
    public void tearDown() throws Exception {
        mA = null;
    }

}