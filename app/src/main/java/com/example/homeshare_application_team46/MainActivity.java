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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();;



        ArrayList<Invitation> invitations = new ArrayList<>();
        Intent intent = getIntent();

        //declare filters
        int minBdrm = Integer.MIN_VALUE;
        int maxBdrm = Integer.MAX_VALUE;
        int minBath = Integer.MIN_VALUE;
        int maxBath = Integer.MAX_VALUE;
        int minRent = Integer.MIN_VALUE;
        int maxRent= Integer.MAX_VALUE;
        if(intent.getStringExtra("filtering") != null){
            minBdrm = Integer.parseInt(intent.getStringExtra("minbedrooms"));
            maxBdrm = Integer.parseInt(intent.getStringExtra("maxbedrooms"));
            minBath = Integer.parseInt(intent.getStringExtra("minbaths"));
            maxBath = Integer.parseInt(intent.getStringExtra("maxbaths"));
            minRent = Integer.parseInt(intent.getStringExtra("minrent"));
            maxRent= Integer.parseInt(intent.getStringExtra("maxrent"));
        }


        //display invitations
        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
        LayoutInflater li = LayoutInflater.from(this);

        int finalMinBath = minBath;
        int finalMaxBath = maxBath;
        int finalMinBdrm = minBdrm;
        int finalMaxBdrm = maxBdrm;
        int finalMinRent = minRent;
        int finalMaxRent = maxRent;
        mDatabase.child("Invitations").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                ArrayList<Invitation> toDisplay = new ArrayList<>();
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for (DataSnapshot t : task.getResult().getChildren()) {
                        String poster = (String) t.child("poster").getValue();
                        String propName = (String) t.child("propName").getValue();
                        String date = (String) t.child("date_and_time").getValue();
                        String invID = (String) t.child("invitation_id").getValue();
                        String address = (String) t.child("address").getValue();
                        int numBath = ((Long) t.child("num_bath").getValue()).intValue();
                        int numBed = ((Long) t.child("num_bdrm").getValue()).intValue();
                        int price = ((Long) t.child("price").getValue()).intValue();

                        boolean goodBed = numBed >= finalMinBdrm && numBed <= finalMaxBdrm;
                        boolean goodBath = numBath >= finalMinBath && numBath <= finalMaxBath;
                        boolean goodPrice = price >= finalMinRent && price <= finalMaxRent;
                        if (goodBed && goodBath && goodPrice) {
                            System.out.println("Added:");
                            toDisplay.add(new Invitation(poster, propName, invID, date, price, address, numBed, numBath));
                        }
                    }


                    for (Invitation inv : toDisplay) {
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
                        String address = inv.getAddress();
                        String propName = inv.getPropName();
                        String dText = propName + " at " + address;
                        details.setText(dText);
                        item.setTag(inv.getInvitation_id());
                        layout.addView(item);
                    }
                }
            }
        });


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

    public void openProfile(View view){
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("user", "jameswha");
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