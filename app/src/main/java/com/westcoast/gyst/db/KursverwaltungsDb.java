package com.westcoast.gyst.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.westcoast.gyst.db.courses.CourseSchemaSQL;
import com.westcoast.gyst.db.grades.GradeSchemaSQL;
import com.westcoast.gyst.db.students.StudentSchemaSQL;

public class KursverwaltungsDb extends SQLiteOpenHelper {

    public static final String DATABASENAME = "kursverwaltung.db";
    public static final int DATABASEVERSION = 1;

    private static KursverwaltungsDb INSTANCE;
    private static Object LOCK = "";

    private KursverwaltungsDb(Context context){
        super (context, DATABASENAME, null, DATABASEVERSION);
    }


    public static KursverwaltungsDb getInstance(Context context){
            if (INSTANCE == null){
                synchronized (LOCK){
                    if(INSTANCE == null){
                        INSTANCE = new KursverwaltungsDb(context);
                    }
                }
            }
            return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CourseSchemaSQL.SQL_CREATE);
        db.execSQL(StudentSchemaSQL.SQL_CREATE);
        db.execSQL(GradeSchemaSQL.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(CourseSchemaSQL.SQL_DROP);
        db.execSQL(StudentSchemaSQL.SQL_DROP);
        db.execSQL(GradeSchemaSQL.SQL_DROP);
        onCreate(db);
    }
}
