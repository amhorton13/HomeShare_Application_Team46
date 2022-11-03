package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
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

        //Example Data
        User testUser = new User("1", "JamesHarris@usc.edu", "Jameswah", "i like cs", 12, "idk");
        testUser.addInvitation(new Invitation(testUser, 1, new Date(), 1500, "USC", 2, 2));
        testUser.addInvitation(new Invitation(testUser, 2, new Date(), 2000, "Orchard", 1, 1));
        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollLayout);

        LayoutInflater li = LayoutInflater.from(this);
        for (int i = 0; i<=6; i++) {
            TextView tv = (TextView) li.inflate(R.layout.feed_item, layout, false);
            tv.setText("Please work " + i);

            layout.addView(tv);
        }


    }

    public void showInvites(){

    }

    public void showResponses(){

    }
}