package com.compsci702.compsci702app.Tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.compsci702.compsci702app.Activity.OutputProcess;
import com.compsci702.compsci702app.Tools.DBHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SentenceList {

    private ArrayList<String> wordList = new ArrayList<>();
    private DBHelper dbHelper;
    private int listLength;
    private Context context;
    private SQLiteDatabase db;

    public SentenceList(int listLength, Context context){
        this.listLength = listLength;
        this.context = context;
        wordList = getWordListFromDatabase(this.listLength);
    }

    private ArrayList<String> getWordListFromDatabase(int listLength){

        //context.deleteDatabase("WordBank.db");
        db = new DBHelper(context).getWritableDatabase();
        ArrayList<String> wordList = new ArrayList<>();

        //Instead of getting all data, only get listed number
        Cursor cursor = new DBHelper(context).getDataFromDatabase(listLength);
        while (cursor.moveToNext()){
           wordList.add(cursor.getString(1));

            OutputProcess op = new OutputProcess();
            String decryptedPlainText;
            //decryptedPlainText = op.decrypt(byteCipherText);
            decryptedPlainText = op.decrypt(cursor.getBlob(1));
            //System.out.println("decryptPlainText " + decryptedPlainText);

//         wordList.add(cursor.getString(1));
            wordList.add(decryptedPlainText);
        }
        return wordList;

    }


    public String getWord(int i){ return wordList.get(i); }

}
