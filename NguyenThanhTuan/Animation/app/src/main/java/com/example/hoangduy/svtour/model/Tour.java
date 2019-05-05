package com.example.hoangduy.svtour.model;

import java.io.Serializable;

/**
 * Created by HoangDuy on 5/8/2018.
 */

public class Tour implements Serializable{

    private int tour_id;
    private String tour_name;
    private String tour_date;
    private byte[] tour_avatar;


    public Tour(int tour_id, String tour_name, String tour_date, byte[] tour_avatar) {
        this.tour_id = tour_id;
        this.tour_name = tour_name;
        this.tour_date = tour_date;
        this.tour_avatar = tour_avatar;
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public String getTour_name() {
        return tour_name;
    }

    public void setTour_name(String tour_name) {
        this.tour_name = tour_name;
    }

    public String getTour_date() {
        return tour_date;
    }

    public void setTour_date(String tour_date) {
        this.tour_date = tour_date;
    }

    public byte[] getTour_avatar() {
        return tour_avatar;
    }

    public void setTour_avatar(byte[] tour_avatar) {
        this.tour_avatar = tour_avatar;
    }
}
