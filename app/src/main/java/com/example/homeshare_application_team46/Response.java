package com.example.homeshare_application_team46;

public class Response {
    private User user;
    private int response_id;
    private Invitation invite;
    private boolean accepted;
    private boolean declined;

    public User getUser() {
        return user;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setDeclined(boolean declined) {
        this.declined = declined;
    }
}
