package com.example.hoangduy.svtour.model;

import java.io.Serializable;

/**
 * Created by HoangDuy on 5/9/2018.
 */

public class Image implements Serializable {

    private int image_id;
    private int tour_id;
    private byte[] image_byte;

    public Image(int image_id, int tour_id, byte[] image_byte) {
        this.image_id = image_id;
        this.tour_id = tour_id;
        this.image_byte = image_byte;
    }
    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public byte[] getImage_byte() {
        return image_byte;
    }

    public void setImage_byte(byte[] image_byte) {
        this.image_byte = image_byte;
    }
}
