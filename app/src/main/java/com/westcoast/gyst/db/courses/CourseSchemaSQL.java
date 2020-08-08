package com.westcoast.gyst.db.courses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.westcoast.gyst.db.KursverwaltungsDb;

public class CourseSchemaSQL implements  CourseColumns {
    private static final String TABLE_NAME = "kurse";

    private KursverwaltungsDb db;
    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrite;
    private Cursor cursor;

    public CourseSchemaSQL(Context context){
        db = KursverwaltungsDb.getInstance(context);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
    }

    public static final String[] TB_COLUMNS = new String[] { ID, FACH, KLASSE, ZEIT };

    public Cursor showTable(String tableName, String[] tableColumns, String sort){
        cursor = dbRead.query(tableName, tableColumns, null, null, null, null, sort);
        return cursor;
    }

    public Cursor showRecord(String tableName, String[] tableColumns, long id){
        cursor = dbRead.query(tableName, tableColumns, CourseSchemaSQL.ID + "=" + id, null, null, null, null);
        return cursor;
    }

    public long insertTable (String tableName, ContentValues data){
        long id = dbWrite.insert(tableName, null, data);
        return id;
    }

    public void changeRecord(String tableName, ContentValues data, long id){
        dbWrite.update(tableName, data, CourseSchemaSQL.ID + "=" + id, null);
    }

    public void deleteRecord(String tableName, long id){
        dbWrite.delete(tableName, CourseSchemaSQL.ID + "=" + id, null);
    }

    public void close(){
        dbRead.close();
        dbWrite.close();
        db.close();
    }

    public static final String SQL_CREATE =
            "CREATE TABLE" + TABLE_NAME +
                    "(" +
                    ID+ "INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    FACH+ "TEXT NOT NULL,"+
                    KLASSE+ "TEXT NOT NULL,"+
                    ZEIT+ "TEXT"+
                    ");";

    public static final String SQL_DROP =
            "DROP TABLE IF EXISTS "+TABLE_NAME;
}
