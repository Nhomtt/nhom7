package com.example.alarmdemo.model;

public class Group {
    public String name;
    public String id;
    public int image;

    public Group(String name, String id, int image) {
        this.name = name;
        this.id = id;
        this.image = image;
    }

    public Group(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
