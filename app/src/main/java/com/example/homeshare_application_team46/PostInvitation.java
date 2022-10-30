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

public class PostInvitation extends AppCompatActivity {

    String propertyName;
    String num_bdrm;
    String num_bath;
    String rent_price;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_invitation);

        Spinner spinnerSchoolYear=findViewById(R.id.spinner_schoolyear);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.schoolYears, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerSchoolYear.setAdapter(adapter);

        EditText nameEditText = (EditText) findViewById(R.id.names);
        propertyName = nameEditText.getText().toString();

        // TODO: handle radio button value to get the gender


        EditText bdrmEditText = (EditText) findViewById(R.id.num_bdrm);
        num_bdrm = bdrmEditText.getText().toString();

        EditText bathEditText = (EditText) findViewById(R.id.num_bath);
        num_bath = bathEditText.getText().toString();

        EditText rentEditText = (EditText) findViewById(R.id.rent_price);
        rent_price = rentEditText.getText().toString();

        EditText addressEditText = (EditText) findViewById(R.id.address);
        address = addressEditText.getText().toString();


    }

    public void radioButtonHandler(View view){
        //figure out how to find which radio button is checked
    }

    public void postInvitationHandler(View view){
        /* TODO: After posting the invite, update the data structure holding all the invites,
            so that after the intent sends the app back to the main feed, the new post will be included */

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("propertyname", propertyName);
        intent.putExtra("numberbedrooms", num_bdrm);
        intent.putExtra("numberbaths", num_bath);
        intent.putExtra("rent", rent_price);
        intent.putExtra("address", address);
        //TODO: add extra for gender when radio button is figured out

        startActivity(intent);

    }

}