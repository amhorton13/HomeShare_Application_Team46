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
 * Test Case #1
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RegisterPageTest {

    // ActivityScenarioRule creates, launches, and closes the activity
    @Rule
    public ActivityScenarioRule<LoginPage> activityScenarioRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void TestGoToRegister(){
        // Click Register Now Link
        onView(withId(R.id.registerLink)).perform(click());

        // TODO: check if the register activity is started

    }

}