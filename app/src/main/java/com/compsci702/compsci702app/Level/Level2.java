package com.compsci702.compsci702app.Level;

import android.content.Context;

import com.compsci702.compsci702app.Tools.SentenceList;

public class Level2 extends Level{

    public Level2(Context context){
        super(context);
        this.levelType = LevelType.LEVEL_2;
        this.levelText = "TGV2ZWwgMiBvZiAz";
        this.levelDescription = "VW5zY3JhbWJsZSA1IHNlbnRlbmNlcyB3aXRoaW4gdGhlIG1pbnV0ZSB0byBwYXNzIHRoaXMgbGV2ZWw=";
        this.levelFinishedDescription = "QW5vdGhlciBsZXZlbCBkb3duLCBvbmUgc3RlcCBjbG9zZXIgdG8gdmljdG9yeQ==";
        this.wordCountGoal = 5;
        this.levelNumber = 2;
        sentenceList = new SentenceList(wordCountGoal, context);
        targetSentence = sentenceList.getWord(currentWordCount);
    }
}
