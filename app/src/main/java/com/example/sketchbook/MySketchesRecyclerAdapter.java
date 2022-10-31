package com.example.sketchbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MySketchesRecyclerAdapter extends RecyclerView.Adapter<MySketchesRecyclerAdapter.ViewHolder> {

    // Initiate Variables
    List<Sketchbook> sketchbookList;
    private Context context;

    // Set up public Adapter
    public MySketchesRecyclerAdapter(List<Sketchbook> sketchbookList, Context context) {
        this.sketchbookList = sketchbookList;
        this.context = context;
    }


    // Public view holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sketchbook_cardviews, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

//    int imageResource = getResources().getIdentifier("@drawable/eye.jpeg", null, this.getPackageName());

    // Bind view holder to change the text on viewSketchbook page when a specific sketchbook is clicked
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        viewHolder.sketchName.setText(sketchbookList.get(position).getName());
        viewHolder.sketchDate.setText(sketchbookList.get(position).getDate());

        viewHolder.sketchPic.setImageDrawable(context.getDrawable(sketchbookList.get(position).getImageResource()));

        // Open viewSketchbook activity with the new information
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, viewSketchbook.class);
                intent.putExtra("id", sketchbookList.get(position).getId());
                context.startActivity(intent);
            }
        });
        viewHolder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, editPage.class);
                intent.putExtra("id", sketchbookList.get(position).getId());
                context.startActivity(intent);
                return false;
            }
        });

    }

    // Get the size of the sketchbook list
    @Override
    public int getItemCount() {
        return sketchbookList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView sketchPic;
        TextView sketchName;
        TextView sketchDate;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sketchPic = itemView.findViewById(R.id.sketchCover);
            sketchName = itemView.findViewById(R.id.sketchName);
            sketchDate = itemView.findViewById(R.id.sketchDate);
            parentLayout = itemView.findViewById(R.id.oneLineSketchbookLayout);

        }
    }
}
