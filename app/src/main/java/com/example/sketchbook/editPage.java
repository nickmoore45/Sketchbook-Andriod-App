package com.example.sketchbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class editPage extends AppCompatActivity {

    // Initiate Sketchbook variables
    List<Sketchbook> sketchbookList;
    EditText sketchName, sketchDate, sketchDescription;
    ImageView sketchPic;

    int id;

    Button editButton;

    // Grab List
    MyApplication myApplication = (MyApplication) this.getApplication();


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);

        // Button
        editButton = findViewById(R.id.editButton);

        // Initiate list
        sketchbookList = myApplication.getSketchbookList();

        // Initiate variables
        sketchName = findViewById(R.id.editName);
        sketchDate = findViewById(R.id.editDate);
        sketchDescription = findViewById(R.id.editDescription);
        sketchPic = findViewById(R.id.editPhoto);


        // get intent id that was passed
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        // set sketchbook to null before initiating it
        Sketchbook sketchbook = null;

//         for "each" loop that finds and sets correct sketchbook in the list
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

        Sketchbook finalSketchbook = sketchbook;
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change the Sketchbooks info
                finalSketchbook.setName(sketchName.getText().toString());
                finalSketchbook.setDate(sketchDate.getText().toString());
                finalSketchbook.setDescription(sketchDescription.getText().toString());
//                finalSketchbook.setImageURL(sketchPic.getText().toString());


                // Go back home
                Intent intent = new Intent(editPage.this, HomePage.class);
                startActivity(intent);
            }
        });

    }
}