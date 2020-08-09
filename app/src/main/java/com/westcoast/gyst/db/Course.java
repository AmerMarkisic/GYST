package com.westcoast.gyst.db;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

public class Course extends SugarRecord {


    String fach;
    String klasse;
    String zeit;


    @Ignore
    boolean showMenu = false;

    public Course(){

    }


    public void setShowMenu(boolean b){
        this.showMenu = b;
    }

    public boolean isShowMenu(){
        return this.showMenu;
    }

    public Course(String f, String k , String z){
        this.fach = f;
        this.klasse = k;
        this.zeit = z;
    }

    public String getFach(){
        return fach;
    }

    public String getKlasse(){
        return klasse;
    }

    public String getZeit(){
        return zeit;
    }

    public List<Student> getStudents(){
        return  Student.find(Student.class, "course = ?", getId().toString());
    }
}
