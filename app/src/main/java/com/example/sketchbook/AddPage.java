package com.example.sketchbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

// Class AddPage is used to create a new sketchbook
public class AddPage extends AppCompatActivity {



    // Initiate variables
    int REQUEST_IMAGE_CAPTURE = 3;
    String imagePath;
    ImageView imageProfile;
    Drawable picture;

    FloatingActionButton backButton;
    Button createButton;
    Button cameraButton;
    List<Sketchbook> sketchbookList;
    EditText sketchName, sketchDate, sketchDescription;
    int sketchPic;

    // Initiate myApplication
    MyApplication myApplication = (MyApplication) this.getApplication();

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);

        // Set the sketchbook list to the updated list
        sketchbookList = myApplication.getSketchbookList();

        // Set the variables
        backButton = findViewById(R.id.addBackButton);
        createButton = findViewById(R.id.createButton);
        cameraButton = findViewById(R.id.cameraButton);
        sketchName = findViewById(R.id.nameInput);
        sketchDate = findViewById(R.id.dateInput);

        sketchDescription = findViewById(R.id.descriptionInput);

        imageProfile = findViewById(R.id.addPhoto);


        cameraButton.setOnClickListener(this::clickedOpenCamera);


        // Send the user back home when the backButton is clicked
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });

        // Create a new Sketchbook with all of the users Info when createButton is pressed -> send them to the home screen
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create Sketchbook object with a new Id number and all of the inputted info
                int nextId = MyApplication.getNextId();
                Sketchbook newSketchbook = new Sketchbook(nextId, sketchName.getText().toString(), sketchDate.getText().toString(),
                        sketchDescription.getText().toString(), R.drawable.d);

                // add the object to the global list
                sketchbookList.add(newSketchbook);

                // add 1 to nextId for the next sketchBook
                MyApplication.setNextId(nextId++);

                // go back to the home screen
                openHomePage();

            }
        });

    }

    private void clickedOpenCamera(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        super.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Drawable d = new BitmapDrawable(getResources(), imageBitmap);
            picture = d;

            imageProfile.setImageDrawable(d);
        }
    }



    // Home screen function
    public void openHomePage() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}