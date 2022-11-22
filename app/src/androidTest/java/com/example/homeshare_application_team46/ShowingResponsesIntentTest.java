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
 * Test Case #11
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ShowingResponsesIntentTest {

    // ActivityScenarioRule creates, launches, and closes the activity
    @Rule
    public ActivityScenarioRule<InvitationDetails> activityScenarioRule = new ActivityScenarioRule<>(InvitationDetails.class);

    @Test
    public void TestSuccessfulPosting(){
        // click on the respond button
        onView(withId(R.id.respondButton)).perform(click());
        // Do UI tests always need to start from the loading of the app?
        // Otherwise, how does it know if the button says show responses or respond
        // TODO: check if the invite details activity is started
    }

}