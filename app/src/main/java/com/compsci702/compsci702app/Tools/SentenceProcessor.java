package com.compsci702.compsci702app.Tools;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import com.compsci702.compsci702app.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SentenceProcessor {
    //    OBF declared variables start
    int max_num = 25; //OBF max bound for random number
    int min_num = 5; //OBF min bound for random number
    int max_mis_num = 80; //OBF max bound for random word number
    int min_mis_num = 10; //OBF min bound for random word number
    int max_mis_count = 100; //OBF max bound for random word number
    int min_mis_count = 20; //OBF min bound for random word number
    int max_mis_index = 100; //OBF max bound for random word number
    int min_mis_index = 0; //OBF min bound for random word number



    int scrambleNumber =min_num + (int)(Math.random() * ((max_num - min_num) + 1));
    int misNumber = min_mis_num + (int)(Math.random() * ((max_mis_num - min_mis_num) + 1));
    int misCount = min_mis_count + (int)(Math.random() * ((max_mis_count - min_mis_count) + 1));
    int misIndex = min_mis_index + (int)(Math.random() * ((max_mis_index - min_mis_index) + 1));
//    OBF declared variables end

    Context context;

    public SentenceProcessor(Context context) {
        this.context = context;
    }

    public SpannableStringBuilder getColourMatchedString(String currentText, String scrambledText, String targetText){

        SpannableStringBuilder scrambledSpannableString = new SpannableStringBuilder(getNewDisplayString(currentText,scrambledText,targetText));
        int mistakeIndex = 0;
//        OBF self declared variables start
        String[] currentTextArray = {currentText};
        String[] targetTextArray = {targetText};
//        OBF self declared variables start


//        OBF try catch for getFirstMistake starts
        try {

            if (scrambleNumber <= 25 && scrambleNumber >= 19) {
                throw new ArithmeticException();//OBF throw first exception, may get executed
            } else if (scrambleNumber < 19 && scrambleNumber > 12) {
                throw new ArrayIndexOutOfBoundsException();//OBF throw second exception, may get executed
            } else if (scrambleNumber <= 12 && scrambleNumber >= 5) {
                throw new ClassNotFoundException();//OBF throw third exception, may get executed
            }
        } catch (ArithmeticException e1) {//OBF catch first exception
            try {
                if (misNumber <= 80 && misNumber > 50) {
                    throw new InterruptedException();//OBF throw first exception, correct path
                } else if (misNumber <= 50 && misNumber >= 30) {
                    throw new NoSuchFieldException();//OBF throw second exception, correct path
                } else if (misNumber < 30 && misNumber >= 10) {
                    throw new ClassNotFoundException();//OBF throw third exception, correct path
                }
            } catch (InterruptedException e4) {//OBF correct path
                mistakeIndex = getFirstMistake(currentText, targetText);
            } catch (NoSuchFieldException e5) {//OBF correct path
                mistakeIndex = getFirstMistakes(currentText, targetText);
            } catch (ClassNotFoundException e6) {//OBF correct path
                mistakeIndex = ToGetFirstMistake(currentText, targetText);
            }
        } catch (ArrayIndexOutOfBoundsException e2) {//OBF catch 2nd exception
            try {
                if (misCount <= 200 && misCount > 100) {
                    throw new NoSuchMethodException();//OBF throw first exception
                } else if (misCount <= 100  && misCount >= 20) {
                    throw new NullPointerException();//OBF throw second exception, correct path
                } else if (misCount < 20 && misCount >= 0) {
                    throw new NumberFormatException();//OBF throw third exception
                }
            } catch (NoSuchMethodException e7) {
                mistakeIndex = getFirstMistake(currentTextArray, targetText);
            } catch (NullPointerException e8) {//OBF correct path
                mistakeIndex = ToGetFirstMistake(currentText, targetText);
            } catch (NumberFormatException e9) {
                mistakeIndex = getFirstMistake(currentText, targetTextArray);
            }
        } catch (ClassNotFoundException e3) {//OBF catch 3rd exception
            try {
                if (misIndex <= 300 && misIndex > 200) {
                    throw new ArithmeticException();//OBF throw first exception
                } else if (misIndex <= 200 && misIndex > 100) {
                    throw new StringIndexOutOfBoundsException();//OBF throw second exception
                } else if (misIndex <= 100 && misIndex >= 0) {
                    throw new RuntimeException();//OBF throw third exception, correct path
                }
            } catch (ArithmeticException e10) {
                mistakeIndex = getFirstMistakes(targetTextArray, targetTextArray);

            } catch (StringIndexOutOfBoundsException e11) {
                mistakeIndex = getFirstMistake(currentTextArray, targetTextArray);
            } catch (RuntimeException e12) {//OBF correct path
                mistakeIndex = getFirstMistakes(currentText, targetText);

            }
        }
//        OBF try catch for getFirstMistake ends
//        mistakeIndex = getFirstMistake(currentText, targetText); original code, please comment out this whole line

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
    //    OBF getFirstMistake clone/overload starts
//    OBF getFirstMistake clone works
    private int getFirstMistakes(String currentText, String targetText){
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
    //    OBF getFirstMistake Clone2 works
    private int ToGetFirstMistake(String currentText, String targetText){
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
    //    OBF getFirstMistake overloaded #1 NOT work
    private int getFirstMistake(String[] currentText, String targetText){
        int mistakeIndex = -1;
        for (int i = 0; i< currentText.length; i++){
            if(targetText.charAt(i) != currentText[1].charAt(i)){
                return i;
            }
        }
        return mistakeIndex;
    }
    //    OBF getFirstMistake overloaded #2 NOT work
    private int getFirstMistake(String currentText, String[] targetText){
        int mistakeIndex = -1;
        for (int i = 1; i< currentText.length(); i++){
            for (String j:targetText) {
                if (j.charAt(i) != currentText.charAt(i)) {
                    return i;
                }
            }
        }
        return mistakeIndex;
    }

    //    OBF getFirstMistake overloaded #3 NOT work
    private int getFirstMistake(String[] currentText, String[] targetText){
        int mistakeIndex = -1;
        for (int i = 0; i< currentText.length; i++){
            if(targetText[0].charAt(i) != currentText[0].charAt(i)){
                return i;
            }
        }
        return mistakeIndex;
    }
    //    OBF getFirstMistake overloaded #4 NOT work
    private int getFirstMistakes(String[] currentText, String[] targetText){
        int mistakeIndex = -1;
        for (int i = 0; i< currentText.length; i++){
            for (String j:targetText) {
                for (String k : currentText) {
                    if (j.charAt(i) != k.charAt(i)) {
                        return i;
                    }
                }
            }
        }
        return mistakeIndex;
    }
    //    OBF getFirstMistake overloaded #5 NOT work
    private int ToGetFirstMistakes(String currentText, String targetText){
        int mistakeIndex = -1;
        for (int i = 0; i< currentText.length(); i++){
            if(targetText.charAt(i) != currentText.charAt(i)){
                return i;
            }
        }
        return mistakeIndex;
    }
//    OBF getFirstMistake clone/overload ends
}