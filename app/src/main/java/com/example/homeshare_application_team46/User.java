package com.example.homeshare_application_team46;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User {


    private String email;
    private String username;
    private String password;
    private int age;
    private String biography;
    private ArrayList<String> user_interests;

    private ArrayList<Invitation> user_invitations;
    private ArrayList<Invitation> invitations_responded_to;

    public User(String email, String username, String password, int age, String biography){

        this.email = email;
        this.username = username;
        this.password = password;
        this.age = age;
        this.biography = biography;
        user_interests = new ArrayList<>();
        user_invitations = new ArrayList<>();
        invitations_responded_to = new ArrayList<>();
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBiography() {
        return biography;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }



    public void setUser_interests(ArrayList<String> user_interests) {
        this.user_interests = user_interests;
    }

    public ArrayList<String> getUser_interests() {
        return user_interests;
    }

    public void addInvitation(Invitation invite){
        user_invitations.add(invite);
    }

    public ArrayList<Invitation> getInvitations() {return user_invitations;}

    public void addResponse(Invitation invite){
        invitations_responded_to.add(invite);
    }

    public ArrayList<Invitation> getResponses() {return invitations_responded_to;}

    public static User queryUser(String userID){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();;
        mDatabase.child("Users").child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    //Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    String email;
                    String username;
                    String password;
                    int age;
                    String biography;
                    try {
                        for (DataSnapshot snap : task.getResult().getChildren()) {
                            if (snap.getKey().equals("age")) age = (int) snap.getValue();
                            else if (snap.getKey().equals("biography"))
                                biography = (String) snap.getValue();
                            else if (snap.getKey().equals("email"))
                                email = (String) snap.getValue();
                            else if (snap.getKey().equals("password"))
                                password = (String) snap.getValue();
                            else if (snap.getKey().equals("username"))
                                username = (String) snap.getValue();
                        }

                    }catch(NullPointerException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        return null;
    }
}

