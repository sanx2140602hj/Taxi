package com.losj.mitaxistiat.incluides;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.losj.mitaxistiat.R;

public class MyToolbar {

    public static void show (AppCompatActivity activity, String title, boolean upBottom){
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(title);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(upBottom);
    }

}
