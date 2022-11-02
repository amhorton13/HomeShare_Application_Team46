package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;


public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Firebase demo
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("demo");
//        for (int i = 0; i<10;i++) {
//            String id = i+"";
//            myRef.child("demo").child(id).setValue("TEST");
//        }
        User testUser = new User("1", "JamesHarris@usc.edu", "Jameswah", "i like cs", 12, "idk");
        String age = "age";
        myRef.child(testUser.getUser_id()).child(age).setValue(testUser.getAge());
//        myRef.setValue("hi");

        /*
           TODO: Get all the extras from the intents and use them to add an invitation
            to the database or filter the invitations of the database accordingly
            before presenting the feed to the user
         */
//
//        Intent intent = getIntent();
//        // check the action on the intent: add invite or filter feed
//        String action = intent.getStringExtra("action");
//        if (action.equals("postInvite")){
//
//            String propertyName = intent.getStringExtra("propertyname");
//            String num_bdrm = intent.getStringExtra("numberbedrooms");
//            String num_bath = intent.getStringExtra("numberbaths");
//            String rent_price = intent.getStringExtra("rent");
//            String address = intent.getStringExtra("address");
//
//            //TODO: use the extras to add an invite to data structure/DB
//
//        }
//        else if (action.equals("filtering")){
//            String minBdrm = intent.getStringExtra("minbedrooms");
//            String maxBdrm = intent.getStringExtra("maxbedrooms");
//            String minBath = intent.getStringExtra("minbaths");
//            String maxBath = intent.getStringExtra("maxbaths");
//            String minRent = intent.getStringExtra("minrent");
//            String maxRent= intent.getStringExtra("maxrent");
//
//            //TODO: use these extras to query and filter the invitation data and display filtered results
//        }

    }
}