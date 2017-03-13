package com.example.denni.hsnrfitnessapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Kalender extends AppCompatActivity {
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radio4;
    RadioButton radio5;
    RadioButton radio6;
    RadioButton radio7;
    ListView list2;
    ListView list3;
    ArrayList Array1;
    ArrayList Array2;
    ArrayList Array3;
    ArrayAdapter<String> arrayAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);
        radio1 = (RadioButton)this.findViewById(R.id.radio1);
        radio2 = (RadioButton)this.findViewById(R.id.radio2);
        radio3 = (RadioButton)this.findViewById(R.id.radio3);
        radio4 = (RadioButton)this.findViewById(R.id.radio4);
        radio5 = (RadioButton)this.findViewById(R.id.radio5);
        radio6 = (RadioButton)this.findViewById(R.id.radio6);
        radio7 = (RadioButton)this.findViewById(R.id.radio7);

        list2 = (ListView)this.findViewById(R.id.list2);
        list3 = (ListView)this.findViewById(R.id.list3);
        Array2 = new ArrayList<String>();
        Array3 = new ArrayList<String>();
        Array3.add("Montag");
        Array3.add("Dienstag");
        Array3.add("Mitwoch");
        Array3.add("Donnerstag");
        Array3.add("Freitag");
        Array3.add("Samstag");
        Array3.add("Sonntag");
        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                String selectedFromList =(String) (list2.getItemAtPosition(myItemInt));
                Array2.add(selectedFromList);
                arrayAdapter2.notifyDataSetChanged();
            }
        });
        Array1 = new ArrayList<String>();
        readTextfile(1);
        readTextfile(2);
        readTextfile(3);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, Array1);
        list2.setAdapter(arrayAdapter);
        arrayAdapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, Array2);
        arrayAdapter2.notifyDataSetChanged();
        list3.setAdapter(arrayAdapter2);


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
    public void addTextToFile(int i,String text) throws IOException {
        int vorhanden = 0;
        //Irgendwas funktioniert hier noch nicht // Erledigt
        File logFile;
        String file ="";
        if (i ==1){
            file = "Montag.txt";
        }
        if (i ==2){
            file = "Dienstag.txt";
        }
        if (i ==3){
            file = "Mittwoch.txt";
        }
        if (i ==4){
            file = "Donnerstag.txt";
        }
        if (i ==5){
            file = "Freitag.txt";
        }
        if (i ==6){
            file = "Samstag.txt";
        }
        if (i ==7){
            file = "Sonntag.txt";
        }
        Log.d("SOMETHING","KA: "+ Environment.getExternalStorageState());
        logFile = new File(Environment.getExternalStorageDirectory()+"/Download/",file);
        if (!logFile.exists()) {
            try {
                Log.d("SOMETHING","File exisitert nicht");

                logFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //testet ob schon vorhanden
        BufferedReader r=null;
        try {
            r = new BufferedReader(new FileReader(logFile));
            String x = r.readLine();
            while (x!=null){
                Log.d("SOMETHING","Durchlauft Schleife");
                Log.d("SOMETHING",x);
                if (x.equals(text)){
                    Log.d("SOMETHING","Eintrag schon vorhanden");
                    Toast.makeText(getApplicationContext(), "Schon vorhanden", Toast.LENGTH_LONG).show();
                    vorhanden = 1;
                    break;
                }
                x = r.readLine();
            }
            r.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("SOMETHING","Schon vorhanden? : "+vorhanden);
        if (vorhanden <1) {
            Log.d("SOMETHING","Schon vorhanden : "+logFile);
            FileWriter fw=new FileWriter(logFile.getAbsolutePath(),true);
            Log.d("SOMETHING","test0");
            BufferedWriter buf = new BufferedWriter(fw);
            Log.d("SOMETHING","Soll angehängt werden: "+text);
            buf.append(text);
            buf.newLine();
            buf.flush();
            buf.close();
        }
        //test ende
        Log.d("SOMETHING","Stest ");
    }
    public void speichern(View v){
        int i =0;
        int wert= 0;
        if (radio1.isChecked()){
            wert=1;
        }
        if (radio2.isChecked()){
            wert=2;
        }
        if (radio3.isChecked()){
            wert=3;
        }
        if (radio4.isChecked()){
            wert=4;
        }
        if (radio5.isChecked()){
            wert=5;
        }
        if (radio6.isChecked()){
            wert=6;
        }
        if (radio7.isChecked()){
            wert=7;
        }
        Toast.makeText(getApplicationContext(), ""+list3.getCount(), Toast.LENGTH_LONG).show();
        while (list3.getCount()>0){
            try {
            addTextToFile(wert,Array2.get(i).toString());
            i++;}
            catch (Exception e){

            }
        }
    }
}
