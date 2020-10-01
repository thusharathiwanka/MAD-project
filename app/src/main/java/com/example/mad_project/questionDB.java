package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;
import static android.provider.Contacts.SettingsColumns.KEY;

public class questionDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "codeLearnerDB.db";
    public static final String TABLE_NAME = "question_table";
    public static final String col_1 = "ID";
    public static final String col_2 = "Email";
    public static final String col_3 = "Module";
    public static  final String col_4 = "Question";
    public  static  final String col_5 = "Answer";

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

    public void add(Question ques){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,ques.getEmail());
        contentValues.put(col_3,ques.getModule());
        contentValues.put(col_4,ques.getQuestion());

        //Save the data to the table.
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        ///close thw database
        sqLiteDatabase.close();
    }


    /*public boolean insertData(String email, String module, String question){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,email);
        contentValues.put(col_3,module);
        contentValues.put(col_4,question);
        long result = db.insert(TABLE_NAME,null,contentValues);

        return result != -1;
    }*/

    public int countQuestion(){
        SQLiteDatabase db = getWritableDatabase();
        String Query = "SELECT * From "+TABLE_NAME;

        Cursor cursor =  db.rawQuery(Query,null);
        return cursor.getCount();
    }

    /*public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res1 = db.rawQuery("select * from " + TABLE_NAME, null);
        return res1;
    }*/

    public List<Question> getAllData(){
        List<Question> ques2 = new ArrayList<>();
        String query = "SELECT* FROM "+TABLE_NAME ;
        SQLiteDatabase db2 = getReadableDatabase();


        Cursor cursor = db2.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                int ID = cursor.getInt(0);
                String Email = cursor.getString(1);
                String Module = cursor.getString(2);
                String Question = cursor.getString(3);

                Question ques1 = new Question(ID,Email,Module,Question);
                ques2.add(ques1);

            }while(cursor.moveToNext());
        }else{

        }
        cursor.close();
        db2.close();
        return ques2;
    }


 }
