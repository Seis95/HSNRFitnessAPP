package com.example.denni.hsnrfitnessapp;

import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public EditText Plan;
    public TextView Datum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText Plan = (EditText)this.findViewById(R.id.editText2);
        TextView Datum = (TextView)this.findViewById(R.id.textView7);
        String mydate = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());

        Datum.setText(mydate);
        Plan.setFocusable(false);
        Plan.setText("Kein Training für Heute geplant");
    }


}
