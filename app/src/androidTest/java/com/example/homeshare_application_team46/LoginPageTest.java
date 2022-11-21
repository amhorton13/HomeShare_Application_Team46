package com.example.homeshare_application_team46;

import static androidx.test.espresso.Espresso.onView;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

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
public class LoginPageTest {
    // Correct username and password for testing
    public static final String STRING_TO_BE_TYPED_USERNAME = "jim@jim.com";
    public static final String STRING_TO_BE_TYPED_PASSWORD = "12345";

    // ActivityScenarioRule creates, launches, and closes the activity
    @Rule
    public ActivityScenarioRule<LoginPage> activityScenarioRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void TestSuccessfulLogin(){
        onView(withId(R.id.etEmail)).perform(typeText(STRING_TO_BE_TYPED_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(typeText(STRING_TO_BE_TYPED_PASSWORD), closeSoftKeyboard());
    }

}