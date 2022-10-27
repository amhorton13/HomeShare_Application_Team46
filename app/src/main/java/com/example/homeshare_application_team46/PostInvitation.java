package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;

public class PostInvitation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_invitation);

        Spinner spinnerSchoolYear=findViewById(R.id.spinner_schoolyear);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.schoolYears, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerSchoolYear.setAdapter(adapter);
    }

}