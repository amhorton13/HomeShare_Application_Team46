package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class ProfilePage extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //TODO: Query DB using username from intent, fill in information to be used by profile page

        //TODO: Get User from intent
        String username = "JamesHarris";
        TextView nameView = (TextView) findViewById(R.id.username);
        nameView.setText(username);

        //Test Data
        User testUser = new User("JamesHarris@usc.edu", "Jameswah", "i like cs", 12, "idk");
        User testUser2 = new User("jj@usc.edu", "jjVal", "peepeepoopoo", 43, "milfs");

        testUser.addInvitation(new Invitation(testUser, 1, new Date(), 1500, "USC", 2, 2));
        testUser.addInvitation(new Invitation(testUser, 2, new Date(), 2000, "Orchard", 1, 1));
        testUser.addResponse(new Invitation(testUser2, 3, new Date(), 2300, "The Moon", 4, 2));

        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
        LayoutInflater li = LayoutInflater.from(this);
        Intent intent = getIntent();
        if(intent.getExtras() != null && intent.getStringExtra("profileSetting").equals("responses")) {
            for(Invitation inv : testUser.getResponses()) {
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
                String location = inv.getLocation();
                String poster = inv.getPoster().getUsername();
                String dText = location + " by " + poster;
                details.setText(dText);
                layout.addView(item);
            }
            Button resButton = (Button) findViewById(R.id.responses);
            resButton.setBackgroundColor(Color.GRAY);
        }
        else{
            for(Invitation inv : testUser.getInvitations()) {
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
                String location = inv.getLocation();
                String dText = location;
                details.setText(dText);
                layout.addView(item);
            }
            Button invButton = (Button) findViewById(R.id.activeInv);
            invButton.setBackgroundColor(Color.GRAY);
        }

    }

    public void showInvites(View view){
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("profileSetting", "invites");
        startActivity(intent);
    }

    public void showResponses(View view){
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("profileSetting", "responses");
        startActivity(intent);
    }
    //TODO: figure out getting inviteID from view and adding to intent
    public void openInvitation(View view){
        Intent intent = new Intent(this, InvitationDetails.class);
        intent.putExtra("inviteID", 1);
        startActivity(intent);
    }
}