package com.example.caffae.studentsc.Forum;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.caffae.studentsc.MainActivity;
import com.example.caffae.studentsc.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ForumAddTwicePlusGradesTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loginFunction(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        onView(ViewMatchers.withId(R.id.loginScreenID)).perform(replaceText("1000001"), closeSoftKeyboard());

        onView(withId(R.id.loginScreenPW)).perform(replaceText("pwd"), closeSoftKeyboard());

        onView(withId(R.id.loginCardView)).perform(click());


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.loginCardView)).perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction button = onData(anything())
                .inAdapterView(allOf(withId(R.id.gridview),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                0)))
                .atPosition(1);
        button.perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forumBasicCheck() {
        loginFunction();
        //refresh forum
        onView(withId(R.id.navigation_dashboard)).perform(click());

        onView(withId(R.id.navigation_home)).perform(click());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        onView(withText("Forum")).check(matches(isDisplayed()));
//
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recycler_view),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0)),
                        3),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

        relativeLayout.perform(click());


    }

//    @Test
//    public void correctClassroom(){
//        loginFunction();
//        //refresh forum
//        onView(withId(R.id.navigation_dashboard)).perform(click());
//
//        onView(withId(R.id.navigation_home)).perform(click());
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ViewInteraction textView2 = onView(
//                allOf(withId(R.id.question), withText("How is  this unidentified organism?"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.recycler_view),
//                                        4),
//                                0),
//                        isDisplayed()));
//
//        textView2.check(matches(isDisplayed()));
//    }

    @Test
    public void forumAddQuestionsTwice() {

        loginFunction();

        //refresh the forum
        onView(withId(R.id.navigation_dashboard)).perform(click());

        onView(withId(R.id.navigation_home)).perform(click());

        //do add q twice

        onView(withId(R.id.floatingActionButton)).perform(click());
        onView(withId(R.id.QuestionTxtSubmit)).perform(replaceText("adding a unique q\n"), closeSoftKeyboard());
        onView(withId(R.id.buttonsubmitquestion)).perform(click());


        onView(withId(R.id.floatingActionButton)).perform(click());
        onView(withId(R.id.QuestionTxtSubmit)).perform(replaceText("adding sone"), closeSoftKeyboard());
        onView(withId(R.id.buttonsubmitquestion)).perform(click());

        //now to check

        onView(withId(R.id.search_button)).perform(click());
        onView(withId(R.id.search_src_text)).perform(replaceText("adding"), closeSoftKeyboard());


        ViewInteraction textView3 = onView(
                allOf(withId(R.id.question), withText("adding a unique q"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("adding a unique q")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.question), withText("adding sone"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view),
                                        1),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("adding sone")));
    }



    @Test
    public void Grades() {
        loginFunction();
        onView(withId(R.id.navigation_grades)).perform(click());
//
//        ViewInteraction textView5 = onView(
//                allOf(withId(R.id.studentIDDisplay), withText("1000001"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.container),
//                                        0),
//                                1),
//                        isDisplayed()));
//        textView5.check(matches(withText("1000001")));

        onView(withId(R.id.studentIDDisplay)).check(matches(withText("1000001")));
        onView(withId(R.id.scoreDisplay)).check(matches(withText("36")));

//
//        ViewInteraction textView6 = onView(
//                allOf(withId(R.id.scoreDisplay), withText("36"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.container),
//                                        0),
//                                5),
//                        isDisplayed()));
//        textView6.check(matches(withText("36")));

    }

//    @Test
//    public void ForumShortPress(){
//        forumBasicCheck();
//
//        //short press
//        ViewInteraction textView2 = onView(
//                allOf(withId(R.id.question), withText("How is  this unidentified organism?"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.recycler_view),
//                                        4),
//                                0),
//                        isDisplayed()));
//
//        textView2.perform(click());
//
//
//    }

//    @Test
//    public void ForumLongPress(){
//        forumBasicCheck();
//
//        //long press
//    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
