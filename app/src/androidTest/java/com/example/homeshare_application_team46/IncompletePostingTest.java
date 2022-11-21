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
 * Test Case #4
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class IncompletePostingTest {
    // Correct username and incorrect password for testing
    public static final String STRING_TO_BE_TYPED_PROPNAME = "Really Cool House";
    public static final String STRING_TO_BE_TYPED_ADDRESS = "2711 Orchard Ave";
    public static final String STRING_TO_BE_TYPED_PRICE = "1875";
    public static final String STRING_TO_BE_TYPED_NUMBEDS = "3";
    //public static final String STRING_TO_BE_TYPED_NUMBATHS = "2";
    public static final String STRING_TO_BE_TYPED_DATE = "2/24";

    // ActivityScenarioRule creates, launches, and closes the activity
    @Rule
    public ActivityScenarioRule<PostInvitation> activityScenarioRule = new ActivityScenarioRule<>(PostInvitation.class);

    @Test
    public void TestFailedPosting(){
        // complete some fields in the posting form
        onView(withId(R.id.etName)).perform(typeText(STRING_TO_BE_TYPED_PROPNAME), closeSoftKeyboard());
        onView(withId(R.id.etAddress)).perform(typeText(STRING_TO_BE_TYPED_ADDRESS), closeSoftKeyboard());
        onView(withId(R.id.etPrice)).perform(typeText(STRING_TO_BE_TYPED_PRICE), closeSoftKeyboard());
        onView(withId(R.id.etNumBeds)).perform(typeText(STRING_TO_BE_TYPED_NUMBEDS), closeSoftKeyboard());
        // Leave one field blank so the form is incomplete
        //onView(withId(R.id.etNumBaths)).perform(typeText(STRING_TO_BE_TYPED_NUMBATHS), closeSoftKeyboard());
        onView(withId(R.id.etDate)).perform(typeText(STRING_TO_BE_TYPED_DATE), closeSoftKeyboard());

        onView(withId(R.id.btnSubmit)).perform(click());

        // TODO: check if the incomplete field toast message occurs
        //onView(withText(R.string.TOAST_STRING)).inRoot(withDecorView(not(getActivity().getWindow().getDecorView()))) .check(matches(isDisplayed()));
    }

}