package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostInvitation extends AppCompatActivity {

    private EditText mPropName,mAddress,mPrice, mNumBeds, mNumBaths, mDate;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Button btnSubmit;
    private static final String TAG= "AddToDatabase";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_invitation);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Users");
        String key = database.getReference("Users").push().getKey();

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        mPropName = (EditText) findViewById(R.id.etName);
        mAddress = (EditText) findViewById(R.id.etAddress);
        mPrice = (EditText) findViewById(R.id.etPrice);
        mNumBeds = (EditText) findViewById(R.id.etNumBeds);
        mNumBaths = (EditText) findViewById(R.id.etNumBaths);
        mDate = (EditText) findViewById(R.id.etDate);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Fields inputted by user
                String propName = mPropName.getText().toString();
                String address = mAddress.getText().toString();
                String price = mPrice.getText().toString();
                String numBeds = mNumBeds.getText().toString();
                String numBaths = mNumBaths.getText().toString();
                String Date = mDate.getText().toString();

//                Integer age = 0;
//                try {
//                    age = Integer.parseInt(mAge.getText().toString());
//                } catch (NumberFormatException nfe) {
//                    System.out.println("Could not parse " + nfe);
//                }
//                isNameDup = false;
//
//                createUser(email, password, userName, age, bio);


            }
        });

    }



//    public void postInvitationHandler(View view){
//        /*TODO: After posting the invite, update the data structure holding all the invites,
//so that after the intent sends the app back to the main feed, the new post will be included */
//
//        Intent intent = new Intent(this, MainActivity.class);
//
//        // added an action definition to the intent so main knows to add invite or filter
//        intent.putExtra("action", "postInvite");
//
//        intent.putExtra("propertyname", propertyName);
//        intent.putExtra("numberbedrooms", num_bdrm);
//        intent.putExtra("numberbaths", num_bath);
//        intent.putExtra("rent", rent_price);
//        intent.putExtra("address", address);
//        //TODO: add extra for gender when radio button is figured out
//
//        startActivity(intent);
//
//    }

    /**
     * customizable toast
     *@parammessage
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}