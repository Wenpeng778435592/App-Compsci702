package com.compsci702.compsci702app.Level;

import android.content.Context;

import com.compsci702.compsci702app.Tools.SentenceList;

public class Level2 extends Level{

    public Level2(Context context){
        super(context);
        this.levelType = LevelType.LEVEL_2;
        this.levelText = "Level 2 of 3";
        this.levelDescription = "Unscramble 10 words within the minute to pass this level";
        this.wordCountGoal = 8;
        this.levelNumber = 2;
        sentenceList = new SentenceList(wordCountGoal, context);
        targetSentence = sentenceList.getWord(currentWordCount);
    }
}
