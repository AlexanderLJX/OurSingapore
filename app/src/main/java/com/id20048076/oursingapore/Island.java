package com.id20048076.oursingapore;

import java.io.Serializable;

public class Island implements Serializable {
    private int id;
    private String name;
    private String description;
    private int area;
    private float stars;

    public Island(int id, String name, String description, int area, float stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
        this.stars = stars;
    }
    public Island(String name, String description, int area, float stars) {
        this.name = name;
        this.description = description;
        this.area = area;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
