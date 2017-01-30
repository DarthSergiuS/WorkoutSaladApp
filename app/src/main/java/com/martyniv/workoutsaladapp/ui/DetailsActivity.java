package com.martyniv.workoutsaladapp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.martyniv.workoutsaladapp.R;

public class DetailsActivity extends AppCompatActivity {
    private EditText title;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getBundleExtra("bundleExtras");
        toolbar.setSubtitle("blabla");
        title = (EditText)findViewById(R.id.details_edit_title);
        text = (EditText) findViewById(R.id.details_edit_desc);

        if(extras.getString("Type")!=null){
            Log.i("MyMsg1",extras.getString("Type"));
        }

        if(extras.getString("Title")!=null){
            title.setText(extras.getString("Title"));
            text.setText(extras.getString("Text"));
        }



    }

}
