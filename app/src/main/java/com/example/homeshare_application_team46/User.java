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

    public User(){
        user_interests = new ArrayList<>();
        user_invitations = new ArrayList<>();
        invitations_responded_to = new ArrayList<>();
    }

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
        final Object[] email = {null};
        final Object[] password = new Object[1];
        final Object[] age = new Object[1];
        final Object[] bio = new Object[1];
        final Object[] username = new Object[1];
        final User[] loggedInUser = new User[1];
        mDatabase.child("Users").child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                        email[0] = task.getResult().child("email").getValue();
                        password[0] = task.getResult().child("password").getValue();
                        age[0] = task.getResult().child("age").getValue();
                        bio[0] = task.getResult().child("biography").getValue();
                        username[0] = task.getResult().child("username").getValue();
                        returnUser(email[0].toString(), username[0].toString(), password[0].toString(), (Math.toIntExact((Long) age[0])), bio[0].toString());
                }
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static User returnUser(String email, String username, String password, int age, String biography) {
        return new User(email,username, password, age, biography);
    }
}

