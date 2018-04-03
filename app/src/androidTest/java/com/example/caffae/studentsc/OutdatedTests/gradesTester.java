package com.example.caffae.studentsc.OutdatedTests;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.example.caffae.studentsc.Grades.GradesPageFragment;
import com.example.caffae.studentsc.StudentMainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Caffae on 4/4/18.
 */

public class gradesTester {
//    @Rule
//    public FragmentTestRule<?, GradesPageFragment> fragmentTestRule =
//            FragmentTestRule.create(GradesPageFragment.class);


    @Rule
    public FragmentTestRule<StudentMainActivity, GradesPageFragment> fragmentTestRule =
            new FragmentTestRule<>(StudentMainActivity.class, GradesPageFragment.class);

    @Test
    public void checkScores() throws Exception {
        onView(withText("Quiz")).check(matches(isDisplayed())); //checks that the multiline individual score works
    }
}
