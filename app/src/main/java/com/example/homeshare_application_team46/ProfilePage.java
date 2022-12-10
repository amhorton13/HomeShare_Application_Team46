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

import java.util.Date;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class ProfilePage extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private User userProf = new User();
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Intent intent = getIntent();
        userID = (String) intent.getStringExtra("user");

        String username = "Loading";
        TextView nameView = (TextView) findViewById(R.id.username);
        nameView.setText(username);

        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
        LayoutInflater li = LayoutInflater.from(this);
        queryUser(layout, li);
    }

    public void showInvites(View view){
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("user", userID);
        startActivity(intent);
    }
    public void editProfile(View view){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    public void logOut(View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void showResponses(View view){
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("profileSetting", "responses");
        intent.putExtra("user", userID);
        startActivity(intent);
    }

    public void openInvitation(View view){
        Intent intent = new Intent(this, InvitationDetails.class);
        intent.putExtra("inviteID", (String) view.getTag());
        startActivity(intent);
    }


    public void queryUser(LinearLayout layout, LayoutInflater li){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();;
        mDatabase.child("Users").child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    userProf.setEmail((String) task.getResult().child("email").getValue());
                    userProf.setPassword((String) task.getResult().child("password").getValue());
                    userProf.setBiography((String) task.getResult().child("biography").getValue());
                    userProf.setUsername((String) task.getResult().child("username").getValue());

                    TextView nameView = (TextView) findViewById(R.id.username);
                    nameView.setText(userProf.getUsername());

                    TextView bioView = (TextView) findViewById(R.id.bio);
                    bioView.setText(userProf.getBiography());
                }
            }
        });
        mDatabase.child("Invitations").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    for(DataSnapshot t : task.getResult().getChildren()){
                        String poster = (String) t.child("poster").getValue();
                        String propName = (String) t.child("propName").getValue();
                        String date = (String) t.child("date_and_time").getValue();
                        String invID = (String) t.child("invitation_id").getValue();
                        String address = (String) t.child("address").getValue();
                        int numBath = ((Long) t.child("num_bath").getValue()).intValue();
                        int numBed = ((Long) t.child("num_bdrm").getValue()).intValue();;
                        int price = ((Long) t.child("price").getValue()).intValue();;

                        for(DataSnapshot response : t.child("Responses").getChildren()){
                            if(userID.equals(response.getKey())){
                                userProf.addResponse(new Invitation(poster, propName, invID, date, price, address, numBed, numBath));
                            }
                        }

                        if(userID.equals(poster)){
                            userProf.addInvitation(new Invitation(poster, propName, invID, date, price, address, numBed, numBath));
                        }
                    }

                    Intent intent = getIntent();
                    if(intent.getStringExtra("profileSetting") != null && intent.getStringExtra("profileSetting").equals("responses")) {
                        for(Invitation inv : userProf.getResponses()) {
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
                            //check if accepted
                            mDatabase.child("Invitations").child(inv.getInvitation_id()).child("Responses").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (!task.isSuccessful()) {
                                        Log.e("firebase", "Error getting data", task.getException());
                                    }
                                    else {
                                        for(DataSnapshot t : task.getResult().getChildren()){
                                            if(t != null && t.getKey().equals(userID) && (boolean) t.getValue()){
                                                item.setBackgroundColor(Color.parseColor("#4CBB17"));
                                            }
                                        }
                                    }
                                }
                            });
                            item.setTag(inv.getInvitation_id());
                            layout.addView(item);
                        }
                        Button resButton = (Button) findViewById(R.id.responses);
                        resButton.setBackgroundColor(Color.GRAY);
                    }
                    else{
                        for(Invitation inv : userProf.getInvitations()) {
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
                            String dText = location;
                            details.setText(dText);
                            item.setTag(inv.getInvitation_id());
                            layout.addView(item);
                        }
                        Button invButton = (Button) findViewById(R.id.activeInv);
                        invButton.setBackgroundColor(Color.GRAY);
                    }


                }
            }
        });



    }
}