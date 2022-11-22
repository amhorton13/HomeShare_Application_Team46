package com.example.homeshare_application_team46;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class searchFilterLogicUnitTest {
    int numBath = 4;
    int numBed = 4;
    int price = 1650;
    int finalMinBdrm = 1;
    int finalMaxBdrm = 5;
    int finalMinBath = 2;
    int finalMaxBath = 4;
    int finalMinRent = 900;
    int finalMaxRent = 1800;


    boolean goodBed = numBed >= finalMinBdrm && numBed <= finalMaxBdrm;
    boolean goodBath = numBath >= finalMinBath && numBath <= finalMaxBath;
    boolean goodPrice = price >= finalMinRent && price <= finalMaxRent;
    boolean isActive = true;

    @Test
    public void testValidateForm() {
        assertEquals(true, goodBed && goodBath && goodPrice && isActive);
    }

}