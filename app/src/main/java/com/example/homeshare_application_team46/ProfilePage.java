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

        //Test Data
        User testUser = new User("1", "JamesHarris@usc.edu", "Jameswah", "i like cs", 12, "idk");
        User testUser2 = new User("2", "jj@usc.edu", "jjVal", "peepeepoopoo", 43, "milfs");

        testUser.addInvitation(new Invitation(testUser, 1, new Date(), 1500, "USC", 2, 2));
        testUser.addInvitation(new Invitation(testUser, 2, new Date(), 2000, "Orchard", 1, 1));
        testUser.addResponse(new Invitation(testUser2, 3, new Date(), 2300, "The Moon", 4, 2));

        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
        LayoutInflater li = LayoutInflater.from(this);
        Intent intent = getIntent();
        if(intent.getExtras() != null && intent.getStringExtra("profileSetting").equals("responses")) {
            for(Invitation inv : testUser.getResponses()) {
                TextView tv = (TextView) li.inflate(R.layout.feed_item, layout, false);
                String text = inv.getNum_bdrm() + " Bed " + inv.getNum_bath() + " Bath near " + inv.getLocation() + " for $" + inv.getPrice();
                text += " by " + inv.getPoster().getUsername();
                tv.setText(text);
                layout.addView(tv);
            }
            Button resButton = (Button) findViewById(R.id.responses);
            resButton.setBackgroundColor(Color.GRAY);
        }
        else{
            for(Invitation inv : testUser.getInvitations()) {
                TextView tv = (TextView) li.inflate(R.layout.feed_item, layout, false);
                String text = inv.getNum_bdrm() + " Bed " + inv.getNum_bath() + " Bath near " + inv.getLocation() + " for $" + inv.getPrice();
                tv.setText(text);
                layout.addView(tv);
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
    public void openInvite(View view){
        Intent intent = new Intent(this, InvitationDetails.class);
        intent.putExtra("inviteID", 1);
        startActivity(intent);
    }
}