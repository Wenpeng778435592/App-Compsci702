package com.compsci702.compsci702app.Level;

import android.content.Context;

import com.compsci702.compsci702app.Tools.MinuteTimer;
import com.compsci702.compsci702app.Tools.Scrambler;
import com.compsci702.compsci702app.Tools.SentenceList;

public abstract class Level {

    protected String levelText;
    protected String levelDescription;
    protected String levelFinishedDescription;
    protected LevelType levelType;
    protected SentenceList sentenceList;
    protected int levelNumber;

    protected int wordCountGoal;
    protected int currentWordCount;
    protected String targetSentence;
    protected String congratulationsText = "TGV2ZWwgUGFzc2VkIQ==";
    protected Scrambler scrambler = new Scrambler();

    public Level(Context context){
        currentWordCount = 0;
    }

    public String getLevelText(){ return levelText; }

    public String getLevelDescription(){ return levelDescription; }

    public String getCongratulationsText(){ return congratulationsText; }

    public String getLevelFinishedDescription(){ return levelFinishedDescription; }

    public int getWordCountGoal(){ return wordCountGoal; }

    public int getCurrentWordCount(){ return currentWordCount; }

    public int getLevelNumber(){ return levelNumber; }

    public String getProgressIndicatorText(){ return String.format("%02d / %02d",currentWordCount ,wordCountGoal); }

    public String getNextSentence(){
        
        targetSentence = sentenceList.getWord(currentWordCount);
        return scrambler.scrambleSentence(targetSentence, this);
    }

    public LevelType getLevelType() { return levelType; }

    public boolean levelFinished(){ return currentWordCount == wordCountGoal; }

    public String getTargetSentence(){ return targetSentence; }

    public boolean checkMatch(String input){

        if (input.equals(targetSentence)){
            currentWordCount ++;
            return true;
        }
        return false;
    }
}



