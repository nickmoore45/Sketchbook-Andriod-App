package com.example.sketchbook;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

// Sketchbook class is used for everything involved in the sketchbook
// TODO: add location!
public class Sketchbook {
    private Integer id;
    private String name;
    private String date;
    private String description;
    int imageResource;

    public Sketchbook(Integer id, String name, String date, String description, int imageResource) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.imageResource = imageResource;
    }

    @Override
    public String toString() {
        return "Sketchbook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", imageResource=" + imageResource +
                '}';
    }

// All of the getters and setters for each item within the class

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
