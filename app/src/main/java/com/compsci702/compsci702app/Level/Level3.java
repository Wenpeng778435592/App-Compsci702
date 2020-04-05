package com.compsci702.compsci702app.Level;

import android.content.Context;

public class Level3 extends Level{

    public Level3(Context context) {
        super(context);
        this.levelType = LevelType.LEVEL_3;
        this.levelText = "Level 3 of 3";
        this.levelDescription = "blah blah blah";
        this.wordCountGoal = 2;
        this.levelNumber = 3;
    }
}
