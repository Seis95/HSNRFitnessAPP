package com.example.denni.hsnrfitnessapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.TextView;

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
        d.reset();
        d.addcoloum(mydate);
        d.add("Übungen",mydate,"Laufen",10);
        d.get(mydate,3);
    }




}
