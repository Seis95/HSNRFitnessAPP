package com.example.denni.hsnrfitnessapp;

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
        database();
    }

    public void database(){
        String te="testM";
        String Data= "";
        SQLiteDatabase myDB;
        myDB = this.openOrCreateDatabase("Fitness", MODE_PRIVATE, null);

   /* Create a Table in the Database. */
        //reset
        myDB.execSQL("DROP TABLE Übungen");

        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + "Übungen"
                + " (Name VARCHAR, Datum VARCHAR);");

   /* Insert data to a Table*/
        myDB.execSQL("INSERT INTO "
                + "Übungen"
                + " (Name, Datum)"
                + " VALUES ('Laufen','"
                +mydate+
                "');");

        Cursor c = myDB.rawQuery("SELECT * FROM " + "Übungen" , null);
        int Column1 = c.getColumnIndex("Name");
        int Column2 = c.getColumnIndex("Datum");
        c.moveToFirst();
        if (c != null) {
            // Loop through all Results
            do {
                String Name = c.getString(Column1);
                String Datum = c.getString(Column2);
                Data =Data +Name+"/"+Datum+"\n";
            }while(c.moveToNext());
        }
        System.out.println(c.toString());
        Log.d("SOMETHING", "Datenbank: "+Data);

    }

}
