package com.example.homeshare_application_team46;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostInvitation extends AppCompatActivity {

    private EditText mPropName,mAddress,mPrice, mNumBeds, mNumBaths, mDate;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Button btnSubmit;
    private static final String TAG= "AddToDatabase";
    private FirebaseAuth mAuth;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_invitation);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Invitations");
        String key = database.getReference("Invitations").push().getKey();

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        mPropName = (EditText) findViewById(R.id.etName);
        mAddress = (EditText) findViewById(R.id.etAddress);
        mPrice = (EditText) findViewById(R.id.etPrice);
        mNumBeds = (EditText) findViewById(R.id.etNumBeds);
        mNumBaths = (EditText) findViewById(R.id.etNumBaths);
        mDate = (EditText) findViewById(R.id.etDate);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

         // Create the user that's logged in
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        System.out.println("Main Activity user: " + user.getUid());
        userID = user.getUid();


        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Fields inputted by user
                String propName = mPropName.getText().toString();
                String address = mAddress.getText().toString();
                String Date = mDate.getText().toString();
                String key = database.getReference("Users").push().getKey();

                Integer price = 0;
                try {
                    price = Integer.parseInt(mPrice.getText().toString());
                } catch (NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                Integer numBeds = 0;
                try {
                    numBeds = Integer.parseInt(mNumBeds.getText().toString());
                } catch (NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                Integer numBaths = 0;
                try {
                    numBaths = Integer.parseInt(mNumBaths.getText().toString());
                } catch (NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }

                addToDatabase(key, propName, Date, price, address, numBeds, numBaths );


            }
        });

    }




    /**
     * customizable toast
     *@parammessage
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    public void addToDatabase(String key, String propName, String date_and_time, Integer price, String address, Integer num_bdrm, Integer num_bath) {
        //handle the exception if the EditText fields are null
        // TODO: remove userID from user, make sure username is unique
        if (!propName.equals("") && !date_and_time.equals("") && !address.equals("") && !(price==null) && !(num_bdrm==null) && !(num_bath==null)){
            Invitation newInvitation = new Invitation(userID, propName, key, date_and_time, price, address, num_bdrm, num_bath);
            myRef.child(key).setValue(newInvitation);
            toastMessage("Info has been saved!");
            mPropName.setText("");
            mAddress.setText("");
            mPrice.setText("");
            mNumBeds.setText("");
            mNumBaths.setText("");
            mDate.setText("");

            // Send intent to Main Activity
            Intent intent = new Intent(PostInvitation.this, MainActivity.class);
            startActivity(intent);

        }else {
                toastMessage("Fill out all the fields loser");

        }
    }

}