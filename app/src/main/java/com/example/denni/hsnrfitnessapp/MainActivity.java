package com.example.denni.hsnrfitnessapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public EditText Plan;
    public TextView Datum;
    String mydate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText Plan = (EditText)this.findViewById(R.id.editText2);
        TextView Datum = (TextView)this.findViewById(R.id.textView7);
        mydate = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());

        Datum.setText(mydate);
        Plan.setFocusable(false);
        Plan.setText("Kein Training für Heute geplant");
        DatabaseHandler d = new DatabaseHandler(this);
       // d.reset();
        //d.addcoloum(mydate);
        //d.add("Übungen",mydate,"Laufen",10);
        //d.get(mydate,3);
        //
        try {
            addTextToFile(1,"Laufen");
            addTextToFile(1,"Schwimmen");
            addTextToFile(1,"Nordic Walking");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Ergebnis: "+readTextfile(1,1));
    }

    public void addTextToFile(int i,String text) throws IOException {
        //Irgendwas funktioniert hier noch nicht
        File logFile;
        String file ="";
        if (i ==1){
            file = "Ausdauer.txt";
        }
        if (i ==2){
            file = "Geräte.txt";
        }
        if (i ==3){
            file = "Übungen.txt";
        }
        Log.d("SOMETHING","KA: "+ Environment.getExternalStorageState());
        logFile = new File(Environment.getExternalStorageDirectory(),file);
        if (!logFile.exists()) {
            try {
                logFile.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //testet ob schon vorhanden

                        BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
                        buf.append(text);
                        buf.newLine();
                        buf.close();

        //test ende

    }
    public String readTextfile(int i,int line){
        File logFile;
        String file ="";
        String ergebnis ="Kein Ergebnis";
        if (i ==1){
            file = "Ausdauer.txt";
        }
        if (i ==2){
            file = "Geräte.txt";
        }
        if (i ==3){
            file = "Übungen.txt";
        }
        BufferedReader buf = null;
        logFile = new File(Environment.getExternalStorageDirectory(),file);
        if (!logFile.exists()) {
            try {
                Log.d("SOMETHING","File existiert aus merkwürdigen Gründen nicht");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            buf = new BufferedReader(new FileReader(logFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int x = 0; x < line; x++){
            try {
              System.out.println  ("Akteulle Line: "+buf.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        try {
            ergebnis = buf.readLine();

            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ergebnis;
    }



}
