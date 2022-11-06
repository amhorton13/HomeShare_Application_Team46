package com.example.homeshare_application_team46;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class RegisterPage extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Button btnSubmit;
    private EditText mUserName,mEmail,mPassword, mAge, mBio;
    private String userID = "test";
    private String message;
    private static final String TAG = "AddToDatabase";
    public Boolean isNameDup = false;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Users");
        String key = database.getReference("Users").push().getKey();

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        mUserName = (EditText) findViewById(R.id.etName);
        mEmail = (EditText) findViewById(R.id.etEmail);
        mPassword = (EditText) findViewById(R.id.etPassword);
        mAge = (EditText) findViewById(R.id.etAge);
        mBio = (EditText) findViewById(R.id.etBio);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Fields inputted by user
                String userName = mUserName.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String bio = mBio.getText().toString();
                Integer age = 0;
                try {
                    age = Integer.parseInt(mAge.getText().toString());
                } catch (NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                isNameDup = false;

                createUser(email, password, userName, age, bio);


            }
        });

    }
    public void addToDatabase(String userID, String userName, String email, String password, Integer age, String bio) {
        //handle the exception if the EditText fields are null
        // TODO: remove userID from user, make sure username is unique
        if (!isNameDup && !userName.equals("") && !email.equals("") && !password.equals("") && !(age==null) && !bio.equals("")){
            User userInformation = new User(email,userName,password, age, bio);
            myRef.child(userID).setValue(userInformation);
            toastMessage("Info has been saved!");
            mUserName.setText("");
            mEmail.setText("");
            mPassword.setText("");
            mAge.setText("");
            mBio.setText("");

            // Send intent to Main Activity
            Intent intent = new Intent(RegisterPage.this, LoginPage.class);
            startActivity(intent);

        }else {
            if (isNameDup) {
                toastMessage("UserName already in use dummy, try again");
            }
            else {
                toastMessage("Fill out all the fields loser");
            }

        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    public void createUser(String email, String password, String userName, Integer age, String bio) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            addToDatabase(user.getUid(), userName, email, password, age, bio );


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());


                        }
                    }
                });
    }



}
