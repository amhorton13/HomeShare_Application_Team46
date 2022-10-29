package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class InvitationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_details);
    }

    public void responseHandler(){
        //TODO: Update data structure or database that has which invites a user has responded to

        //After responding to an invite, app goes to profile page to see all invites user has responded to.
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }
}