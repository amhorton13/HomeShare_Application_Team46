package com.example.homeshare_application_team46;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Deque;
import java.util.HashMap;

public class InvitationDetails extends AppCompatActivity {

    String invID;
    String curr_userID;
    Boolean ownProfile = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_details);

        /* TODO: set the correct values for the invitation details frontend */
        Intent intent = getIntent();
        invID = intent.getStringExtra("inviteID");

        /* TODO: Query DB using invID to get the other information and set the value  */
        System.out.println("INV ID INSIDE OF INV DETAILS:  " + invID);
        TextView propNameDetails = (TextView) findViewById(R.id.etName);
        TextView addDetails = (TextView) findViewById(R.id.etAddress);
        TextView rentpriceDetails = (TextView) findViewById(R.id.etPrice);
        TextView numbdrmDetails = (TextView) findViewById(R.id.etNumBeds);
        TextView numbathDetails = (TextView) findViewById(R.id.etNumBaths);
        TextView dateDetails = (TextView) findViewById(R.id.etDate);
        TextView btnSubmit = (TextView) findViewById(R.id.respondButton);
        curr_userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        displayInvitedetails(invID, propNameDetails, numbdrmDetails, numbathDetails, rentpriceDetails, addDetails, dateDetails, btnSubmit);
    }



    public void displayInvitedetails(String invID, TextView propNameDetails, TextView numbdrmDetails, TextView numbathDetails, TextView rentpriceDetails, TextView addDetails, TextView dateDetails, TextView btnSubmit){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();;
        //Invitation temp = new Invitation();
        mDatabase.child("Invitations").child(invID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    /* the following lines are just setting all the text views to contain correct information */
                    propNameDetails.setText((String) task.getResult().child("propName").getValue());
                    rentpriceDetails.setText(Integer.toString(Math.toIntExact((Long) task.getResult().child("price").getValue())));
                    addDetails.setText((String) task.getResult().child("address").getValue());
                    numbdrmDetails.setText(Integer.toString(Math.toIntExact((Long) task.getResult().child("num_bdrm").getValue())));
                    numbathDetails.setText(Integer.toString(Math.toIntExact((Long) task.getResult().child("num_bath").getValue())));
                    dateDetails.setText((String) task.getResult().child("date_and_time").getValue());

                    /* Get the useID from the invitation in the data base and compare it to current userID (data member) */

                    if (task.getResult().child("poster").getValue().equals(curr_userID)) {
                        btnSubmit.setText("Show Current Responses");
                    }


                    btnSubmit.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            System.out.println("INSIDE OF BUTTON ONCLICK TASK VAL:  " + task.getResult().child("poster").getValue());
                            System.out.println("INSIDE OF BUTTON ONCLICK USEERID:  " + curr_userID);
                            System.out.println("INSIDE OF BUTTON ONCLICK USEERID:  " + task.getResult().child("poster").getValue() == curr_userID);
                            // Firebase instance
                            if (task.getResult().child("poster").getValue().equals(curr_userID)) {
                                /* Go to responses page with intent */
                                //toastMessage("This is YOUR invite, going to responses.");
                                Intent intent = new Intent(InvitationDetails.this, ShowingResponses.class);
                                intent.putExtra("inviteID", invID);
                                startActivity(intent);
                            }
                            else {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference().child("Invitations").child(invID);
                                // New map to add
                                HashMap<String, Object> result = new HashMap<>();
                                result.put(curr_userID, false);
                                myRef.child("Responses").child(curr_userID).setValue(false);
                                // Call intent to profile
                                Intent intent = new Intent(InvitationDetails.this, ProfilePage.class);
                                intent.putExtra("user", curr_userID);
                                startActivity(intent);
                            }

                        }
                    });
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