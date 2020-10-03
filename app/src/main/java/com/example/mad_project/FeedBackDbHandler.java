package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class FeedBackDbHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "CodeLearnerDB.db";
    private static final String TABLE_NAME = "feedbacks";

    // Column names
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    public FeedBackDbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE + " TEXT,"
                +DESCRIPTION + " TEXT,"
                +STARTED+ " TEXT,"
                +FINISHED+" TEXT" +
                ");";

        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        // Drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        // Create tables again
        onCreate(db);
    }

    // Add a single feedback
    public void addFeedBack(FeedBack feedBack){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,feedBack.getTitle());
        contentValues.put(DESCRIPTION, feedBack.getDescription());
        contentValues.put(STARTED,feedBack.getStarted());
        contentValues.put(FINISHED,feedBack.getFinished());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        // close database
        sqLiteDatabase.close();
    }

    // Count feedback table records
    public int countFeedBack(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    // Get all feedbacks into a list
    public List<FeedBack> getAllFeedBacks(){

        List<FeedBack> feedBacks = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                // Create new feedback object
                FeedBack feedBack = new FeedBack();

                feedBack.setId(cursor.getInt(0));
                feedBack.setTitle(cursor.getString(1));
                feedBack.setDescription(cursor.getString(2));
                feedBack.setStarted(cursor.getLong(3));
                feedBack.setFinished(cursor.getLong(4));

                feedBacks.add(feedBack);
            }while (cursor.moveToNext());
        }
        return feedBacks;
    }

    // Delete item
    public void deleteFeedBack(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        db.close();
    }




}