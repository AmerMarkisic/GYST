package com.westcoast.gyst.db.students;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.westcoast.gyst.db.KursverwaltungsDb;

import java.util.EmptyStackException;

public class StudentSchemaSQL implements StudentColumns {
    public static final String TABLE_NAME = "schueler";

    private KursverwaltungsDb db;
    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrite;
    private Cursor cursor;

    public StudentSchemaSQL(Context context){
        db = KursverwaltungsDb.getInstance(context);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
    }

    public static final String[] TB_COLUMNS = new String[] {ID, VORNAME, NACHNAME, DURCHSCHNITT, EMAIL, KURSID};

    public Cursor showTable (String tableName, String[] tableColumns, String sort){
        cursor = dbRead.query(tableName, tableColumns, null, null, null, null, sort);
        return cursor;
    }

    public Cursor showRecord(String tableName, String[] tableColumns, long id){
        cursor = dbRead.query(tableName, tableColumns, StudentSchemaSQL.ID + "=" + id, null, null, null, null);
        return cursor;
    }

    public long insertTable(String tableName, ContentValues data){
        long id = dbWrite.insert(tableName, null, data);
        return id;
    }

    public void changeRecord(String tableName, ContentValues data, long id){
        dbWrite.update(tableName, data, StudentSchemaSQL.ID + "=" + id, null);
    }

    public void deleteRecord(String tableName, long id){
        dbWrite.delete(tableName, StudentSchemaSQL.ID + "=" + id, null);
    }

    public Cursor showRecordsForCourse(String tableName, String[] tableColumns, long kursId){
        cursor = dbRead.query(tableName, tableColumns, StudentSchemaSQL.KURSID + "=" + kursId, null, null, null, null);
        return cursor;
    }

    public void close(){
        dbRead.close();
        dbWrite.close();
        db.close();
    }

    public static final String SQL_CREATE =
            "CREATE TABLE "+TABLE_NAME+
                    "("+
                    ID+ "INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    VORNAME+ "TEXT NOT NULL,"+
                    NACHNAME+ "TEXT NOT NULL,"+
                    EMAIL+ "TEXT,"+
                    DURCHSCHNITT+ "TEXT NOT NULL,"+
                    KURSID+ "INTEGER NOT NULL"+
                    ");";

    public static final String SQL_DROP =
            "DROP TABLE IF EXISTS "+TABLE_NAME;
}
