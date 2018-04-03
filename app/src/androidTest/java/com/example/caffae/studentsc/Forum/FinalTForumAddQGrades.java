package com.example.caffae.studentsc.Forum;


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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FinalTForumAddQGrades {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void finalTForumAddQGrades() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(
                allOf(ViewMatchers.withId(R.id.loginScreenID),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("1000017"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.loginScreenPW),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("pwdpwd"), closeSoftKeyboard());

        onView(withId(R.id.loginCardView)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.loginCardView)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTextClassroomID),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editTextClassroomID),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonEnterClassroom), withText("Enter Classroom"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        ViewInteraction bottomNavigationItemView = onView(
//                allOf(withId(R.id.navigation_dashboard),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.navigationBarSA),
//                                        0),
//                                1),
//                        isDisplayed()));
//        bottomNavigationItemView.perform(click());

        onView(withId(R.id.navigation_dashboard)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.navigation_home)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        ViewInteraction bottomNavigationItemView2 = onView(
//                allOf(withId(R.id.navigation_home),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.navigationBarSA),
//                                        0),
//                                0),
//                        isDisplayed()));
//        bottomNavigationItemView2.perform(click());

//        ViewInteraction textView = onView(
//                allOf(withText("Forum"),
//                        childAtPosition(
//                                allOf(withId(R.id.toolbar),
//                                        childAtPosition(
//                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        textView.check(matches(withText("Forum")));

//        Espresso.onView(withContentDescription("Forum")).check(matches(withText("Forum"))); //forum view

//        ViewInteraction textView2 = onView(
//                allOf(withId(R.id.question), withText("When is Pi"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.recycler_view),
//                                        5),
//                                0),
//                        isDisplayed()));
//        textView2.check(matches(isDisplayed()));

//        onView(withId(R.id.question)).check(matches(isDisplayed()));

//        ViewInteraction floatingActionButton = onView(
//                allOf(withId(R.id.floatingActionButton),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.container),
//                                        2),
//                                2),
//                        isDisplayed()));
//        floatingActionButton.perform(click());

        onView(withId(R.id.floatingActionButton)).perform(click());
//
//        ViewInteraction appCompatEditText5 = onView(
//                allOf(withId(R.id.QuestionTxtSubmit),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.container),
//                                        2),
//                                1),
//                        isDisplayed()));
//        appCompatEditText5.perform(replaceText("Questiondt purpos"), closeSoftKeyboard());

        onView(withId(R.id.QuestionTxtSubmit)).perform(replaceText("Questiondt purpos"), closeSoftKeyboard());

//        ViewInteraction textView3 = onView(
//                allOf(withText("Submit Question"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.container),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textView3.check(matches(withText("Submit Question")));


//        onView(withId(R.id.QSubmitTitle)).check(matches(withText("Submit Question")));


//        ViewInteraction editText = onView(
//                allOf(withId(R.id.QuestionTxtSubmit), withText("Questiondt purpos"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.container),
//                                        0),
//                                1),
//                        isDisplayed()));
//        editText.check(matches(withText("Questiondt purpos")));

//        pressBack();

//        ViewInteraction appCompatButton2 = onView(
//                allOf(withId(R.id.buttonsubmitquestion), withText("Submit Question"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.container),
//                                        2),
//                                2),
//                        isDisplayed()));
//        appCompatButton2.perform(click());

        onView(withId(R.id.buttonsubmitquestion)).perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        ViewInteraction bottomNavigationItemView3 = onView(
//                allOf(withId(R.id.navigation_grades),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.navigationBarSA),
//                                        0),
//                                3),
//                        isDisplayed()));
//        bottomNavigationItemView3.perform(click());

        onView(withId(R.id.navigation_grades)).perform(click());

//        ViewInteraction textView4 = onView(
//                allOf(withId(R.id.studentIDDisplay), withText("1000017"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.container),
//                                        0),
//                                1),
//                        isDisplayed()));
//        textView4.check(matches(withText("1000017")));

        onView(withId(R.id.studentIDDisplay)).check(matches(withText("1000017")));

//        ViewInteraction textView5 = onView(
//                allOf(withId(R.id.scoreDisplay), withText("54"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.container),
//                                        0),
//                                5),
//                        isDisplayed()));
//        textView5.check(matches(withText("54")));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        onView(withId(R.id.scoreDisplay)).check(matches(withText("54")));

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        ViewInteraction editText2 = onView(
//                allOf(withId(R.id.individualQScores), withText("Quiz 0 : 7\nQuiz 1 : 10\nQuiz 2 : 1\n"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.container),
//                                        0),
//                                7),
//                        isDisplayed()));
//        editText2.check(matches(withText("Quiz 0 : 7 Quiz 1 : 10 Quiz 2 : 1 ")));

//        onView(withId(R.id.individualQScores)).check(ma("Quiz 0")))

//        ViewInteraction bottomNavigationItemView4 = onView(
//                allOf(withId(R.id.navigation_home),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.navigationBarSA),
//                                        0),
//                                0),
//                        isDisplayed()));
//        bottomNavigationItemView4.perform(click());

        onView(withId(R.id.navigation_home)).perform(click());

//        ViewInteraction bottomNavigationItemView5 = onView(
//                allOf(withId(R.id.navigation_home),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.navigationBarSA),
//                                        0),
//                                0),
//                        isDisplayed()));
//        bottomNavigationItemView5.perform(click());
//
//        onView(withId(R.id.navigation_home)).perform(click());


        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.search_button), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withId(R.id.search_bar),
                                        childAtPosition(
                                                withId(R.id.action_search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.search_close_btn), withContentDescription("Clear query"),
                        childAtPosition(
                                allOf(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.search_button), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withId(R.id.search_bar),
                                        childAtPosition(
                                                withId(R.id.action_search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("ques"), closeSoftKeyboard());

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.question), withText("Questiondt purpos"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view),
                                        0),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("Questiondt purpos")));

//        ViewInteraction textView7 = onView(
//                allOf(withId(R.id.answered), withText("Not Answered"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.recycler_view),
//                                        0),
//                                1),
//                        isDisplayed()));
//        textView7.check(matches(withText("Not Answered")));

    }

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
