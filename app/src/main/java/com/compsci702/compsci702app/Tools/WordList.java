package com.compsci702.compsci702app.Tools;


import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;
import com.compsci702.compsci702app.Tools.DBHelper;

import java.util.ArrayList;

public class WordList {

    private ArrayList<String> wordList = new ArrayList<>();
    private DBHelper dbHelper;
    private int listLength;

    public WordList( int listLength){
        this.listLength = listLength;
        getWordListFromDatabase(this.listLength);
    }

    private ArrayList<String> getWordListFromDatabase(int listLength){
        //rb /Dummy data
        //rb read from database
        Cursor cursor = dbHelper.alldata();
        if (cursor.getCount() == 0){
            wordList.add("no data");
        }
        else {
            while (cursor.getCount() < (listLength + 1) && cursor.moveToNext()){
              wordList.add(cursor.getString(1));
            }
        }
        //Emily
        //wordList.add("Word");
        //wordList.add("Jumper");
        return wordList;
    }

    public String getWord(int i){ return wordList.get(i); }

}
