package com.example.sketchbook;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.firebase.ui.auth.AuthUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Home page class displays the main screen
public class HomePage extends AppCompatActivity{

    // Tag for testing
    private static final String TAG = "HomePage";

    // Initiate Add Button
    ImageButton addButton;

    // call myApplication
    MyApplication myApplication = (MyApplication) this.getApplication();

    // Set the list
    List<Sketchbook> sketchbookList;

    // Initiate variables for the recycler
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Call the updated sketchbook list
        sketchbookList = myApplication.getSketchbookList();

        // Add the "add sketchbook" button
        addButton = (ImageButton) findViewById(R.id.addButton);

        // When add button is clicked bring the user to the add sketchbook activity
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddPage();
            }
        });

        // set the recycler view
        recyclerView = findViewById(R.id.sketchbookRecList);

        // Keep the size fixed
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify the adapter
        mAdapter = new MySketchesRecyclerAdapter(sketchbookList, this);
        recyclerView.setAdapter(mAdapter);
    }


    // Simple function to open the add page
    public void openAddPage() {
        Intent intent = new Intent(this, AddPage.class);
        startActivity(intent);
    }

}