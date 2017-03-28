package com.example.denni.hsnrfitnessapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    String Path="";
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
        File myDir = getFilesDir();
        Path =myDir.toString();
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rgroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Log.d("SOMETHING","Android Studio bug");
                int id=checkedId;
                Array2.clear();
                Log.d("SOMETHING","Android Studio bug");
                if (id==R.id.radio1){
                    Log.d("SOMETHING","Android Studio bug");
                    readTextfile(4,2);
                }
                if (id == R.id.radio2){
                    readTextfile(5,2);
                }
                if (id==R.id.radio3){
                    readTextfile(6,2);
                }
                if (id==R.id.radio4){
                    readTextfile(7,2);
                }
                if (id==R.id.radio5){
                    readTextfile(8,2);
                }
                if (id==R.id.radio6){
                    readTextfile(9,2);
                }
                if (id==R.id.radio7){
                    readTextfile(10,2);
                }
                arrayAdapter2.notifyDataSetChanged();
            }
        });

        list2 = (ListView)this.findViewById(R.id.list2);//auswahl
        list3 = (ListView)this.findViewById(R.id.list3);//ausgewählt
        Array2 = new ArrayList<String>();

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                String selectedFromList =(String) (list2.getItemAtPosition(myItemInt));
                if (Array2.toString().contains(selectedFromList)) {

                }else {
                    Array2.add(selectedFromList);
                    arrayAdapter2.notifyDataSetChanged();
                }
            }
        });
        list3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                String selectedFromList =(String) (list3.getItemAtPosition(myItemInt));
                Array2.remove(selectedFromList);
                arrayAdapter2.notifyDataSetChanged();
            }
        });
        Array1 = new ArrayList<String>();
        readTextfile(1,1);
        readTextfile(2,1);
        readTextfile(3,1);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, Array1);
        list2.setAdapter(arrayAdapter);
        arrayAdapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, Array2);
        arrayAdapter2.notifyDataSetChanged();
        list3.setAdapter(arrayAdapter2);
        readTextfile(4,2);
        arrayAdapter2.notifyDataSetChanged();

    }
    public void readTextfile(int i,int array){
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
        if (i ==4){
            file = "Montag.txt";
        }
        if (i ==5){
            file = "Dienstag.txt";
        }
        if (i ==6){
            file = "Mittwoch.txt";
        }
        if (i ==7){
            file = "Donnerstag.txt";
        }
        if (i ==8){
            file = "Freitag.txt";
        }
        if (i ==9){
            file = "Samstag.txt";
        }
        if (i ==10){
            file = "Sonntag.txt";
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
                if(array ==1) {
                    Array1.add(x);
                }
                if(array ==2) {
                    if (Array2.toString().contains(x)) {
                        // true
                    }else {
                        Array2.add(x);
                    }
                }
                x = r.readLine();
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addTextToFile(int i,String text) throws IOException {
        int vorhanden = 0;
        //Irgendwas funktioniert hier noch nicht
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
        logFile = new File(Path,file);
        if (!logFile.exists()) {
            try {
                Log.d("SOMETHING","File exisitert nicht");

                logFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{


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
        String file="";
        if (wert ==1){
            file = "Montag.txt";
        }
        if (wert ==2){
            file = "Dienstag.txt";
        }
        if (wert==3){
            file = "Mittwoch.txt";
        }
        if (wert ==4){
            file = "Donnerstag.txt";
        }
        if (wert ==5){
            file = "Freitag.txt";
        }
        if (wert ==6){
            file = "Samstag.txt";
        }
        if (wert ==7){
            file = "Sonntag.txt";
        }
        Toast.makeText(getApplicationContext(), "Gespeichert", Toast.LENGTH_LONG).show();
        File logFile = new File(Path,file);
        logFile.delete();
        while (list3.getCount()>i){

            Log.d("SOMETHING","Wird gespeichert: "+list3.getAdapter().getItem(i).toString());
            try {
            addTextToFile(wert,list3.getAdapter().getItem(i).toString());
            i++;
                Log.d("SOMETHING","aktuell:"+i +"noch: "+ list3.getCount());
            }
            catch (Exception e){

            }
        }
        Intent in = new Intent(Kalender.this, MainActivity.class);
        startActivity(in);
    }
}
