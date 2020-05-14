package com.compsci702.compsci702app.Level;

import android.content.Context;

import com.compsci702.compsci702app.Tools.SentenceList;

public class Level1 extends Level{

    public Level1(Context context){
        super(context);
        this.levelType = LevelType.LEVEL_1;
        this.levelText = "Level 1 of 3";
        this.levelDescription = "Unscramble 6 sentences within the minute to pass this level";
        this.levelFinishedDescription = "One level down, two to go";
        this.wordCountGoal = 6;
        this.levelNumber = 1;
        sentenceList = new SentenceList(wordCountGoal, context);
        targetSentence = sentenceList.getWord(currentWordCount);
    }
}