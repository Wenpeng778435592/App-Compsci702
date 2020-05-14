package com.compsci702.compsci702app.Level;

import android.content.Context;

import com.compsci702.compsci702app.Tools.SentenceList;

public class Level3 extends Level{

    public Level3(Context context) {
        super(context);
        this.levelType = LevelType.LEVEL_3;
        this.levelText = "TGV2ZWwgMyBvZiAz";
        this.levelDescription = "VW5zY3JhbWJsZSA4IHdvcmRzIHdpdGhpbiB0aGUgbWludXRlIHRvIHdpbiB0aGlzIGdhbWU=";
        this.levelFinishedDescription = "V293LCB5b3UgbWFkZSBpdCB0byB0aGUgZW5kLiBDb25ncmF0dWxhdGlvbnM=";
        this.wordCountGoal = 8;
        this.levelNumber = 3;
        sentenceList = new SentenceList(wordCountGoal, context);
        targetSentence = sentenceList.getWord(currentWordCount);
    }
}