package com.example.homeshare_application_team46;

import java.util.ArrayList;
import java.util.Date;

public class Invitation {
    private User poster;
    private int invitation_id;
    private Date date_and_time;

    private int price;
    private String location;
    private int num_bdrm;
    private int num_bath;

    private ArrayList<Response> responses;

    public Invitation(User poster, int invitation_id, Date date_and_time, int price, String location, int num_bdrm, int num_bath){
        this.poster = poster;
        this.invitation_id = invitation_id;
        this.date_and_time = date_and_time;
        this.price = price;
        this.location = location;
        this.num_bdrm = num_bdrm;
        this.num_bath = num_bath;
    }

    public void post(){

    }

    public void delete(){

    }

}
