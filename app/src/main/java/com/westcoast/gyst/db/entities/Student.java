package com.westcoast.gyst.db.entities;

import com.orm.SugarRecord;

public class Student extends SugarRecord {
    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getEmail() {
        return email;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    String vorname;
    String nachname;
    String email;
    int courseId;


    public Student(String v, String n, String e){
        this.vorname = v;
        this.nachname = n;
        this.email = e;
    }

    public Student(String v, String n, String e, int c){
        this.vorname = v;
        this.nachname = n;
        this.email = e;
        this.courseId = c;
    }


    public Student(){

    }

}
