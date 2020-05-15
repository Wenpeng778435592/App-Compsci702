package com.compsci702.compsci702app.Level;

import android.content.Context;

import com.compsci702.compsci702app.Tools.SentenceList;

public class Level1 extends Level{

    public Level1(Context context){
        super(context);
        this.levelType = LevelType.LEVEL_1;
        this.levelText = "TGV2ZWwgMSBvZiAz";
        this.levelDescription = "VW5zY3JhbWJsZSA0IHNlbnRlbmNlcyB3aXRoaW4gdGhlIG1pbnV0ZSB0byBwYXNzIHRoaXMgbGV2ZWw=";
        this.levelFinishedDescription = "T25lIGxldmVsIGRvd24sIHR3byB0byBnbw==";
        this.wordCountGoal = 4;
        this.levelNumber = 1;
        sentenceList = new SentenceList(wordCountGoal, context);
        targetSentence = sentenceList.getWord(currentWordCount);
    }
}