package com.example.homeshare_application_team46;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef;
    private Button btnSubmit;
    private EditText mUserName,mEmail,mPassword, mAge, mBio;
    private String userID = "10";
    private String message;
    private static final String TAG = "AddToDatabase";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Users");
//        userRef = myRef.child("Users");
//        myRef = database.getReference().child("message");

//
//
        User testUser = new User("1", "JamesHarris@usc.edu", "Jameswah", "i like cs", 12, "idk");
        String age = "age";
//        myRef.child(testUser.getUser_id()).child(age).setValue(testUser.getAge());

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
                String name = mUserName.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                Integer age = 0;
                try {
                    age = Integer.parseInt(mAge.getText().toString());
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                String bio = mBio.getText().toString();

                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "name: " + name + "\n" +
                        "email: " + email + "\n" +
                        "password: " + password + "\n" +
                        "age: " + age + "\n" +
                        "bio: " + bio + "\n"
                );

                //handle the exception if the EditText fields are null
                // TODO
                if(!name.equals("") && !email.equals("") && !password.equals("") && !(age==null)){
                    User userInformation = new User(userID, email,name,password, age, bio);
                    myRef.child(userID).setValue(userInformation);
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
        });
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
