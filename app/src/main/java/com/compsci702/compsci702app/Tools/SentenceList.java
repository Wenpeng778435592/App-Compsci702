package com.compsci702.compsci702app.Tools;

import com.compsci702.compsci702app.Tools.DBHelper;

import java.util.ArrayList;

public class SentenceList {

    private ArrayList<String> wordList = new ArrayList<>();
    private DBHelper dbHelper;
    private int listLength;

    public SentenceList(int listLength){
        this.listLength = listLength;
        getWordListFromDatabase(this.listLength);
    }

    private ArrayList<String> getWordListFromDatabase(int listLength){
        //Dummy data
        wordList.add("This is a sentence that is cool");
        wordList.add("Jumpers are cool");
        wordList.add("Another sentence to jumble");
        wordList.add("Dogs and cats are both pretty cool");
        return wordList;
    }public String getWord(int i){ return wordList.get(i); }

}
