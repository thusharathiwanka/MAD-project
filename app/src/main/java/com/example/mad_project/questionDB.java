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

    public static final String DATABASE_NAME = "CodeLearnerDB.db";
    public static final String TABLE_NAME = "question_table";
    public static final String col_1 = "ID";
    public static final String col_2 = "Email";
    public static final String col_3 = "Module";
    public static  final String col_4 = "Question";
    public static  final String col_5 = "Answer";

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
    //Count the questions.
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

    //Get all questions.
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
                //String Answer = cursor.getString(4);

                Question ques1 = new Question(ID,Email,Module,Question);
                ques2.add(ques1);

            }while(cursor.moveToNext());
        }else{

        }
        cursor.close();
        db2.close();
        return ques2;
    }

    //Delete questions.
    public void deleteQuestions(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,col_1 +" =?",new String[]{String.valueOf(id)});
        db.close();
    }

    //Get a single Question to edit question.
    public Question getSingleQuestion(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor2 = db.query(TABLE_NAME,new String[]{col_1,col_2,col_3,col_4},col_1 + "= ?",new String[]{String.valueOf(id)},null,null,null);
        Question question;
        if(cursor2 != null){
            cursor2.moveToFirst();
            question = new Question(
                    cursor2.getInt(0),
                    cursor2.getString(1),
                    cursor2.getString(2),
                    cursor2.getString(3)
            );
            return question;
        }
        return null;
    }

    //Update a single Question to edit question.
    public int updateQuestion(Question question){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,question.getEmail());
        contentValues.put(col_3,question.getModule());
        contentValues.put(col_4,question.getQuestion());

        int status = db.update(TABLE_NAME,contentValues,col_1 +" =?",new String[]{String.valueOf(question.getId())});

        db.close();
        return status;

    }

    //Set Answer
    public int setAnswer(Question question){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(col_2,question.getEmail());
        contentValues.put(col_3,question.getModule());
        contentValues.put(col_4,question.getQuestion());
        contentValues.put(col_5,question.getAnswer());

        int status = db.update(TABLE_NAME,contentValues,col_1 +" =?",new String[]{String.valueOf(question.getId())});

        db.close();
        return status;

    }

    //Display Answer
    public List<Question2> getAllAnswers(){
        List<Question2> returnList = new ArrayList<>();
        String query = "SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String email = cursor.getString(1);
                String module = cursor.getString(2);
                String question = cursor.getString(3);
                String answer = cursor.getString(4);

                Question2 question1 = new Question2(id,email,module,question,answer);
                returnList.add(question1);
            }while(cursor.moveToNext());
        }else{
            //If it is faile do not display.
        }
        cursor.close();
        db.close();
        return returnList;
    }

    //Get a single answer to edit.
    public Question2 getSingleAnswer(int id1){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{col_1,col_2,col_3,col_4,col_5},col_1 + "= ?",new String[]{String.valueOf(id1)},null,null,null);
        Question2 question;
        if(cursor != null){
            cursor.moveToFirst();
            question = new Question2(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            return question;
        }
        return null;

    }

    //Update Answer
    public int answerUpdate(Question2 question){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues =new ContentValues();
        contentValues.put(col_2,question.getEmail());
        contentValues.put(col_3,question.getModule());
        contentValues.put(col_4,question.getQuestion());
        contentValues.put(col_5,question.getAnswer());

        int status = db.update(TABLE_NAME,contentValues,col_1 +" =?",new String[]{String.valueOf(question.getId())});

        db.close();
        return status;
    }



 }
