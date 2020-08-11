package com.westcoast.gyst.db.entities;

import com.orm.SugarRecord;

public class Grade extends SugarRecord {

    String beschreibung;
    String note;
    int schuelerId;

    public Grade() {
    }

    public Grade(String beschreibung, String note, int schuelerId) {
        this.beschreibung = beschreibung;
        this.note = note;
        this.schuelerId = schuelerId;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getNote() {
        return note.isEmpty() ? "1" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getSchuelerId() {
        return schuelerId;
    }

    public void setSchuelerId(int schuelerId) {
        this.schuelerId = schuelerId;
    }
}
