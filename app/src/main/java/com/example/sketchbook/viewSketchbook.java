package com.example.sketchbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class viewSketchbook extends AppCompatActivity{

    // Initiate Sketchbook variables
    List<Sketchbook> sketchbookList;
    TextView sketchName, sketchDate, sketchDescription;
    ImageView sketchPic;
    int id;

    // Initiate Buttons
    FloatingActionButton backHomeButton;

    // Grab List
    MyApplication myApplication = (MyApplication) this.getApplication();

    // Swipe Variables
    ConstraintLayout constraintLayout;
    SwipeListener swipeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sketchbook);

        // Swipe Option
        constraintLayout = findViewById(R.id.constaint_layout);
        swipeListener = new SwipeListener(constraintLayout);

        // Back Button
        backHomeButton = findViewById(R.id.backHomeButton);

        // Initiate list
        sketchbookList = myApplication.getSketchbookList();

        // Initiate variables
        sketchName = findViewById(R.id.viewName);
        sketchDate = findViewById(R.id.viewDate);
        sketchDescription = findViewById(R.id.viewDescription);
        sketchPic = findViewById(R.id.viewImage);

        // get intent id that was passed
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        // set sketchbook to null before initiating it
        Sketchbook sketchbook = null;

        // for "each" loop that finds and sets correct sketchbook in the list
        for (Sketchbook s: sketchbookList) {
            if (s.getId() == id) {
                sketchbook = s;
            }
        }

        // Set the text to
        sketchName.setText(sketchbook.getName());
        sketchDate.setText(sketchbook.getDate());
        sketchDescription.setText(sketchbook.getDescription());
        sketchPic.setImageDrawable(getDrawable(sketchbook.getImageResource()));


        // Set up the back button listener
        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });

    }

    // Function that brings user home if called
    private void openHomePage() {
        Intent intent = new Intent(viewSketchbook.this, HomePage.class);
        startActivity(intent);
    }

    // Create the swipe class that tracks if the user swipe to the right
    private class SwipeListener implements View.OnTouchListener {
        //Initialize variable
        GestureDetector gestureDetector;

        // Create constructor
        SwipeListener(View view){
            // Initialize threshold value
            int threshold = 100;
            int velocity_threshold = 100;

            // Initialize simple listener
            GestureDetector.SimpleOnGestureListener listener =
                    new GestureDetector.SimpleOnGestureListener(){
                        @Override
                        public boolean onDown(MotionEvent e) {
                            // pass true value
                            return true;
                        }

                        @Override
                        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                            // get x and y difference
                            float xDiff = e2.getX() - e1.getX();
                            float yDiff = e2.getY() - e1.getY();
                            try {
                                // Check condition
                                if (Math.abs(xDiff) > Math.abs(yDiff)){
                                    //When x is greater than y
                                    //Check condition
                                    if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold){
                                        // When x diff is greater than threshold and when x velocity
                                        // is greater than velocity threshold
                                        // Check condition
                                        if (xDiff > 0){
                                            // When Swiped right
                                            openHomePage();
                                        }
                                        return true;
                                    }
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            return false;

                        }
                    };
            // Initialize gesture detector
            gestureDetector = new GestureDetector(listener);
            // Set listener on view
            view.setOnTouchListener(this);
        }
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            // Return Gesture event
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }


}