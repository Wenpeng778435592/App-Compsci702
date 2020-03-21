package com.compsci702.compsci702app;

public class Level {

    private String levelText;
    private String levelDescription;
    private int wordCountGoal;
    private int currentWordCount;

    public Level(String levelText,String levelDescription, int wordCountGoal){

        this.levelText = levelText;
        this.levelDescription = levelDescription;
        this.wordCountGoal = wordCountGoal;
        currentWordCount = 0;
    }

    public String getLevelText(){ return levelText; }

    public String getLevelDescription(){ return levelDescription; }

    public int getWordCountGoal(){ return wordCountGoal; }

    public int getCurrentWordCount(){ return currentWordCount; }

    public int incrementWordCount(){ return currentWordCount++; }

}



