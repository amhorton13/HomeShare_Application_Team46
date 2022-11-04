package com.example.homeshare_application_team46;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Users");
        String key = database.getReference("Users").push().getKey();
//        myRef = database.getReference().child("message");
        //        myRef.setValue("inside of register");

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        mUserName = (EditText) findViewById(R.id.etName);
        mEmail = (EditText) findViewById(R.id.etEmail);
        mPassword = (EditText) findViewById(R.id.etPassword);
        mAge = (EditText) findViewById(R.id.etAge);
        mBio = (EditText) findViewById(R.id.etBio);




        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Fields inputted by user
                String userName = mUserName.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                Integer age = 0;
                try {
                    age = Integer.parseInt(mAge.getText().toString());
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                String bio = mBio.getText().toString();

                // Boolean if duplicate userName



                // Reading info from database and checking if there are duplicate userName
                Integer finalAge = age;
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Invitation> users = new ArrayList<>();
                        System.out.println("VALUE FROM DATA" + snapshot.getChildren());
                        for (DataSnapshot x: snapshot.getChildren()) {
                            System.out.println("FIRST iterating:  " + x.getValue());
                            for (DataSnapshot t: x.getChildren()) {
                                System.out.println("SECOND iterating:  " + t.getValue());
                                if (t.getKey().equals("username")) {
                                    System.out.println("  SAME KEY USER   " );
                                    if (t.getValue().equals(userName)) {
                                        System.out.println("duplicate ALERT");
                                        isNameDup = true;
                                    }
                                }
                            }
                        }

                        if (isNameDup == false) {
                            addToDatabase(userName, email, password, finalAge, bio, key);
                        }
                        else {
                            toastMessage("UserName already in use dummy, try again");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Log.d("Reading ing", "Failed to read in value");
                    }
                });



                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "name: " + userName + "\n" +
                        "email: " + email + "\n" +
                        "password: " + password + "\n" +
                        "age: " + age + "\n" +
                        "bio: " + bio + "\n"
                );

            }
        });


    }
    public void addToDatabase(String userName, String email, String password, Integer age, String bio, String key) {
        //handle the exception if the EditText fields are null
        // TODO: remove userID from user, make sure username is unique
        if(!userName.equals("") && !email.equals("") && !password.equals("") && !(age==null) && !bio.equals("")){
            User userInformation = new User(email,userName,password, age, bio);
            myRef.child(key).setValue(userInformation);
            toastMessage("Info has been saved!");
            mUserName.setText("");
            mEmail.setText("");
            mPassword.setText("");
            mAge.setText("");
            mBio.setText("");
        }else{
            toastMessage("Fill out all the fields loser");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


}
