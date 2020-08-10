package com.westcoast.gyst.db.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

public class Course extends SugarRecord {


    String fach;
    String klasse;
    String zeit;


    public Course(){

    }

    public Course(String f, String k , String z){
        this.fach = f;
        this.klasse = k;
        this.zeit = z;
    }

    public void setFach(String f) { this.fach = f; }

    public void setKlasse(String k) { this.klasse = k; }

    public void setZeit(String z) { this.zeit = z; }

    public String getFach(){
        return fach;
    }

    public String getKlasse(){
        return klasse;
    }

    public String getZeit(){
        return zeit;
    }
}
