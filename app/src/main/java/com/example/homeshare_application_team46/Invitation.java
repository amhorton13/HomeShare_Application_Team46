package com.example.homeshare_application_team46;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class Invitation {
    private String poster;
    private String invitation_id;
    private String date_and_time;
    private String propName;
    private int price;
    private String address;
    private int num_bdrm;
    private int num_bath;

    private ArrayList<Response> responses;

    public Invitation(String poster, String propName, String invitation_id, String date_and_time, int price, String address, int num_bdrm, int num_bath){
        this.poster = poster;
        this.invitation_id = invitation_id;
        this.propName = propName;
        this.date_and_time = date_and_time;
        this.price = price;
        this.address = address;
        this.num_bdrm = num_bdrm;
        this.num_bath = num_bath;
    }

    public String getPoster(){ return poster;}
    public String getInvitation_id(){ return invitation_id;}
    public String getDate_and_time(){return date_and_time;}
    public java.lang.String getPropName() {return propName;}
    public int getPrice(){return price;}
    public String getAddress(){return address;}
    public int getNum_bdrm(){return num_bdrm;}
    public int getNum_bath(){return num_bath;}

    public void post(){

    }

    public void delete(){

    }
    public static Invitation queryInvitation(String invitation_id){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        final Object[] poster = {null};
        final Object[] propName = new Object[1];
        final Object[] invitationID = new Object[1];
        final Object[] date = new Object[1];
        final Object[] price = new Object[1];
        final Object[] address = new Object[1];
        final Object[] numBeds = new Object[1];
        final Object[] numBaths = new Object[1];

        mDatabase.child("Invitations").child(invitation_id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    //Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    poster[0] = task.getResult().child("poster").getValue();
                    propName[0] = task.getResult().child("password").getValue();
                    invitationID[0] = task.getResult().child("invitation_id").getValue();
                    date[0] = task.getResult().child("date_and_time").getValue();
                    price[0] = task.getResult().child("price").getValue();
                    address[0] = task.getResult().child("address").getValue();
                    numBeds[0] = task.getResult().child("num_bdrm").getValue();
                    numBaths[0] = task.getResult().child("num_bath").getValue();
                    returnInvitation(poster[0].toString(), propName[0].toString(), invitationID[0].toString(), date[0].toString(), (Math.toIntExact((Long) price[0])), address[0].toString(), (Math.toIntExact((Long) numBeds[0])), (Math.toIntExact((Long) numBaths[0])));

                }
            }
        });

        return null;
    }
    public static Invitation returnInvitation(String poster, String propName, String invitation_id, String date_and_time, int price, String address, int num_bdrm, int num_bath) {
        System.out.println(new Invitation(poster, propName, invitation_id, date_and_time, price, address, num_bdrm, num_bath));
        return new Invitation(poster, propName, invitation_id, date_and_time, price, address, num_bdrm, num_bath);
    }

}
