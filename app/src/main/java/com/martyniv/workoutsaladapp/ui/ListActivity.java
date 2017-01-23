package com.martyniv.workoutsaladapp.ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.martyniv.workoutsaladapp.R;
import com.martyniv.workoutsaladapp.adapter.CustomSpinnerAdapter;
import com.martyniv.workoutsaladapp.adapter.WorkoutAdapter;
import com.martyniv.workoutsaladapp.model.DBOpenHelper;
import com.martyniv.workoutsaladapp.model.ListWorkout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListActivity extends AppCompatActivity {

    private RecyclerView rcView;
    public WorkoutAdapter wokOutAdapter;
    private DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
    private SQLiteDatabase db;
    private Toolbar toolbar;
    public String s = "triceps";
    public Context context;

    private Spinner spinner_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        context = this;
        db = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.onUpgrade(db, 2, 3);
        ListWorkout workout = new ListWorkout("workout1", "Nothingness cannot be definedgfgf", "chest");
        ListWorkout workout2 = new ListWorkout("workout2", "The softest thing cannot be snapped", "biceps");
        ListWorkout workout3 = new ListWorkout("workout2", "The softest thing cannot be snapped", "biceps");
        ListWorkout workout4 = new ListWorkout("workout2", "The softest thing cannot be snapped", "triceps");

        dbOpenHelper.insertWorkout(workout);
        dbOpenHelper.insertWorkout(workout2);
        dbOpenHelper.insertWorkout(workout3);
        dbOpenHelper.insertWorkout(workout4);
        db.close();
        rcView = (RecyclerView) findViewById(R.id.rec_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        spinner_nav = (Spinner) findViewById(R.id.spinner_nav);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        addItemsToSpinner();


    }

    public void addItemsToSpinner() {

        db = dbOpenHelper.getReadableDatabase();
        Set<String> set = dbOpenHelper.getAllBodyParts();
        List<String> list = new ArrayList<>();

        for (String aSet : set) {
            list.add(aSet);
        }

        // Custom ArrayAdapter with spinner item layout to set popup background

        /*CustomSpinnerAdapter spinAdapter = new CustomSpinnerAdapter(
                getApplicationContext(), list);*/


        // Default ArrayAdapter with default spinner item layout, getting some
        // view rendering problem in lollypop device, need to test in other
        // devices

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        spinAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);


        spinner_nav.setAdapter(spinAdapter);
        spinner_nav.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                s = adapter.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(getApplicationContext(), "Selected  : " + s,
                        Toast.LENGTH_LONG).show();
                wokOutAdapter = new WorkoutAdapter(dbOpenHelper.getWorkoutDataByBodyPart(s), context);

                rcView.setAdapter(wokOutAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }


}
