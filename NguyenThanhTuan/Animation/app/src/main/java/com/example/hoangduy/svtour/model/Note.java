package com.example.hoangduy.svtour.model;

/**
 * Created by HoangDuy on 5/9/2018.
 */

public class Note {

    private int note_id;
    private int tour_id;
    private String note_title;
    private String note_description;

    public Note(int note_id, int tour_id, String note_title, String note_description) {
        this.note_id = note_id;
        this.tour_id = tour_id;
        this.note_title = note_title;
        this.note_description = note_description;
    }


    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_description() {
        return note_description;
    }

    public void setNote_description(String note_description) {
        this.note_description = note_description;
    }
}
