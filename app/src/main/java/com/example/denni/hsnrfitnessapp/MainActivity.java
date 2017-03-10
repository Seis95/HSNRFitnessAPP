package com.example.denni.hsnrfitnessapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
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

/**
 * Created by Denni on 15.02.2017.
 */

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
        //resetfiles(); //nur zum test
        try {
            addTextToFile(1,"Laufen");
            addTextToFile(1,"Schwimmen");
            addTextToFile(1,"Nordic Walking");
            addTextToFile(2,"Butterfly");
            addTextToFile(2,"Beinpresse");
            addTextToFile(2,"Crunch");
            addTextToFile(3,"Liegestützen");
            addTextToFile(3,"Sit-ups");
            addTextToFile(3,"Crunches");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Ergebnis: "+readTextfile(1,1));
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
        logFile = new File(Environment.getExternalStorageDirectory()+"/Download/",file);
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

    public void resetfiles(){
       File logFile = null;
        logFile = new File(Environment.getExternalStorageDirectory(),"Ausdauer.txt");
        logFile.delete();
        logFile = new File(Environment.getExternalStorageDirectory(),"Geräte.txt");
        logFile.delete();
        logFile = new File(Environment.getExternalStorageDirectory(),"Übungen.txt");
        logFile.delete();
    }
    public void starteintrag1(View v){
        Intent in = new Intent(MainActivity.this, Eintrag.class);
        Bundle extras = new Bundle();
        extras.putInt("liste",1);
        in.putExtras(extras);
        startActivity(in);
    }
    public void starteintrag2(View v){
        Intent in = new Intent(MainActivity.this, Eintrag.class);
        Bundle extras = new Bundle();
        extras.putInt("liste",2);
        in.putExtras(extras);
        startActivity(in);
    }
    public void starteintrag3(View v){
        Intent in = new Intent(MainActivity.this, Eintrag.class);
        Bundle extras = new Bundle();
        extras.putInt("liste",3);
        in.putExtras(extras);
        startActivity(in);
    }
}
