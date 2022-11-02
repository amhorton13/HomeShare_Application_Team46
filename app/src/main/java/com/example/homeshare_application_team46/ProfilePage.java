package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Date;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //TODO: Query DB using username from intent, fill in information to be used by profile page

        //Example Data
        User testUser = new User(1, "JamesHarris", "12345", 13, "I like apps");
        testUser.addInvitation(new Invitation(testUser, 1, new Date(), 1500, "USC", 2, 2));


    }
}