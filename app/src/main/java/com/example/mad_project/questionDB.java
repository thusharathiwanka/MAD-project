package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static android.os.Build.ID;
import static android.provider.Contacts.SettingsColumns.KEY;

public class questionDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "codeLearnerDB.db";
    public static final String TABLE_NAME = "question_table";
    public static final String col_1 = "ID";
    public static final String col_2 = "Email";
    public static final String col_3 = "Module";
    public static  final String col_4 = "Question";

    public questionDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table question_table (ID INTEGER PRIMARY KEY AUTOINCREMENT,Email VARCHAR(255),Module TEXT,Question TEXT,Answer TEXT)");
        System.out.println("Rub");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS question_table");
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String email, String module, String question){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,email);
        contentValues.put(col_3,module);
        contentValues.put(col_4,question);
        long result = db.insert(TABLE_NAME,null,contentValues);

        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res1 = db.rawQuery("select * from " + TABLE_NAME, null);
        return res1;
    }

 }
