package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.*;
import java.util.*;
import android.os.Handler;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private TextView login;

    private String someVariable;

    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Firebase demo
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("message");
        myRef.setValue("main working");


        String age = "age";
//        myRef.child(testUser.getUser_id()).child(age).setValue(1);


        ArrayList<Invitation> invitations = new ArrayList<>();
        Intent intent = getIntent();
        if(intent.getExtras() != null && intent.getBooleanExtra("filtering", false)){
            String minBdrm = intent.getStringExtra("minbedrooms");
            String maxBdrm = intent.getStringExtra("maxbedrooms");
            String minBath = intent.getStringExtra("minbaths");
            String maxBath = intent.getStringExtra("maxbaths");
            String minRent = intent.getStringExtra("minrent");
            String maxRent= intent.getStringExtra("maxrent");
            //TODO: get invitations from DB to display using those filters ^^, set as invitations array from above
        }
        else{
            //TODO: get all invitations from DB to display, set as invitations array from above
        }

        //Test Data
        User testUser = new User("jj@usc.edu", "jjVal", "peepeepoopoo", 43, "milfs");
        User testUser2 = new User("jj@usc.edu", "jjVal", "peepeepoopoo", 43, "milfs");
        invitations.add(new Invitation(testUser, 1, new Date(), 1500, "USC", 2, 2));
        invitations.add(new Invitation(testUser, 2, new Date(), 2000, "Orchard", 1, 1));
        invitations.add(new Invitation(testUser2, 3, new Date(), 2300, "The Moon", 4, 2));

        //display invitations (ugly)
        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
        LayoutInflater li = LayoutInflater.from(this);
        for(Invitation inv : invitations){
            TextView tv = (TextView) li.inflate(R.layout.feed_item, layout, false);
            String text = inv.getNum_bdrm() + " Bed " + inv.getNum_bath() + " Bath near ";
            text += inv.getLocation() + " for $" + inv.getPrice() + " by " + inv.getPoster().getUsername();
            tv.setText(text);
            layout.addView(tv);
        }



    }
    public void openLogin(View view){
        Intent intent = new Intent(view.getContext(), LoginPage.class);
        startActivity(intent);
    }

    //TODO: figure out getting inviteID from view and adding to intent
    public void openInvitation(View view){
        Intent intent = new Intent(this, InvitationDetails.class);
        intent.putExtra("inviteID", 1);
        startActivity(intent);
    }


}