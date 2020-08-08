package com.westcoast.gyst.db.grades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.westcoast.gyst.db.KursverwaltungsDb;

public class GradeSchemaSQL implements GradeColumns {
    public static final String TABLE_NAME = "noten";

    private KursverwaltungsDb db;
    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrite;
    private Cursor cursor;

    public GradeSchemaSQL(Context context){
        db = KursverwaltungsDb.getInstance(context);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
    }

    public static final String[] TABLE_COLUMNS = new String[] {ID, BESCHREIBUNG, NOTE, SCHUELERID};

    public Cursor showTable (String tableName, String[] tableColumns, String sort){
        cursor = dbRead.query(tableName, tableColumns, null, null, null, null, sort);
        return cursor;
    }

    public Cursor showRecord(String tableName, String[] tableColumns, long id){
        cursor = dbRead.query( tableName, tableColumns, GradeSchemaSQL.ID +"="+id, null, null, null, null);
        return cursor;
    }

    public long insertTable(String tableName, ContentValues data){
        long id = dbRead.insert(tableName, null, data);
        return id;
    }

    public void changeRecord(String tableName, ContentValues data, long id){
        dbRead.update( tableName, data, GradeSchemaSQL.ID+"="+id, null);
    }

    public void deleteRecord(String tableName, long id){
        dbWrite.delete(tableName, GradeSchemaSQL.ID+"="+id, null);
    }

    public Cursor showRecordsForStudent(String tableName, String[] tableColumns, long studentId){
        cursor = dbRead.query(tableName, tableColumns, GradeSchemaSQL.SCHUELERID+"="+studentId, null, null, null, null);
        return cursor;
    }

    public void close(){
        db.close();
        dbRead.close();
        dbWrite.close();
    }

    public static final String SQL_CREATE =
            "CREATE TABLE "+TABLE_NAME+
                    "("+
                    ID+ "INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    BESCHREIBUNG+ "TEXT NOT NULL,"+
                    NOTE+ "TEXT NOT NULL,"+
                    SCHUELERID+ "INTEGER NOT NULL"+
                    ");";

    public static final String SQL_DROP =
            "DROP TABLE IF EXISTS "+TABLE_NAME;




}
