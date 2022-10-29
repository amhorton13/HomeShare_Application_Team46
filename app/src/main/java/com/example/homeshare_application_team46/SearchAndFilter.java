package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;

public class SearchAndFilter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_filter);

        Spinner spinnerSchoolYearFilter=findViewById(R.id.spinner_schoolyear_filter);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.schoolYears, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerSchoolYearFilter.setAdapter(adapter);
    }

    public void applyFiltersHandler(View view){
        /* TODO: update the filters attached to the main activity feed so that
            after intent returns to main page the feed is filtered */

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}