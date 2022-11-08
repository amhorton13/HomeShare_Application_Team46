package com.example.homeshare_application_team46;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.*;
import java.util.*;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements MyCallback {

    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef;

    // Callback
    User loggedInUser = null;
    private static final String TAG = "CallbackActivity";
    private FirebaseAuth mAuth;
    String userID;

    // Update loginUI
    private TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();


        ArrayList<Invitation> invitations = new ArrayList<>();
        Intent intent = getIntent();


        if(intent.getExtras() != null && intent.getBooleanExtra("filtering", true)){
            String minBdrm = intent.getStringExtra("minbedrooms");
            String maxBdrm = intent.getStringExtra("maxbedrooms");
            String minBath = intent.getStringExtra("minbaths");
            String maxBath = intent.getStringExtra("maxbaths");
            String minRent = intent.getStringExtra("minrent");
            String maxRent= intent.getStringExtra("maxrent");
            String minAge = intent.getStringExtra("minage");
            String maxAge= intent.getStringExtra("maxage");
            //TODO: get invitations from DB to display using those filters ^^, set as invitations array from above
        }
        else{
            //TODO: get all invitations from DB to display, set as invitations array from above
        }

        //Test Data
        User testUser = new User("jj@usc.edu", "jjVal", "peepeepoopoo", 43, "milfs");
        User testUser2 = new User("jj@usc.edu", "jjVal", "peepeepoopoo", 43, "milfs");
        invitations.add(new Invitation("testUser", "moon", "1", "jan", 1500, "USC", 2, 2));
        invitations.add(new Invitation("testUser", "lorenzo", "2",  "october", 2000, "Orchard", 1, 1));
        invitations.add(new Invitation("testUser2", "mo", "3", "december", 2300, "The Moon", 4, 2));

        //display invitations
        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
        LayoutInflater li = LayoutInflater.from(this);
        for(Invitation inv : invitations){
            ConstraintLayout item = (ConstraintLayout) li.inflate(R.layout.feed_item, layout, false);


            //set price
            TextView price = (TextView) item.getChildAt(0);
            String pText = "$" + Integer.toString(inv.getPrice());
            price.setText(pText);
            //set bedBath
            TextView bedBath = (TextView) item.getChildAt(2);
            int numBed = inv.getNum_bdrm();
            int numBath = inv.getNum_bath();
            String bbText = Integer.toString(numBed) + " Bed " + Integer.toString(numBath) + " Bath";
            bedBath.setText(bbText);
            //set details
            TextView details = (TextView) item.getChildAt(3);
            String location = inv.getAddress();
//            String poster = inv.getPoster().getUsername();
//            String dText = location + " by " + poster;
//            details.setText(dText);
            item.setOnClickListener(this::openInvitation);
            layout.addView(item);
        }


        // Create the user that's logged in
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        System.out.println("Main Activity user: " + user.getUid());
        userID = user.getUid();


        // Callback once async call is back
        readData(new MyCallback() {
            @Override
            public void onCallback(String value) {

            }
            @Override
            public void onCallback(String email, String username, String password, int age, String biography) {

                loginText = findViewById(R.id.login);
                loginText.setText(username);
                System.out.println("CALLBACK LOGGED email" + email);
                System.out.println("CALLBACK LOGGED userN" + username);
                System.out.println("CALLBACK LOGGED password" + password);
                System.out.println("CALLBACK LOGGED age" + age);
                System.out.println("CALLBACK LOGGED bio" + biography);
                loggedInUser = new User(email, username, password, age, biography);
                System.out.println("CALLBACK LOGGED bio" + loggedInUser.getEmail());
            }

        });

    }

    public void openLogin(View view){
        Intent intent = new Intent(view.getContext(), LoginPage.class);
        startActivity(intent);
        User testUser3 = new User("jj@usc.edu", "jjVal", "peepeepoopoo", 43, "milfs");
    }

    public void openInvitation(View view){
        Intent intent = new Intent(this, InvitationDetails.class);
        intent.putExtra("inviteID", (String) view.getTag());
        startActivity(intent);
    }
    public void openPostInvitation(View view){
        Intent intent = new Intent(this, PostInvitation.class);
        startActivity(intent);
    }

    // Receive data from database and call update UI
    public void readData(MyCallback myCallback) {
        userRef = database.getReference().child("Users").child(userID);

        final Object[] email = {null};
        final Object[] password = new Object[1];
        final Object[] age = new Object[1];
        final Object[] bio = new Object[1];
        final Object[] username = new Object[1];
        final User[] loggedInUser = new User[1];

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("SECOND OUT STATE " + snapshot.getValue());

                email[0] = snapshot.child("email").getValue();
                password[0] = snapshot.child("password").getValue();
                age[0] = snapshot.child("age").getValue();
                bio[0] = snapshot.child("biography").getValue();
                username[0] = snapshot.child("username").getValue();


                loggedInUser[0] = new User(email[0].toString(), username[0].toString(), password[0].toString(), 4, bio[0].toString());

                Log.d(TAG, "ONCLICK: API SUCCESS");
                myCallback.onCallback(email[0].toString(), username[0].toString(), password[0].toString(), 4, bio[0].toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    @Override
    public void onCallback(String value) {

    }

    @Override
    public void onCallback(String toString, String toString1, String toString2, int i, String toString3) {

    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}