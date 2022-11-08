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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InvitationDetails extends AppCompatActivity {

    String invID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_details);
        /* TODO: set the correct values for the invitation details frontend */
        Intent intent = getIntent();
        invID = intent.getStringExtra("inviteID");
        /* TODO: Query DB using invID to get the other information and set the value  */

        TextView propNameDetails = (TextView) findViewById(R.id.property_name);
//        String propertyNameString = curr_inv.getPropName();
//        propNameDetails.setText(propertyNameString);

        TextView numbdrmDetails = (TextView) findViewById(R.id.num_bdrms_inv_details);
//        String numbdrmString = String.valueOf(curr_inv.getNum_bdrm());
//        numbdrmDetails.setText("Number of Bedrooms: " + numbdrmString);

        TextView numbathDetails = (TextView) findViewById(R.id.num_baths_inv_details);
//        String numbathString = String.valueOf(curr_inv.getNum_bath());
//        numbathDetails.setText("Number of Baths: " + numbathString);

        TextView rentpriceDetails = (TextView) findViewById(R.id.rent_price_inv_details);
//        String rentpriceString = String.valueOf(curr_inv.getPrice());
//        rentpriceDetails.setText("Rent per month: " + rentpriceString);

        TextView addDetails = (TextView) findViewById(R.id.address_inv_details);
        //String addString = String.valueOf(curr_inv.getAddress());
        //addDetails.setText("Address: " + addString);

        displayInvitedetails(invID, propNameDetails, numbdrmDetails, numbathDetails, rentpriceDetails, addDetails);
    }

    public void responseHandler(View view){
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Response response_to_add = new Response(userID, false, false);
        //addResponseToInvitation(invID, response_to_add);

        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }

//    private void addResponseToInvitation(String invID, Response response_to_add) {
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();;
//        //Invitation temp = new Invitation();
//        mDatabase.child("Invitations").child(invID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                }
//                else {
//                    propNameDetails.setText((String)task.getResult().child("prop_name").getValue());
//                    propNameDetails.setText((String)task.getResult().child("price").getValue());
//                    addDetails.setText((String)task.getResult().child("address").getValue());
//                    numbdrmDetails.setText((String)task.getResult().child("num_bdrm").getValue());
//                    numbathDetails.setText((String)task.getResult().child("num_bath").getValue());
//                }
//            }
//        });
//
//    }

    public void displayInvitedetails(String invID, TextView propNameDetails, TextView numbdrmDetails, TextView numbathDetails, TextView rentpriceDetails, TextView addDetails){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();;
        //Invitation temp = new Invitation();
        mDatabase.child("Invitations").child(invID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    propNameDetails.setText((String)task.getResult().child("prop_name").getValue());
                    rentpriceDetails.setText((String)task.getResult().child("price").getValue());
                    addDetails.setText((String)task.getResult().child("address").getValue());
                    numbdrmDetails.setText((String)task.getResult().child("num_bdrm").getValue());
                    numbathDetails.setText((String)task.getResult().child("num_bath").getValue());
                }
            }
        });

    }
}