package com.example.denni.hsnrfitnessapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Denni on 07.02.2017.
 */

public class Datenbank extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "FitnessDaten";
    // Contacts table name
    private static final String Fitness = "Fitness";
    // Shops Table Columns names
    private static final String ID ="";
    private static final String Date = "";
    private static final String Ausdauer = "";
    private static final String Geräte = "";
    private static final String Übungen = "";
    public Datenbank(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + Fitness + "("
        + ID + " INTEGER PRIMARY KEY," + Date + " TEXT,"
        + Ausdauer + " INTEGER" + Geräte + " INTEGER," + Übungen + " INTEGER,"+  ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Fitness);
// Creating tables again
        onCreate(db);
    }
}
