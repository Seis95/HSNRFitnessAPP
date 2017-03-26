package com.example.denni.hsnrfitnessapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Dennis on 10.03.2017.
 */

public class Eintrag extends AppCompatActivity {
ListView list;
    ArrayList Array1;
    EditText iWert;
    String selectedFromList;

    int Wert;
    String Path="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eintrag);
        int wert = getIntent().getExtras().getInt("liste");
        list = (ListView)this.findViewById(R.id.listview1);
        iWert = (EditText)this.findViewById(R.id.editText);

        Array1 = new ArrayList<String>();
        File myDir = getFilesDir();
        Path =myDir.toString();
        readTextfile(wert);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, Array1);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                 selectedFromList =(String) (list.getItemAtPosition(myItemInt));

            }
        });
    }
    public void save(View v){
    Log.d("SOMETHING","Soll gespeichert werden: "+selectedFromList);
        int wert=Integer.parseInt(iWert.getText().toString());
        String  mydate = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        DatabaseHandler d = new DatabaseHandler(this);
        if (d.check(selectedFromList)==0){
            d.add("Data",mydate,selectedFromList,wert,wert,wert);

        }else {
            d.updateA(selectedFromList,wert);
            Log.d("SOMETHING","Höchster Wert "+d.getHWert(selectedFromList));
            Log.d("SOMETHING","Aktueller Wert "+wert);
            if(wert>d.getHWert(selectedFromList)){
                Log.d("SOMETHING","Update "+wert);
                d.updateH(selectedFromList,wert);
            }
        }
        Intent in = new Intent(Eintrag.this, MainActivity.class);
        
        startActivity(in);

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
        logFile = new File(Path,file);
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
