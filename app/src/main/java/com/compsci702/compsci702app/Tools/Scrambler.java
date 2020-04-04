package com.compsci702.compsci702app.Tools;

import android.text.TextUtils;
import com.compsci702.compsci702app.Level.Level;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Scrambler {

    //Scramble sentence by scrambling longest words first. Number of words scrambled depends
    //on the level. Also probability of an additional scramble that increases with level.
    //Calls to all other private methods from this method.
    public String scrambleSentence(String sentence, Level level){

        List<String> sentenceArray = Arrays.asList(sentence.split(" "));

        String[] sortedArray = sortArray(sentence);

        int num = level.getLevelNumber();
        if(num >= sentenceArray.size()){ num = sentenceArray.size(); }

        for (int i = 0 ; i< num; i++){
            String wordToFind = sortedArray[i];
            int index = sentenceArray.indexOf(wordToFind);

            String scrambledWord = scrambleString(wordToFind,level);
            sentenceArray.set(index,scrambledWord);
        }

        if(additionalScramble(level)){
            int index = randomNumber(0,sentenceArray.size()-1);
            String word = sentenceArray.get(index);

            String scrambledWord = scrambleString(word,level);
            sentenceArray.set(index,scrambledWord);
        }
        return TextUtils.join(" ", sentenceArray);
    }

    //Scramble the particular word in the sentence. Amount of scrambling depends on the level.
    private String scrambleString(String word, Level level) {

        int levelNum = level.getLevelNumber();
        char[] sentenceArray = word.toCharArray();

        int[] range = getRange(sentenceArray, levelNum);
        char[] scrambledArray = shuffleChars(sentenceArray, range[0],range[1]);
        String scrambledString = new String(scrambledArray);

        return scrambledString;
    }

    //Shuffle the chrarcters in the word (given as a character array) given the range.
    private char[] shuffleChars(char[] array, int rangeStart, int rangeEnd) {

        int index;
        char temp;
        Random random = new Random();
        for (int i = rangeEnd; i > rangeStart; i--)
        {
            index = random.nextInt(i);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }

    //Gets the range of indices on which to scramble the characters. Range increases with the level.
    private int[] getRange(char[] array, int levelNumber) {

        int max = array.length - levelNumber - 2;
        int min = 0;

        if (max <= 0){ return new int[]{min, array.length-1}; }

        int startIndex = randomNumber(min, max);
        int[] range = {startIndex, startIndex + levelNumber + 1};
        return range;
    }

    //Sort words in the sentence from longest to shortest
    private String[] sortArray(String sentence){

        String[] array = sentence.split(" ");
        Arrays.sort(array,new Comparator<String>() {

            @Override
            public int compare(String str1, String str2) {
                return str2.length() - str1.length();
            }
        });
        return array;
    }

    //Returns whether an additional word will be scrambled for the level. Based on a probability
    //that increases with the level.
    private boolean additionalScramble(Level level){

        double probability = (double)level.getLevelNumber()/10;
        double random = Math.random();
        if(probability < random){
            return false;
        }else {
            return true;
        }
    }

    //Gets random number within min max range specified
    private int randomNumber(int min, int max){

        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
}
