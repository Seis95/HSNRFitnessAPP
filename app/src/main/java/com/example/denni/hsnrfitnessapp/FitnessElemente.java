package com.example.denni.hsnrfitnessapp;

public class FitnessElemente {


    int Höchstwert;
    int Aktuellerwert;
    int Startwert;
    String Name;


    public int getHöchstwert() {
        return Höchstwert;
    }
    public int getAktuellerwert() {
        return Aktuellerwert;
    }
    public int getStartwert() {
        return Startwert;
    }
    public void setAktuellerwert(int wert) {
        Aktuellerwert= wert;
    }
    public void setHöchstwert(int wert) {
        Höchstwert= wert;
    }
    public void setStartwert(int wert) {
        Startwert= wert;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }


}
