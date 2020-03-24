package com.compsci702.compsci702app.Level;

import com.compsci702.compsci702app.Tools.WordList;

public abstract class Level {

    protected String levelText;
    protected String levelDescription;
    protected String congratulationsText = "Level Passed!";
    protected String levelFinishedDescription;
    protected int wordCountGoal;
    protected int currentWordCount;
    protected LevelType levelType;

    protected WordList wordList;

    public Level(){
        currentWordCount = 0;
        wordList = new WordList(wordCountGoal);
    }

    public String getLevelText(){ return levelText; }

    public String getLevelDescription(){ return levelDescription; }

    public String getCongratulationsText(){ return congratulationsText; }

    public String getLevelFinishedDescription(){ return levelFinishedDescription; }

    public int getWordCountGoal(){ return wordCountGoal; }

    public int getCurrentWordCount(){ return currentWordCount; }

    public String getProgressIndicatorText(){ return String.format("%02d / %02d",currentWordCount ,wordCountGoal); }

    public String getNextWord(){ return wordList.getWord(currentWordCount); }

    public LevelType getLevelType() { return levelType; }

    public boolean levelFinished(){ return currentWordCount == wordCountGoal; }

    public void wordMatched(){ currentWordCount ++; }
}



