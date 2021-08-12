package com.id20048076.oursingapore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Island> islandList;

    public CustomAdapter(Context context, int resource, ArrayList<Island> objects){
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        islandList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tv1 = rowView.findViewById(R.id.textView6);
        TextView tv2 = rowView.findViewById(R.id.textView7);
        TextView tv3 = rowView.findViewById(R.id.textView8);
        RatingBar ratingBar = rowView.findViewById(R.id.ratingBar2);

        // Obtain the Android Version information based on the position
        Island currentIsland = islandList.get(position);

        // Set values to the TextView to display the corresponding information
        tv1.setText(currentIsland.getName());
        tv2.setText(currentIsland.getDescription());
        tv3.setText(String.format("%d",currentIsland.getArea()));
        ratingBar.setRating(currentIsland.getStars());
        ratingBar.setEnabled(false);

        return rowView;
    }

}