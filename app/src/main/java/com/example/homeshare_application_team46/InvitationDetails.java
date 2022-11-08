package com.example.homeshare_application_team46;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InvitationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_details);
        /* TODO: set the correct values for the invitation details frontend */
        Intent intent = getIntent();
        String invID = intent.getStringExtra("inviteID");
        /* TODO: Query DB using invID to get the other information and set the value  */
        Invitation curr_inv = Invitation.queryInvitation(invID);

        TextView propNameDetails = (TextView) findViewById(R.id.property_name);
        String propertyNameString = curr_inv.getPropName();
        propNameDetails.setText(propertyNameString);

        TextView numbdrmDetails = (TextView) findViewById(R.id.num_bdrms_inv_details);
        String numbdrmString = String.valueOf(curr_inv.getNum_bdrm());
        propNameDetails.setText(numbdrmString);

        TextView numbdrmDetails = (TextView) findViewById(R.id.num_bdrms_inv_details);
        String numbdrmString = String.valueOf(curr_inv.getNum_bdrm());
        propNameDetails.setText(numbdrmString);


    }

    public void responseHandler(){
        //TODO: Update data structure or database that has which invites a user has responded to

        //After responding to an invite, app goes to profile page to see all invites user has responded to.
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }
}