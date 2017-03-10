package com.example.denni.hsnrfitnessapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dennis on 10.03.2017.
 */

public class Eintrag extends AppCompatActivity {
ListView list;
    ArrayList Array1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eintrag);
        int wert = getIntent().getExtras().getInt("liste");
        list = (ListView)this.findViewById(R.id.listview1);
        Array1 = new ArrayList<String>();
        readTextfile(wert);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, Array1);
       list.setAdapter(arrayAdapter);
    }

    public void readTextfile(int i){
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
        logFile = new File(Environment.getExternalStorageDirectory()+"/Download/",file);
        if (!logFile.exists()) {
            try {
                Log.d("SOMETHING","File existiert aus merkwürdigen Gründen nicht");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BufferedReader r=null;
        try {
            r = new BufferedReader(new FileReader(logFile));
            String x = r.readLine();
            while (x!=null){
                Log.d("SOMETHING","Durchlauft Schleife");
                Log.d("SOMETHING",x);
                Array1.add(x);
                x = r.readLine();
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
