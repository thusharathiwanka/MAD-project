package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String ADD_LESSONS = "ADD_LESSONS";
    public static final String COLUMN_LESSON_NAME = "LESSON_NAME";
    public static final String COLUMN_CONTENT = "CONTENT";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "CodeLearnerDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatment = "CREATE TABLE " + ADD_LESSONS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LESSON_NAME + " TEXT, " + COLUMN_CONTENT + " TEXT)";
        db.execSQL(createTableStatment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(AdminModel adminModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();//stores data in paires

        cv.put(COLUMN_LESSON_NAME, adminModel.getLname());
        cv.put(COLUMN_CONTENT, adminModel.getLcontent());

        long insert = db.insert(ADD_LESSONS, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }
}
