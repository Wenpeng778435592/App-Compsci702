package com.compsci702.compsci702app.Tools;

import android.content.Context;
import android.database.Cursor;

import com.compsci702.compsci702app.Data.Phrase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SentenceExtractor {
    private Phrase targetPhrase;
    private List<Phrase> phrases = new ArrayList<>();

    private Scrambler scrambler = new Scrambler();

    private Context context;
    private String difficulty;

    public SentenceExtractor(Context context, String difficulty){
        this.context = context;
        this.difficulty = difficulty;
        getSentencesFromDatabase();
    }

    public String getNextSentence(){
        targetPhrase = phrases.get(0);
        phrases.remove(targetPhrase);
        return scrambler.scrambleSentence(targetPhrase.getPhrase(), difficulty);
    }

    public String getTargetPhraseString() {
        return targetPhrase.getPhrase();
    }

    public Phrase getTargetPhrase(){
        return targetPhrase;
    }

    public boolean checkMatch(String input){
        if (input.equals(targetPhrase.getPhrase())){
            return true;
        }
        return false;
    }

    private void getSentencesFromDatabase(){
        Cursor cursor = new DBHelper(context).getRowsFromDatabase();
        while (cursor.moveToNext()){
            Phrase phrase = new Phrase();
            phrase.setPhrase(cursor.getString(cursor.getColumnIndex("Sentence")));
            phrase.setMeaning(cursor.getString(cursor.getColumnIndex("Meaning")));
            phrase.setExample(cursor.getString(cursor.getColumnIndex("Example")));
            phrases.add(phrase);
        }
        Collections.shuffle(phrases);
    }

}
