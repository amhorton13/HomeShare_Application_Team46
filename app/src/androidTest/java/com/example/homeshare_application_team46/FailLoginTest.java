package com.example.homeshare_application_team46;

import static androidx.test.espresso.Espresso.onView;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FailLoginTest {
    // Correct username and incorrect password for testing
    public static final String STRING_TO_BE_TYPED_USERNAME = "jim@jim.com";
    public static final String STRING_TO_BE_TYPED_WRONGPASSWORD = "wrongpass";

    // ActivityScenarioRule creates, launches, and closes the activity
    @Rule
    public ActivityScenarioRule<LoginPage> activityScenarioRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void TestFailedLogin(){
        // type a correct username and wrong password into the fields and click submit
        onView(withId(R.id.etEmail)).perform(typeText(STRING_TO_BE_TYPED_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText(STRING_TO_BE_TYPED_WRONGPASSWORD), closeSoftKeyboard());

        onView(withId(R.id.btnSubmit)).perform(click());

        // TODO: check if the incorrect password toast message occurs
        //onView(withText(R.string.TOAST_STRING)).inRoot(withDecorView(not(getActivity().getWindow().getDecorView()))) .check(matches(isDisplayed()));
    }

}