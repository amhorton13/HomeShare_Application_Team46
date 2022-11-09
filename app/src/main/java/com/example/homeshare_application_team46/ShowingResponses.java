package com.example.homeshare_application_team46;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowingResponses extends AppCompatActivity {

    String invID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_responses);
        Intent intent = getIntent();
        invID = intent.getStringExtra("inviteID");

        displayResponses(invID);
    }

    public void displayResponses(String invID){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();;
        //Invitation temp = new Invitation();
        //TODO: the child is invitations, can you get child again with responses?
        mDatabase.child("Invitations").child(invID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    /* TODO: Display a list of users like the main feed but not invitations, users */
                    //display invitations
                    LinearLayout layout = (LinearLayout) findViewById(R.id.scrollUserLayout);
                    // james help with the layout inflator
                    LayoutInflater li = LayoutInflater.from(ShowingResponses.this);

                    HashMap<String, Boolean>  responses = new HashMap<>();
                    ArrayList<String> usersToDisplay = new ArrayList<>();
                    final TextView[] acceptView = {null};
                    if (!task.isSuccessful()) {
                                Log.e("firebase", "Error getting data", task.getException());
                            } else {
                                //get the responses column from a specific invitation item in firebase
                                System.out.println(task.getResult().child("Responses").getValue());
                                responses = ((HashMap<String, Boolean>) task.getResult().child("Responses").getValue());

                                // loop through the responses and add the user from each response to toDisplay, then display
                                for (String key : responses.keySet()){
                                    String userID = key;
                                    usersToDisplay.add(userID);
                                    // now you have a list of userIDs to display
                                }
                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();;
                            //Invitation temp = new Invitation();
                            mDatabase.child("Users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (!task.isSuccessful()) {
                                        Log.e("firebase", "Error getting data", task.getException());
                                    }
                                    else {

                                        for (String userID : usersToDisplay) {
                                            ConstraintLayout item = (ConstraintLayout) li.inflate(R.layout.response_item, layout, false);
                                            //set price
                                            TextView userName = (TextView) item.getChildAt(0);
                                            userName.setTag(userID);
                                            userName.setOnClickListener(ShowingResponses.this::displayUser);
                                            // technically want to query using userID to get some user info
                                            String userText = "User: " + task.getResult().child(userID).child("username").getValue();
                                            userName.setText(userText);

                                            // add an accept button type deal
                                            acceptView[0] = (TextView) item.getChildAt(2);
                                            acceptView[0].setTag(userID);
                                            acceptView[0].setOnClickListener(ShowingResponses.this::acceptUser);
                                            layout.addView(item);
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    public void acceptUser(View view){
        String userID = (String) view.getTag();
        HashMap<String, Object> result = new HashMap<>();
        result.put(userID, true);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Invitations").child(invID).child("Responses").setValue(result);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void displayUser(View view) {
        String userID = (String) view.getTag();
        System.out.println("DISPLAY USER  " + userID);
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("user", userID);
        startActivity(intent);
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

    public void searchAndFilter(View view){
        Intent intent = new Intent(this, SearchAndFilter.class);
        startActivity(intent);
    }

    public void openProfile(View view){
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("user", "jameswha");
        startActivity(intent);
    }

}
