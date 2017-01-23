package com.martyniv.workoutsaladapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DBOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    //Constants for db name and version
    public static final String DATABASE_NAME = "workout.db";
    private static final int DATABASE_VERSION = 2;

    //Constants for identifying table and columns
    public static final String TABLE_WORKOUT = "workout";
    public static final String WORKOUT_ID = "_id";
    public static final String WORKOUT_NAME = "workoutName";
    public static final String WORKOUT_TEXT = "workoutText";
    public static final String WORKOUT_PART = "workoutPart";
    public static final String WORKOUT_CREATED = "workoutCreated";


    public static final String TABLE_WEEK = "week";
    public static final String WEEK_ID = "_id";
    public static final String WEEK_NAME = "weekName";
    public static final String WEEK_MON = "monday";
    public static final String WEEK_TUE = "tuesday";
    public static final String WEEK_WED = "wednesday";
    public static final String WEEK_THU = "thursday";
    public static final String WEEK_FRI = "friday";
    public static final String WEEK_SAT = "saturday";
    public static final String WEEK_SUN = "sunday";
    public static final String WEEK_CREATED = "weekCreated";

    private static final int[] icons = {android.R.drawable.ic_popup_reminder, android.R.drawable.ic_menu_add,
            android.R.drawable.ic_menu_delete};

    public static final String[] ALL_WORKOUT_COLUMNS = {
            WORKOUT_NAME, WORKOUT_TEXT, WORKOUT_PART
    };
    public static final String[] ALL_WEEK_COLUMNS = {
            WEEK_NAME, WEEK_NAME, WEEK_MON, WEEK_TUE, WEEK_WED, WEEK_THU, WEEK_FRI, WEEK_SAT, WEEK_SUN
    };

    //SQL to create table
    private static final String TABLE_WORKOUT_CREATE =
            "CREATE TABLE " + TABLE_WORKOUT + " (" +
                    WORKOUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    WORKOUT_NAME + " TEXT, " +
                    WORKOUT_TEXT + " TEXT, " +
                    WORKOUT_PART + " TEXT, " +
                    WORKOUT_CREATED + " TEXT default CURRENT_TIMESTAMP" +
                    ")";
    private static final String TABLE_WEEK_CREATE =
            "CREATE TABLE " + TABLE_WEEK + " (" +
                    WEEK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    WEEK_NAME + " TEXT, " +
                    WEEK_MON + " TEXT, " +
                    WEEK_TUE + " TEXT, " +
                    WEEK_WED + " TEXT, " +
                    WEEK_THU + " TEXT, " +
                    WEEK_FRI + " TEXT, " +
                    WEEK_SAT + " TEXT, " +
                    WEEK_SUN + " TEXT, " +
                    WEEK_CREATED + " TEXT default CURRENT_TIMESTAMP" +
                    ")";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_WORKOUT_CREATE);
        db.execSQL(TABLE_WEEK_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEEK);
            onCreate(db);
        }
    }

    public boolean insertWeek(List list) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (int i = 0; i <= ALL_WEEK_COLUMNS.length; i++) {
            values.put(ALL_WEEK_COLUMNS[i], list.get(i).toString());
        }
        long r = db.insert(TABLE_WEEK, null, values);

        return r != -1;

    }

    public void insertWorkout(ListWorkout workout) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WORKOUT_NAME, workout.getItemTitle());
        values.put(WORKOUT_TEXT, workout.getText());
        values.put(WORKOUT_PART, workout.getType());
        db.insert(TABLE_WORKOUT, null, values);
        db.close();


    }

    public List<ListWorkout> getAllWorkoutData() {
        db = this.getReadableDatabase();

        List<ListWorkout> workoutList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_WORKOUT, null, null, null, null, null, null);
        try {
            while (cursor.moveToNext()) {
                workoutList.add(new ListWorkout(cursor.getString(1), cursor.getString(2),
                        cursor.getString(3)));
            }
        } finally {
            cursor.close();
            db.close();
        }


        return workoutList;
    }

    public List<ListWorkout> getWorkoutDataByBodyPart(String part) {
        db = this.getReadableDatabase();

        List<ListWorkout> workoutList = new ArrayList<>();
        try {
        Cursor cursor = db.query(TABLE_WORKOUT, new String[]{WORKOUT_NAME, WORKOUT_TEXT, WORKOUT_PART},
                WORKOUT_PART + "=?", new String[]{part}, null, null, null);

            while (cursor.moveToNext()) {
                workoutList.add(new ListWorkout(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2)));

            }
            cursor.close();
        } finally {

            db.close();
        }

        return workoutList;
    }

    public Set<String> getAllBodyParts() {
        db = this.getReadableDatabase();

        Set<String> bodyParts = new HashSet<>();
        Cursor cursor = db.query(TABLE_WORKOUT, new String[]{WORKOUT_PART},
                null, null, null, null, null);
        try {
            while (cursor.moveToNext()) {
                bodyParts.add(cursor.getString(0));
            }
        } finally {
            cursor.close();
            db.close();
        }

        return bodyParts;
    }

}
