package com.martyniv.workoutsaladapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.martyniv.workoutsaladapp.R;
import com.martyniv.workoutsaladapp.adapter.WorkoutAdapter;
import com.martyniv.workoutsaladapp.model.DBOpenHelper;
import com.martyniv.workoutsaladapp.model.WorkoutItem;
import com.martyniv.workoutsaladapp.ui.DetailsActivity;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class WorkoutListFragment extends Fragment implements WorkoutAdapter.ItemClickCallBack {
    private RecyclerView rcView;
    private MaterialSpinner spinner;
    public WorkoutAdapter wokOutAdapter;
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private Activity activity;
    private List<WorkoutItem> workoutList;
    private WorkoutListFragment thisFragment;

    public String getCurrentBodyType() {
        return currentBodyType;
    }

    public String currentBodyType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisFragment = this;
        activity = getActivity();
        dbOpenHelper = new DBOpenHelper(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout_list, container, false);
        // Inflate the layout for this fragment
       insertSomeData();
        spinner = (MaterialSpinner) v.findViewById(R.id.my_spinner);
        rcView = (RecyclerView) v.findViewById(R.id.rec_list);
        rcView.setLayoutManager(new LinearLayoutManager(activity));
        getSpinner();
        return v;
    }
    private void insertSomeData(){
        db = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.onUpgrade(db, 2, 3);
        WorkoutItem workout = new WorkoutItem("workout1", "Nothingness cannot be definedgfgf", "chest");
        WorkoutItem workout2 = new WorkoutItem("workout3", "The softest thing cannot be snapped", "biceps");
        WorkoutItem workout3 = new WorkoutItem("workout2", "The softest thing cannot be snapped", "biceps");
        WorkoutItem workout4 = new WorkoutItem("workout2", "The softest thing cannot be snapped", "triceps");

        dbOpenHelper.insertWorkout(workout);
        dbOpenHelper.insertWorkout(workout2);
        dbOpenHelper.insertWorkout(workout3);
        dbOpenHelper.insertWorkout(workout4);
        db.close();
    }
    private void getSpinner() {
        db = dbOpenHelper.getReadableDatabase();
        Set<String> set = dbOpenHelper.getAllBodyParts();
        List<String> list = new ArrayList<>();

        for (String aSet : set) {
            list.add(aSet);
        }

        spinner.setItems(list);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                workoutList = dbOpenHelper.getWorkoutDataByBodyPart(item);
                currentBodyType = item;
                wokOutAdapter = new WorkoutAdapter(workoutList, activity);
                rcView.setAdapter(wokOutAdapter);
                wokOutAdapter.setItemClickCallBack(thisFragment);

            }
        });
        if (workoutList == null) {
            workoutList = dbOpenHelper.getWorkoutDataByBodyPart("triceps");
            wokOutAdapter = new WorkoutAdapter(workoutList, activity);
            rcView.setAdapter(wokOutAdapter);
            wokOutAdapter.setItemClickCallBack(this);
        }


    }

    @Override
    public void onItemClick(int p) {
        WorkoutItem item = workoutList.get(p);
        Log.i("MyMsg", item.getItemTitle());
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", item.getId());
        bundle.putString("Title", item.getItemTitle());
        bundle.putString("Text", item.getText());
        bundle.putString("Type", item.getType());
        intent.putExtra("bundleExtras", bundle);
        startActivity(intent);
        //new intent
    }

    @Override
    public void onButtonClick(int p) {

    }
}

