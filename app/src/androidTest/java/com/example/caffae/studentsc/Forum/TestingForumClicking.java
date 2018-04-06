package com.example.caffae.studentsc.Forum;


import android.support.test.espresso.DataInteraction;
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestingForumClicking {

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

        onView(withId(R.id.navigation_dashboard)).perform(click());

        onView(withId(R.id.navigation_home)).perform(click());
    }
//    @Test
//    public void testingForumClicking() {
//
//        loginFunction();
//
//        onView(allOf(
//                withId(R.id.question), withText("When is Traveler's Algorithm"))).perform(longClick());
//
//        ViewInteraction textView = onView(
//                allOf(withId(R.id.titleQ), withText("When is Traveler's Algorithm"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textView.check(matches(withText("When is Traveler's Algorithm")));
//
//        ViewInteraction textView2 = onView(
//                allOf(withId(R.id.contentBoxAns), withText("2018"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        textView2.check(matches(withText("2018")));
//
//        ViewInteraction linearLayout = onView(
//                allOf(childAtPosition(
//                        allOf(withId(android.R.id.content),
//                                childAtPosition(
//                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
//                                        0)),
//                        0),
//                        isDisplayed()));
//        linearLayout.check(matches(isDisplayed()));
//
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
