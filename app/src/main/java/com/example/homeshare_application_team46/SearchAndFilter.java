package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;

public class SearchAndFilter extends AppCompatActivity {

    String min_rent;
    String max_rent;
    String min_bdrm;
    String max_bdrm;
    String min_bath;
    String max_bath;
    String min_age;
    String max_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_filter);

        EditText minrentEditText = (EditText) findViewById(R.id.priceMin);
        min_rent = minrentEditText.getText().toString();

        EditText maxrentEditText = (EditText) findViewById(R.id.priceMax);
        max_rent = maxrentEditText.getText().toString();

        EditText minbdrmEditText = (EditText) findViewById(R.id.bdrmMin);
        min_bdrm = minbdrmEditText.getText().toString();

        EditText maxbdrmEditText = (EditText) findViewById(R.id.bdrmMax);
        max_bdrm = maxbdrmEditText.getText().toString();

        EditText minbathEditText = (EditText) findViewById(R.id.bathMin);
        min_bath = minbathEditText.getText().toString();

        EditText maxbathEditText = (EditText) findViewById(R.id.bathMax);
        max_bath = maxbathEditText.getText().toString();

        EditText minageEditText = (EditText) findViewById(R.id.ageMin);
        min_age = minageEditText.getText().toString();

        EditText maxageEditText = (EditText) findViewById(R.id.ageMax);
        max_age = maxageEditText.getText().toString();
    }

    public void applyFiltersHandler(View view){
        /* TODO: update the extras (filters) attached to the main activity feed so that
            after intent returns to main page the extras can be used to filter invites */

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("filtering", true);

        intent.putExtra("maxbedrooms", max_bdrm);
        intent.putExtra("minbedrooms", min_bdrm);
        intent.putExtra("minbaths", min_bath);
        intent.putExtra("maxbaths", max_bath);
        intent.putExtra("minrent", min_rent);
        intent.putExtra("maxrent", max_rent);
        intent.putExtra("minage", min_age);
        intent.putExtra("maxage", max_age);

        startActivity(intent);
    }
}