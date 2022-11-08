package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();
        TextView username = (TextView) findViewById(R.id.etName);
        username.setText(intent.getStringExtra("username"));

        TextView email = (TextView) findViewById(R.id.etEmail);
        email.setText(intent.getStringExtra("email"));

        TextView password = (TextView) findViewById(R.id.etPassword);
        password.setText(intent.getStringExtra("username"));

        TextView age = (TextView) findViewById(R.id.etAge);
        age.setText(Integer.toString(intent.getIntExtra("age", 0)));

        TextView bio = (TextView) findViewById(R.id.etBio);
        bio.setText(intent.getStringExtra("bio"));
    }

    public void submitChanges(View view){

    }

}