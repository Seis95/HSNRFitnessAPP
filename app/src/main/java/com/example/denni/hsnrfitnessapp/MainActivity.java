package com.example.denni.hsnrfitnessapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Denni on 15.02.2017.
 */

public class MainActivity extends AppCompatActivity implements Adapter.listener {
    String mydate;
    String Path="";
    RecyclerView rv;
    ArrayList Array1;
    List<FitnessElemente> fitnessElementes;
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) this.findViewById(R.id.recyclerView);
        TextView Datum = (TextView)this.findViewById(R.id.textView7);
        mydate = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        Datum.setText(mydate);
        File myDir = getFilesDir();
        Path =myDir.toString();

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
        List();
    }
    private void List() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        Array1 = new ArrayList<String>();
        getDay();
        fitnessElementes = new ArrayList();
        DatabaseHandler d = new DatabaseHandler(this);
        for (int i = 0; Array1.size() >i; i++){
            Log.d("TEST",Array1.get(i).toString());
            addtoList(Array1.get(i).toString(), d.getAWert(Array1.get(i).toString()),d.getHWert(Array1.get(i).toString()));

    }

        setadapter(fitnessElementes);
    }
    private void setadapter(List<FitnessElemente> fitnessElementes) {
        adapter = new Adapter(fitnessElementes, this);
        rv.setAdapter(adapter);
    }


    public List<FitnessElemente> addtoList(String Name, double Wert1, double Wert2){

        {
            FitnessElemente fitnessElemente = new FitnessElemente();
            fitnessElemente.setName(Name);
            fitnessElemente.setAktuellerwert(Wert1);
            fitnessElemente.setHöchstwert(Wert2);
            this.fitnessElementes.add(fitnessElemente);
        }

        return fitnessElementes;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
        // Wir prüfen, ob Menü-Element mit der ID "action_daten_aktualisieren"
        // ausgewählt wurde und geben eine Meldung aus
        int id = item.getItemId();
        if (id == R.id.Informationen) {
            Toast.makeText(getApplicationContext(), "Code und Design: Dennis Maus   Dokumentation und Organisation: Tanja Schneider", Toast.LENGTH_LONG).show();

            return true;
        }
        if (id == R.id.Hinzufügen) {
            Intent in = new Intent(MainActivity.this, Add.class);
            startActivity(in);
            return true;
        }
        if (id == R.id.Statistiken) {
            Intent in = new Intent(MainActivity.this, Statistik.class);
            startActivity(in);
            return true;
        }
        if (id == R.id.Reset) {
            try {
                reset();
                DatabaseHandler d = new DatabaseHandler(this);
                d.reset();
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
                Intent in = new Intent(MainActivity.this, MainActivity.class);
                startActivity(in);
            }catch(Exception e){

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        logFile = new File(Path,file);
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
        logFile = new File(Path,file);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
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

    public void reset() throws Exception{
       File logFile = null;
        logFile = new File(Path,"Ausdauer.txt");
        logFile.delete();
        logFile.createNewFile();
        logFile = new File(Path,"Geräte.txt");
        logFile.delete();
        logFile.createNewFile();
        logFile = new File(Path,"Übungen.txt");
        logFile.delete();
        logFile.createNewFile();
        logFile = new File(Path,"Montag.txt");
        logFile.delete();
        logFile = new File(Path,"Dienstag.txt");
        logFile.delete();
        logFile = new File(Path,"Mittwoch.txt");
        logFile.delete();
        logFile = new File(Path,"Donnerstag.txt");
        logFile.delete();
        logFile = new File(Path,"Freitag.txt");
        logFile.delete();
        logFile = new File(Path,"Samstag.txt");
        logFile.delete();
        logFile = new File(Path,"Sonntag.txt");
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
    public void kalender(View v){
        Intent in = new Intent(MainActivity.this, Kalender.class);

        startActivity(in);
    }

    public void getDay(){

        Log.d("SOMETHING","getDay");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {

            case Calendar.MONDAY:
                Log.d("SOMETHING", "Montag");
                // Current day is Monday
                readTextfile(4);
                break;
            case Calendar.TUESDAY:
                Log.d("SOMETHING", "Dienstag");
                // etc.
                readTextfile(5);
                break;
            case Calendar.WEDNESDAY:
                Log.d("SOMETHING", "Mittwoch");
                // etc.
                readTextfile(6);
                break;
            case Calendar.THURSDAY:
                Log.d("SOMETHING", "Donnerstag");
                // etc.
                readTextfile(7);
                break;
            case Calendar.FRIDAY:
                Log.d("SOMETHING", "Freitag");
                // etc.
                readTextfile(8);
                break;
            case Calendar.SATURDAY:
                Log.d("SOMETHING", "Samstag");
                // etc.
                readTextfile(9);
                break;
            case Calendar.SUNDAY:
                Log.d("SOMETHING", "Sonntag");
                // Current day is Sunday
                readTextfile(10);
                break;
        }

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
                Log.d("SOMETHING","plan: "+x);



                Array1.add(x);

                Log.d("SOMETHING","plan array: "+Array1.toString());
                x = r.readLine();
            }
            Log.d("SOMETHING","plan array size: "+Array1.size());

            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onListItemClick(int i) {



    }
}
