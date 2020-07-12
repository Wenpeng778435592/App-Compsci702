package com.compsci702.compsci702app.Tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.compsci702.compsci702app.Data.Phrase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static String DATABASE_NAME = "WordBank.db";
    private static int SCHEMA_NUMBER = 1;
    private final String HIGHSCORE_DIFFICULTY_COL = "Difficulty";
    private final String HIGHSCORE_SCORE_COL = "Score";
    private final String HIGHSCORE_SCORE_TABLE = "HighScores";
    private final String WORD_TABLE = "Words";
    private final String WORD_TABLE_SENTENCE_COL = "Sentence";
    private final String WORD_TABLE_EXAMPLE_COL = "Example";
    private final String WORD_TABLE_MEANING_COL = "Meaning";
    private final String WORD_TABLE_FAV_COL = "Favourited";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,SCHEMA_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Words(Id Integer Primary Key Autoincrement, Sentence text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion) {
            copyDBFromResource();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) { super.onOpen(db); }

    public void createDB(){
        if(!dbExists()){ copyDBFromResource(); }
    }

    public Cursor getRowsFromDatabase(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Words", null);
        return cursor;
    }

    public Cursor getHighScores(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HighScores", null);
        return cursor;
    }

    public Cursor getFavouritePhrases(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WORD_TABLE, null, WORD_TABLE_FAV_COL + " == " + 1,
                null, null,null,null );
        return cursor;
    }

    public void updateHighScore(String difficulty, int score){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(HIGHSCORE_DIFFICULTY_COL, difficulty);
        values.put(HIGHSCORE_SCORE_COL, score);

        db.replace(HIGHSCORE_SCORE_TABLE, null,values);
    }

    public void updateFavourited(Phrase phrase, int favourited){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(WORD_TABLE_SENTENCE_COL, phrase.getPhrase());
        values.put(WORD_TABLE_MEANING_COL, phrase.getMeaning());
        values.put(WORD_TABLE_EXAMPLE_COL, phrase.getExample());
        values.put(WORD_TABLE_FAV_COL, favourited);

        db.replace(WORD_TABLE, null,values);
    }

    private boolean dbExists(){
        File databasePath = context.getDatabasePath(DATABASE_NAME);
        File temp = new File(databasePath.getPath());

        if (temp.exists()){ return true; };

        if (!temp.getParentFile().exists()) { temp.getParentFile().mkdirs(); }

        return false;
    }

    private void copyDBFromResource() {
        InputStream myInput;
        OutputStream myOutput;

        try
        {
            myInput = context.getAssets().open(DATABASE_NAME);
            File path = context.getDatabasePath(DATABASE_NAME);
            myOutput = new FileOutputStream(path);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = myInput.read(buffer)) > 0)
            {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
