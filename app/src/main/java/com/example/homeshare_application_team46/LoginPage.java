package com.example.homeshare_application_team46;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class LoginPage  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Second activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

    }
    public void openRegister(View view){
        Intent intent = new Intent(view.getContext(), RegisterPage.class);
        startActivity(intent);
    }



}
