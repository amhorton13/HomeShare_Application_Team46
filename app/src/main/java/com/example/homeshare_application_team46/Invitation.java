package com.example.homeshare_application_team46;

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
    public int getPrice(){return price;}
    public String getAddress(){return address;}
    public int getNum_bdrm(){return num_bdrm;}
    public int getNum_bath(){return num_bath;}

    public void post(){

    }

    public void delete(){

    }

}
