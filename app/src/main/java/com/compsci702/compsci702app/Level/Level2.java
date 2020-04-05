package com.compsci702.compsci702app.Level;

import android.content.Context;

public class Level2 extends Level{

    public Level2(Context context){
        super(context);
        this.levelType = LevelType.LEVEL_2;
        this.levelText = "Level 2 of 3";
        this.levelDescription = "Type 50 words within the minute to pass this level";
        this.wordCountGoal = 2;
        this.levelNumber = 2;
    }
}
