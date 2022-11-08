package com.example.homeshare_application_team46;

public class Response {
    private String userID;
//    private int response_id;
//    private Invitation invite;
    private boolean accepted;
    private boolean declined;

    public Response(String userID, boolean accepted, boolean declined){
        this.userID = userID;
        this.accepted = accepted;
        this.declined = declined;
    }

    public String getUser() {
        return userID;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setDeclined(boolean declined) {
        this.declined = declined;
    }
}
