package com.compsci702.compsci702app.Tools;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import com.compsci702.compsci702app.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SentenceProcessor {

    Context context;

    public SentenceProcessor(Context context) {
        this.context = context;
    }

    public SpannableStringBuilder getColourMatchedString(String currentText, String scrambledText, String targetText){

        SpannableStringBuilder scrambledSpannableString = new SpannableStringBuilder(getNewDisplayString(currentText,scrambledText,targetText));

        int mistakeIndex = getFirstMistake(currentText, targetText);
        if(mistakeIndex != -1){

            ForegroundColorSpan fcsgreen = new ForegroundColorSpan(context.getResources().getColor(R.color.green));
            ForegroundColorSpan fcsred = new ForegroundColorSpan(context.getResources().getColor(R.color.red));

            scrambledSpannableString.setSpan(fcsgreen, 0, mistakeIndex,Spanned.SPAN_INCLUSIVE_INCLUSIVE );
            scrambledSpannableString.setSpan(fcsred, mistakeIndex, mistakeIndex+1,Spanned.SPAN_INCLUSIVE_INCLUSIVE );

        }else if (currentText.length() == 0){
            ForegroundColorSpan fcsgrey = new ForegroundColorSpan(context.getResources().getColor(R.color.darkGrey));
            scrambledSpannableString.setSpan(fcsgrey,0,currentText.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }else{
            ForegroundColorSpan fcsgreen = new ForegroundColorSpan(context.getResources().getColor(R.color.green));
            scrambledSpannableString.setSpan(fcsgreen,0,currentText.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return scrambledSpannableString;
    }

    //If user unscrambles right character at the right index, put that character at the correct index
    //and shuffle all characters in the word left by 1
    private String shuffleText(String currentText, String scrambledText, int i){

        char currentChar = currentText.charAt(i);
        int indexOfScrambled = scrambledText.indexOf(currentChar,i);

        char[] scrambledArray = scrambledText.toCharArray();
        char lastIndexChar = scrambledArray[i];

        for (int k = i + 1 ; k <= indexOfScrambled ; k++){
            char temp = scrambledArray[k];
            scrambledArray[k] = lastIndexChar;
            lastIndexChar = temp;
        }
        scrambledArray[i] = currentChar;
        scrambledText = new String(scrambledArray);

        return scrambledText;
    }

    //Rearranges the order of the characters in the sentence displayed if the correctly unscrambles a
    //charater in the sentence.
    private String getNewDisplayString(String currentText, String scrambledText, String targetText){
        if(currentText.length() > 0 && currentText.length() < targetText.length()) {
            char lastChar = currentText.charAt(currentText.length() - 1);

            if (lastChar == targetText.charAt(currentText.length() - 1) &&
                    lastChar != scrambledText.charAt(currentText.length() - 1)) {
                
                return shuffleText(currentText, scrambledText, currentText.length() - 1);
            }
        }
        return scrambledText;
    }

    //Finds the index of the first character that doesn't match the sentence from the database
    private int getFirstMistake(String currentText, String targetText){
        int mistakeIndex = -1;
        for (int i = 0; i< currentText.length(); i++){
            if(targetText.charAt(i) != currentText.charAt(i)){
                return i;
            }
        }
        return mistakeIndex;
    }

    private int getFirstMistakeClone(String currentText, String targetText){
        int mistakeIndex = -1;
        boolean mistakeFound = false;
        int i = 0;
        while (!mistakeFound){
            if(i >= currentText.length()){
                break;
            }
            if(targetText.charAt(i) != currentText.charAt(i)){
                mistakeIndex = i;
                mistakeFound = true;
            }
            i++;
        }
        return mistakeIndex;
    }

    private int getFirstMistakeClone2(String currentText, String targetText){
        String[] currentTextArray = currentText.split("");
        String[] targetTextArray = targetText.split("");

        ArrayList<String> listCurrent = new ArrayList<String>(Arrays.asList(currentTextArray));
        ArrayList<String> listTarget = new ArrayList<String>(Arrays.asList(targetTextArray));
        int i = 0;

        for(String s : listCurrent){
            if (!s.equals(listTarget.get(i))){
                return i - 1;
            }
            i++;
        }
        return -1;
    }
}