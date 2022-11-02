package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

    }
}