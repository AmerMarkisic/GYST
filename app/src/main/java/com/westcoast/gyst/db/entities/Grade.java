package com.westcoast.gyst.db.entities;

import com.orm.SugarRecord;

public class Grade extends SugarRecord {

    private String beschreibung;
    private String note;
    private int schuelerId;

    public Grade() {
    }

    public Grade(String beschreibung, int schuelerId) {
        this.beschreibung = beschreibung;
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

    public boolean setNote(String note) {
        try {
            double parsedVal = Double.parseDouble(note);
            if (Double.isNaN(parsedVal) || parsedVal < 1 || parsedVal > 6) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        this.note = note;
        return true;
    }
}
