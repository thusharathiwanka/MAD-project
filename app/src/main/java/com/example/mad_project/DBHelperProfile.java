package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelperProfile extends SQLiteOpenHelper {
    public static final String DBNAME = "CodeLearner.db";

    public DBHelperProfile(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE students (student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_email TEXT, student_username TEXT, student_password TEXT, student_favourites TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS students");
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM students WHERE student_email = ?", new String[] {email});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM students WHERE student_username = ?", new String[] {username});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertStudents(String email, String username, String password, ArrayList<String> favourites) {
        StringBuffer stringBuffer = new StringBuffer();
        for(String favourite: favourites) {
            stringBuffer.append(favourite);
            stringBuffer.append(", ");
        }
        String favourite = stringBuffer.toString();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("STUDENT_EMAIL", email);
        contentValues.put("STUDENT_USERNAME", username);
        contentValues.put("STUDENT_PASSWORD", password);
        contentValues.put("STUDENT_FAVOURITES", favourite);

        long result = sqLiteDatabase.insert("students", null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean studentLoginCheck(String username, String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM students WHERE student_username = ? AND student_password = ?", new String[] {username, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
