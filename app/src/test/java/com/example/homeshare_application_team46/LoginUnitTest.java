package com.example.homeshare_application_team46;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginUnitTest {
    public static final String STRING_EMAIL = "jim@jim.com";
    public static final String STRING_PASSWORD = "12345";
    public static final String STRING_EMPTY_PASSWORD = "";

    @Test
    public void testValidateForm() {
        assertEquals(true, STRING_EMAIL != "" && STRING_PASSWORD != "");
    }

    @Test
    public void testValidateFormAgain() {
        assertEquals(false, STRING_EMAIL != "" && STRING_EMPTY_PASSWORD != "");
    }
}