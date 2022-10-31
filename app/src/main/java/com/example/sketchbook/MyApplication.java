package com.example.sketchbook;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.lights.LightState;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// MyApplication is used to call and edit the list of Sketchbooks
// Mainly used for the recycler list

public class MyApplication extends Application {

    // Initiate variables:
    // list of sketchbooks
    private static List<Sketchbook> sketchbookList = new ArrayList<Sketchbook>();

    // next Id number to track the list
    private static int nextId = 4;


    public MyApplication() {
        fillSketchbookList();
    }

    // Create temporary list items (all but one example will be erased later)
    private void fillSketchbookList() {
        Sketchbook s0 = new Sketchbook(0, "Doodles", "4/20/2022", "Random doodles", R.drawable.aj);
        Sketchbook s1 = new Sketchbook(1, "Art Class", "4/19/2022", "Class work", R.drawable.eye);
        Sketchbook s2 = new Sketchbook(2, "Car ride", "9/15/2021", "Car ride drawings", R.drawable.eyeball);
        Sketchbook s3 = new Sketchbook(3, "Ideas", "1/12/2021", "Some random Ideas", R.drawable.pika);

        // Add to the list
        sketchbookList.addAll(Arrays.asList(new Sketchbook[] {s0, s1, s2, s3}));
    }

    // getter - list
    public static List<Sketchbook> getSketchbookList() {
        return sketchbookList;
    }

    // setter - list
    public static void setSketchbookList(List<Sketchbook> sketchbookList) {
        MyApplication.sketchbookList = sketchbookList;
    }

    // getter - Id
    public static int getNextId() {
        return nextId;
    }

    // setter - Id
    public static void setNextId(int nextId) {
        MyApplication.nextId = nextId;
    }
}
