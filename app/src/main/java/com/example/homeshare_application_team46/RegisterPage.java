package com.example.homeshare_application_team46;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("message");
        //        for (int i = 0; i<10;i++) {
//            String id = i+"";
//            myRef.child("demo").child(id).setValue("TEST");
//        }
        User testUser = new User("1", "JamesHarris@usc.edu", "Jameswah", "i like cs", 12, "idk");
        String age = "age";
//        myRef.child(testUser.getUser_id()).child(age).setValue(testUser.getAge());

        myRef.setValue("inside of register bitches");
    }
}
