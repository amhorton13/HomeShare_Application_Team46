package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import org.w3c.dom.Text;

public class ProfilePage extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private User userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Intent intent = getIntent();
        //TODO: Get User from intent
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userProfile = User.queryUser(userID);

        if(!intent.getStringExtra("user").equals(userProfile.getUsername())){
            Button editProf = (Button) findViewById(R.id.editProfile);
            ((ViewGroup) editProf.getParent()).removeView(editProf);
        }
        System.out.println("MADE IT HERE");
        if(userProfile == null) System.out.println("NULLLLLLLLLLLLL");
        else {
            System.out.println("USERNAME: " + userProfile.getUsername());
            TextView nameView = (TextView) findViewById(R.id.username);
            nameView.setText(userProfile.getUsername());
        }

        //Test Data
        User testUser = new User("JamesHarris@usc.edu", "Jameswah", "i like cs", 12, "idk");
        User testUser2 = new User("jj@usc.edu", "jjVal", "peepeepoopoo", 43, "milfs");

        testUser.addInvitation(new Invitation("testUser", "minn", "1", "popo", 1500, "USC", 2, 2));
        testUser.addInvitation(new Invitation("testUser", "jkdnfjks", "2", "date", 2000, "Orchard", 1, 1));
        testUser.addResponse(new Invitation("testUser2", "jks","3", "date", 2300, "The Moon", 4, 2));

        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);
        LayoutInflater li = LayoutInflater.from(this);
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
                String location = inv.getAddress();
                String poster = "inv.getPoster().getUsername()";
                String dText = location + " by " + poster;
                details.setText(dText);
                item.setTag(inv.getInvitation_id());
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

    public void showInvites(View view){
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("profileSetting", "invites");
        startActivity(intent);
    }

    public void editProfile(View view){
        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("username", userProfile.getUsername());
        intent.putExtra("age", userProfile.getAge());
        intent.putExtra("password", userProfile.getPassword());
        intent.putExtra("bio", userProfile.getBiography());
        intent.putExtra("email", userProfile.getEmail());
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
        intent.putExtra("inviteID", (String) view.getTag());
        startActivity(intent);
    }
}