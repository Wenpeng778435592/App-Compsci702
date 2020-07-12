package com.compsci702.compsci702app.Tools;

import android.graphics.Color;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class ScoreManager {
    TextView scoreText;
    TextView bonusText;

    int score = 0;

    int scorePerCorrectPhrase = 100;
    int halfTimeBonus = 50;
    int thirdTimeBonus = 25;

    public ScoreManager(TextView scoreText, TextView bonusText) {
        this.scoreText = scoreText;
        this.bonusText = bonusText;
        scoreText.setText(String.valueOf(score));
    }

    public void addScore(int millisecondsLeft, int timePerPhrase) {
        if (millisecondsLeft > timePerPhrase*0.5) {
            score += scorePerCorrectPhrase + halfTimeBonus;
            bonusText.setTextColor(Color.parseColor("#50c878"));
            bonusText.setText("+50 time bonus!");
            fadeAnimation();
        } else if (millisecondsLeft > timePerPhrase*0.25) {
            score += scorePerCorrectPhrase + thirdTimeBonus;
            bonusText.setTextColor(Color.parseColor("#FFC107"));
            bonusText.setText("+25 time bonus!");
            fadeAnimation();
        } else {
            score += scorePerCorrectPhrase;
        }
        scoreText.setText(String.valueOf(score));
    }

    private void fadeAnimation() {
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;
        bonusText.startAnimation(fadeIn);
        bonusText.startAnimation(fadeOut);
        fadeIn.setDuration(300);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(300);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(1000);
    }

    public int getScore(){
        return score;
    }
}