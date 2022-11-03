package com.example.homeshare_application_team46;

import java.util.ArrayList;

public class User {

    private String user_id;
    private String email;
    private String username;
    private String password;
    private int age;
    private String biography;
    private ArrayList<String> user_interests;

    private ArrayList<Invitation> user_invitations;
    private ArrayList<Invitation> invitations_responded_to;

    public User(String user_id, String email, String username, String password, int age, String biography){
        this.user_id = user_id;
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

    public String getBEmail() {
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

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
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
}

