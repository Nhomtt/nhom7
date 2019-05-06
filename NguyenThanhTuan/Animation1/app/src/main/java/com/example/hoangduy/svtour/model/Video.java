package com.example.hoangduy.svtour.model;

import java.io.Serializable;

/**
 * Created by HoangDuy on 5/9/2018.
 */

public class Video implements Serializable {

    private int video_id;
    private int tour_id;
    private String video_url;

    public Video(int video_id, int tour_id, String video_url) {
        this.video_id = video_id;
        this.tour_id = tour_id;
        this.video_url = video_url;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
