package com.example.denni.hsnrfitnessapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Denni on 15.02.2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String LOG = "DatabaseHelper";

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "Fitness";

    //Tables
    private static final String TABLE_1 = "Ausdauer";
    private static final String TABLE_2= "Geräte";
    private static final String TABLE_3 = "Übungen";
    //Table1
    private static final String Ausdauer_ID = "ID";
    private static final String Ausdauer_Name = "Name";
    //Table2
    private static final String Geräte_ID = "ID";
    private static final String Geräte_Name = "Name";
    //Table3
    private static final String Übungen_ID = "ID";
    private static final String Übungen_Name = "Name";

    private static final String CREATE_Table1 = "CREATE TABLE IF NOT EXISTS Ausdauer (ID INTEGER PRIMARY KEY,Name TEXT );";
    private static final String CREATE_Table2 = "CREATE TABLE IF NOT EXISTS Geräte " + "" + "(" + Geräte_ID + " INTEGER PRIMARY KEY," + Geräte_Name + " TEXT "+ ");";
    private static final String CREATE_Table3 = "CREATE TABLE IF NOT EXISTS Übungen " + "" + "(" + Übungen_ID + " INTEGER PRIMARY KEY," + Übungen_Name + " TEXT "+ ");";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_Table1);
        db.execSQL(CREATE_Table2);
        db.execSQL(CREATE_Table3);
    }

    //Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_3);
        // Create Tables again;
        onCreate(db);
    }

    public void reset(){
        SQLiteDatabase db = this.getReadableDatabase();
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_3);
        // Create Tables again;
        onCreate(db);
    }

    public void add(String table,String datum,String name2,int gewicht){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("INSERT INTO "
                + table
                + " (Name,'"+ datum+"')"
                + " VALUES ('"+name2+"',"
                + gewicht+
                ");");
    }
    public void addcoloum(String datum){

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("ALTER TABLE "+TABLE_1+" ADD '"+datum+"' TEXT;");
        db.execSQL("ALTER TABLE "+TABLE_2+" ADD '"+datum+"' TEXT;");
        db.execSQL("ALTER TABLE "+TABLE_3+" ADD '"+datum+"' TEXT;");
    }
    public String get(String datum){
        String Data ="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_3 , null);
        String cnames= c.getColumnName(1);
//        c.getString(1);
//        Log.d("SOMETHING", "Datenbank: "+c.getString(1));
        int Column1 = c.getColumnIndex("Name");
        int Column2 = c.getColumnIndex(datum);
        c.moveToFirst();
        if (c != null) {
            // Loop through all Results
            do {
               String Name = c.getString(Column1);
                String Datum = c.getString(Column2);
                Data =Data +Name+"/"+Datum+"\n";
            }while(c.moveToNext());
        }
        System.out.println(c.toString());
        Log.d("SOMETHING", "Datenbank: "+Data);
        return Data;
    }
}
