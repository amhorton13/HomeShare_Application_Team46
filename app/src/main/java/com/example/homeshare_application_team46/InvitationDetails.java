package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        Invitation curr_inv = Invitation.queryInvitation(invID);

        TextView propNameDetails = (TextView) findViewById(R.id.property_name);
        String propertyNameString = curr_inv.getPropName();
        propNameDetails.setText(propertyNameString);

        TextView numbdrmDetails = (TextView) findViewById(R.id.num_bdrms_inv_details);
        String numbdrmString = String.valueOf(curr_inv.getNum_bdrm());
        numbdrmDetails.setText("Number of Bedrooms: " + numbdrmString);

        TextView numbathDetails = (TextView) findViewById(R.id.num_baths_inv_details);
        String numbathString = String.valueOf(curr_inv.getNum_bath());
        numbathDetails.setText("Number of Baths: " + numbathString);

        TextView rentpriceDetails = (TextView) findViewById(R.id.rent_price_inv_details);
        String rentpriceString = String.valueOf(curr_inv.getPrice());
        rentpriceDetails.setText("Rent per month: " + rentpriceString);

        TextView addDetails = (TextView) findViewById(R.id.address_inv_details);
        String addString = String.valueOf(curr_inv.getAddress());
        rentpriceDetails.setText("Address: " + addString);
    }

    public void responseHandler(View view){
        //TODO: Add the current user to the responses of the current invite
        //After responding to an invite, app goes to profile page to see all invites user has responded to.
        Invitation curr_inv = Invitation.queryInvitation(invID);
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Response response_to_add = new Response(userID, false, false);
        curr_inv.addResponse(response_to_add);

        //TODO: update the same invitation in the databases now that the response array has been updated

        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }
}