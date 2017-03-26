package com.example.denni.hsnrfitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dennis on 15.02.2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String LOG = "DatabaseHelper";

    //Database Version
    private static final int DATABASE_VERSION = 3;

    //Database Name
    private static final String DATABASE_NAME = "Fitness";

    //Tables
    private static final String TABLE_1 = "Data";



    private static final String CREATE_Table1 = "CREATE TABLE Data (ID INTEGER PRIMARY KEY,Name TEXT,Datum Text,Startwert INTEGER, Aktuellerwert INTEGER, Höchstwert INTEGER );";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_Table1);

    }

    //Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);

        // Create Tables again;
        onCreate(db);
    }

    public void reset(){
        SQLiteDatabase db = this.getReadableDatabase();
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);

        // Create Tables again;
        onCreate(db);
    }

    public void add(String table,String datum,String name,int gewicht,int gewicht2,int gewicht3) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put("Name", name);
        values.put("Aktuellerwert", gewicht);
        values.put("Höchstwert", gewicht2);
        values.put("Startwert", gewicht3);
        values.put("Datum", datum);

        db.insert("Data", null, values);

        // I will suggest to keep the connection to the database open when your app is
        // running, the recommended time to close the connection is when your app is
        //going to pause/stop.
        //db.close();
    }
    public int getAWert(String Name) {

        final String TABLE_NAME = "Data";

        String selectQuery = "SELECT * FROM Data where Name = '" +Name+"'";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String[] data      = null;
        int wert=0;

        if (cursor.moveToFirst()) {
            do {
                wert =  cursor.getInt(4);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return wert;
    }
    public int getHWert(String Name) {

        final String TABLE_NAME = "Data";

        String selectQuery = "SELECT * FROM Data where Name = '" +Name+"'";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String[] data      = null;
        int wert=0;

        if (cursor.moveToFirst()) {
            do {
                wert =  cursor.getInt(5);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return wert;
    }
    public String getdate(String Name) {

        final String TABLE_NAME = "Data";

        String selectQuery = "SELECT * FROM Data where Name = '" +Name+"'";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String[] data      = null;
        String wert="Datum";

        if (cursor.moveToFirst()) {
            do {
                wert =  cursor.getString(2);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return wert;
    }
    public int getSWert(String Name) {

        final String TABLE_NAME = "Data";

        String selectQuery = "SELECT * FROM Data where Name = '" +Name+"'";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String[] data      = null;
        int wert=0;

        if (cursor.moveToFirst()) {
            do {
                wert =  cursor.getInt(3);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return wert;
    }
    public int check(String Name){
        String selectQuery = "SELECT * FROM Data WHERE Name = '" +Name+"'";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        return cursor.getCount();
    }
    public void updateA(String Name, int Wert){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues args = new ContentValues();
        args.put("Aktuellerwert", Wert);
        db.update("Data", args, "Name" + "= '" + Name+"'", null);
    }
    public void updateH(String Name, int Wert){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues args = new ContentValues();
        args.put("Höchstwert", Wert);
        db.update("Data", args, "Name" + "= '" + Name+"'", null);
    }
}
