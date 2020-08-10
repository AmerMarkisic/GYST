package com.westcoast.gyst.db.entities;

import com.orm.SugarRecord;

public class Student extends SugarRecord {
    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getDurchschnitt() {
        return durchschnitt;
    }

    public String getEmail() {
        return email;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setDurchschnitt(String durchschnitt) {
        this.durchschnitt = durchschnitt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }

    String vorname;
    String nachname;
    String durchschnitt;
    String email;
    int CourseId;





    public Student(){

    }

}
