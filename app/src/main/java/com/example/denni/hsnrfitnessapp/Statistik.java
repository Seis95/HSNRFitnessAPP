package com.example.denni.hsnrfitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Statistik extends AppCompatActivity {
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    String Path="";
    ArrayList Array1;
    ListView list;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView date;
    ImageView image;
    ArrayAdapter<String> arrayAdapter;
    DatabaseHandler d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);
        radio1 = (RadioButton)this.findViewById(R.id.radio1);
        radio2 = (RadioButton)this.findViewById(R.id.radio2);
        radio3 = (RadioButton)this.findViewById(R.id.radio3);
        list = (ListView)this.findViewById(R.id.list);
        image = (ImageView) this.findViewById(R.id.imageView);
        t1 = (TextView)this.findViewById(R.id.textView1);
        t2 = (TextView)this.findViewById(R.id.textView2);
        t3 = (TextView)this.findViewById(R.id.textView3);
        date = (TextView)this.findViewById(R.id.tv_date);
        Array1=new ArrayList<String>();
        File myDir = getFilesDir();
        Path =myDir.toString();
        d = new DatabaseHandler(this);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rgroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Log.d("SOMETHING","Android Studio bug");
                int id=checkedId;
                Array1.clear();
                Log.d("SOMETHING","Android Studio bug");
                if (id==R.id.radio1){
                    readTextfile(1);
                }
                if (id == R.id.radio2){
                    readTextfile(2);
                }
                if (id==R.id.radio3){
                    readTextfile(3);
                }

                arrayAdapter.notifyDataSetChanged();
            }
        });
        readTextfile(1);
        arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, Array1);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {

                String selectedFromList =(String) (list.getItemAtPosition(myItemInt));
                t1.setText(String.valueOf(d.getSWert(selectedFromList)));
                t2.setText(String.valueOf(d.getHWert(selectedFromList)));
                t3.setText(String.valueOf(d.getAWert(selectedFromList)));
                date.setText(String.valueOf(d.getdate(selectedFromList)));
                if (d.getAWert(selectedFromList)>d.getSWert(selectedFromList)){
                    image.setImageResource(R.drawable.smileygruen);
                }
                if (d.getAWert(selectedFromList)<d.getSWert(selectedFromList)){
                    image.setImageResource(R.drawable.smileyrot);
                }
                if (d.getAWert(selectedFromList)==d.getSWert(selectedFromList)){
                    image.setImageResource(R.drawable.smileygelb);
                }
            }
        });
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
