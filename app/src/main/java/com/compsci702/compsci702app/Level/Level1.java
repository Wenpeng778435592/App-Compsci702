package com.compsci702.compsci702app.Level;

public class Level1 extends Level{

    public Level1(){
        this.levelType = LevelType.LEVEL_1;
        this.levelText = "Level 1 of 3";
        this.levelDescription = "Type 4 words within the minute to pass this level";
        this.wordCountGoal = 2;
    }
}