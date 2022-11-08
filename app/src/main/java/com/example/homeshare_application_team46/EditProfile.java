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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        TextView username = (TextView) findViewById(R.id.etName);
        TextView email = (TextView) findViewById(R.id.etEmail);
        TextView password = (TextView) findViewById(R.id.etPassword);
        TextView age = (TextView) findViewById(R.id.etAge);
        TextView bio = (TextView) findViewById(R.id.etBio);
        queryUser(userID, username, email, password, age, bio);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> result = new HashMap<>();
                result.put("username", username.getText().toString());
                result.put("email", email.getText().toString());
                result.put("password", password.getText().toString());
                result.put("age", Integer.parseInt(age.getText().toString()));
                result.put("bio", bio.getText().toString());
                mDatabase.child("Users").child(userID).updateChildren(result);

                Intent intent = new Intent(EditProfile.this, ProfilePage.class);
                startActivity(intent);

            }
        });

    }

    public void queryUser(String userID, TextView username, TextView email, TextView password, TextView age, TextView bio) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        User temp = new User();
        mDatabase.child("Users").child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    username.setText((String) task.getResult().child("username").getValue());
                    email.setText((String) task.getResult().child("email").getValue());
                    password.setText((String) task.getResult().child("password").getValue());
                    age.setText( "4");
                    System.out.println(task.getResult().child("age").getValue().getClass());
                    bio.setText((String) task.getResult().child("biography").getValue());

                }

            }
        });

    }
}