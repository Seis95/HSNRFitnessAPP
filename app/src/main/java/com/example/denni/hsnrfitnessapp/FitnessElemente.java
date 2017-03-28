package com.example.denni.hsnrfitnessapp;

public class FitnessElemente {


    double Höchstwert;
    double Aktuellerwert;
    double Startwert;
    String Name;


    public double getHöchstwert() {
        return Höchstwert;
    }
    public double getAktuellerwert() {
        return Aktuellerwert;
    }
    public double getStartwert() {
        return Startwert;
    }
    public void setAktuellerwert(double wert) {
        Aktuellerwert= wert;
    }
    public void setHöchstwert(double wert) {
        Höchstwert= wert;
    }
    public void setStartwert(double wert) {
        Startwert= wert;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }


}
