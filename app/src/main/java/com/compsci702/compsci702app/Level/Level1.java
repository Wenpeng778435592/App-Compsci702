package com.compsci702.compsci702app.Level;

import android.content.Context;

public class Level1 extends Level{

    public Level1(Context context){
        super(context);
        this.levelType = LevelType.LEVEL_1;
        this.levelText = "Level 1 of 3";
        this.levelDescription = "Unscramble 8 sentences within the minute to pass this level";
        this.wordCountGoal = 2;
        this.levelNumber = 1;
    }
}