package com.compsci702.compsci702app.Tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SentenceList {

    private ArrayList<String> wordList;
    private int listLength;
    private Context context;
    private SQLiteDatabase db;

    public SentenceList(int listLength, Context context){
        this.listLength = listLength;
        this.context = context;
        wordList = getWordListFromDatabase(this.listLength);
    }

    private ArrayList<String> getWordListFromDatabase(int listLength){
;
        db = new DBHelper(context).getWritableDatabase();
        ArrayList<String> wordList = new ArrayList<>();

        //Instead of getting all data, only get listed number
        Cursor cursor = new DBHelper(context).getDataFromDatabase(listLength);
        while (cursor.moveToNext()){
           wordList.add(cursor.getString(1));
        }
        return wordList;
    }

    public String getWord(int i){ return wordList.get(i); }

}
