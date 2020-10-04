package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ADD_LESSONS);
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

    public boolean deleteOne(AdminModel adminModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ADD_LESSONS+ " WHERE " + COLUMN_ID+ " = " + adminModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public List<AdminModel> getData(){
        List<AdminModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + ADD_LESSONS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            //loop through the results
            do{
                int ID = cursor.getInt(0);
                String lessonName = cursor.getString(1);
                String content = cursor.getString(2);

                AdminModel newLesson = new AdminModel(ID, lessonName, content);
                returnList.add(newLesson);

            }while(cursor.moveToNext());

        }else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public AdminModel getSingleAdminModel(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(ADD_LESSONS, new String[]{COLUMN_ID, COLUMN_LESSON_NAME, COLUMN_CONTENT}, COLUMN_ID + "= ?", new String[]{String.valueOf(id)}, null,null,null);

        AdminModel adminModel;
        if(cursor != null){
            cursor.moveToFirst();
            adminModel = new AdminModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)

                    );
            return adminModel;
        }
        return null;
    }

    public int update(AdminModel adminModel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LESSON_NAME, adminModel.getLname());
        cv.put(COLUMN_CONTENT, adminModel.getLcontent());

        int status = db.update(ADD_LESSONS, cv, COLUMN_ID+" =?",
                new String[]{String.valueOf(adminModel.getId())});

        db.close();
        return status;
    }

}
