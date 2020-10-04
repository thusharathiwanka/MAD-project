package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelperProfile extends SQLiteOpenHelper {
    public static final String DBNAME = "CodeLearnerDB.db";

    public DBHelperProfile(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS students (student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_email TEXT, student_username TEXT, student_password TEXT, student_favourites TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS admins (admin_id INTEGER PRIMARY KEY AUTOINCREMENT, admin_name TEXT, admin_email TEXT, admin_username TEXT, admin_password TEXT, admin_subjects TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS students");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS admins");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS question_table");
        onCreate(sqLiteDatabase);
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM students WHERE student_email = ?", new String[] {email});

        return cursor.getCount() > 0;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM students WHERE student_username = ?", new String[] {username});

        return cursor.getCount() > 0;
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
        return result != -1;
    }

    public boolean studentLoginCheck(String username, String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM students WHERE student_username = ? AND student_password = ?", new String[] {username, password});

        return cursor.getCount() > 0;
    }

    public Cursor getStudentInfo(String userName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM students WHERE student_username = ?", new String[] {userName});

        return cursor;
    }

    public boolean updateStudent(String currentUsername, String email, String username, String password, String favourites) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("STUDENT_EMAIL", email);
        contentValues.put("STUDENT_USERNAME", username);
        contentValues.put("STUDENT_PASSWORD", password);
        contentValues.put("STUDENT_FAVOURITES", favourites);

        long result = sqLiteDatabase.update("students", contentValues, "student_username = ?", new String[] {currentUsername});
        return result != -1;
    }

    public int deleteStudent(String username) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.delete("students", "student_username = ?", new String[] {username});
    }

    public boolean checkEmailForget(String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM students WHERE student_email = ?", new String[] {email});

        return cursor.getCount() > 0;
    }


    public boolean updatePassword(String emailInput, String newPassword) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("student_password", newPassword);

        long result = sqLiteDatabase.update("students", contentValues, "student_email = ?", new String[] {emailInput});
        return result != -1;
    }

    public boolean insertAdmins(String name, String email, String username, String password, String subjects) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ADMIN_NAME", name);
        contentValues.put("ADMIN_EMAIL", email);
        contentValues.put("ADMIN_USERNAME", username);
        contentValues.put("ADMIN_PASSWORD", password);
        contentValues.put("ADMIN_SUBJECTS", subjects);

        long result = sqLiteDatabase.insert("admins", null, contentValues);
        return result != -1;
    }

    public boolean adminLoginCheck(String username, String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM admins WHERE admin_username = ? AND admin_password = ?", new String[] {username, password});

        return cursor.getCount() > 0;
    }

    public Cursor viewAllStudents() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM students", null);

        return cursor;
    }
}
