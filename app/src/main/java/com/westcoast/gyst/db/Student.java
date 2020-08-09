package com.westcoast.gyst.db;

import com.orm.SugarRecord;
import com.westcoast.gyst.db.Course;

public class Student extends SugarRecord {
    String vorname;
    String nachname;
    String durchschnitt;
    String email;
    Course course;

    public Student(){

    }

}
