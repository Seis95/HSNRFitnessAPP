package com.example.denni.hsnrfitnessapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class Add extends AppCompatActivity {
    RadioButton radioa;
    RadioButton radioü;
    RadioButton radiog;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        radioa = (RadioButton)this.findViewById(R.id.radioA);
        radiog = (RadioButton)this.findViewById(R.id.radioG);
        radioü = (RadioButton)this.findViewById(R.id.radioÜ);
        edit = (EditText)this.findViewById(R.id.editText3);

    }
public void save(View v){
    String name = edit.getText().toString();
if(radioa.isChecked()){
    Toast.makeText(getApplicationContext(), name+ " wurde Hinzugefügt", Toast.LENGTH_LONG).show();
    try {
        addTextToFile(1,name);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    if(radiog.isChecked()){
        Toast.makeText(getApplicationContext(), name+ " wurde Hinzugefügt", Toast.LENGTH_LONG).show();
        try {
            addTextToFile(2,name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    if(radioü.isChecked()){
        Toast.makeText(getApplicationContext(), name+ " wurde Hinzugefügt", Toast.LENGTH_LONG).show();
        try {
            addTextToFile(3,name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    public void addTextToFile(int i,String text) throws IOException {
        int vorhanden = 0;
        //Irgendwas funktioniert hier noch nicht // Erledigt
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
}
