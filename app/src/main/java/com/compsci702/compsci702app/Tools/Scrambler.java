package com.compsci702.compsci702app.Tools;

import android.text.TextUtils;
import com.compsci702.compsci702app.Level.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Scrambler {
    //    OBF declared variables start
    int max_num = 25; //OBF max bound for random number
    int min_num = 5; //OBF min bound for random number
    int max_word_num = 100; //OBF max bound for random word number
    int min_word_num = 50; //OBF min bound for random word number
    int max_word_count = 500; //OBF max bound for random word number
    int min_word_count = 100; //OBF min bound for random word number
    int max_sentence_count = 50; //OBF max bound for random word number
    int min_sentence_count = 30; //OBF min bound for random word number

    int max_ScrambleIndex = 300;
    int min_ScrambleIndex = 100;
    int max_ScrambleSentenceIndex = 120;
    int min_ScrambleSentenceIndex = 20;
    int max_ScrambleSentenceNum = 180;
    int min_ScrambleSentenceNum = 60;

    int scrambleNumber =min_num + (int)(Math.random() * ((max_num - min_num) + 1));
    int wordNumber = min_word_num + (int)(Math.random() * ((max_word_num - min_word_num) + 1));
    int wordCount = min_word_count + (int)(Math.random() * ((max_word_count - min_word_count) + 1));
    int sentenceCount = min_sentence_count + (int)(Math.random() * ((max_sentence_count - min_sentence_count) + 1));
    int scrambleIndex = min_ScrambleIndex + (int)(Math.random() * ((max_ScrambleIndex - min_ScrambleIndex) + 1));
    int scrambleSentenceIndex = min_ScrambleSentenceIndex + (int)(Math.random() * ((max_ScrambleSentenceIndex - min_ScrambleSentenceIndex) + 1));
    int scrambleSentenceNumber = min_ScrambleSentenceNum + (int)(Math.random() * ((max_ScrambleSentenceNum - min_ScrambleSentenceNum) + 1));

//    OBF declared variables end

    //Scramble sentence by scrambling longest words first. Number of words scrambled depends
    //on the level. Also probability of an additional scramble that increases with level.
    //Calls to all other private methods from this method.
    public String scrambleSentence(String sentence, Level level){

        List<String> sentenceArray = Arrays.asList(sentence.split(" "));
//        OBF try catch for sortArray start
        String[] sentenceArrayUnsorted = {sentence};
        String[] sortedArray = {};
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
                if (sentenceCount <= 50 && sentenceCount > 40) {
                    throw new InterruptedException();//OBF throw first exception, correct path
                } else if (sentenceCount <= 40 && sentenceCount >= 30) {
                    throw new NoSuchFieldException();//OBF throw second exception, correct path
                } else if (sentenceCount < 30 && sentenceCount >= 20) {
                    throw new ClassNotFoundException();//OBF throw third exception
                }
            } catch (InterruptedException e4) {//OBF correct path
                sortedArray = sortArray(sentence);
            } catch (NoSuchFieldException e5) {//OBF correct path
                sortedArray = ToSortArray(sentence);
            } catch (ClassNotFoundException e6) {
                sortedArray = sortArray(sentenceArrayUnsorted);
            }
        } catch (ArrayIndexOutOfBoundsException e2) {//OBF catch 2nd exception
            try {
                if (wordNumber <= 100 && wordNumber >= 80) {
                    throw new NoSuchMethodException();//OBF throw first exception, correct path
                } else if (wordNumber < 80  && wordNumber >= 50) {
                    throw new NullPointerException();//OBF throw second exception, correct path
                } else if (wordNumber < 50 && wordNumber >= 0) {
                    throw new NumberFormatException();//OBF throw third exception
                }
            } catch (NoSuchMethodException e7) {//OBF correct path
                sortedArray = ToSortArray(sentence);
            } catch (NullPointerException e8) {//OBF correct path
                sortedArray = sortArray(sentence);
            } catch (NumberFormatException e9) {
                sortedArray = sortArray(sentenceArrayUnsorted);
            }
        } catch (ClassNotFoundException e3) {//OBF catch 3rd exception
            try {
                if (wordCount <= 1500 && wordCount > 1000) {
                    throw new ArithmeticException();//OBF throw first exception
                } else if (wordCount <= 1000 && wordCount > 500) {
                    throw new StringIndexOutOfBoundsException();//OBF throw second exception
                } else if (wordCount <= 500 && wordCount >= 100) {
                    throw new RuntimeException();//OBF throw third exception, correct path
                }
            } catch (ArithmeticException e10) {
                sortedArray = sortArray(sentence);

            } catch (StringIndexOutOfBoundsException e11) {
                sortedArray = sortArray(sentenceArrayUnsorted);
            } catch (RuntimeException e12) {//OBF correct path
                sortedArray = ToSortArray(sentence);
            }
        }


//        OBF try catch for sortArray ends
//        sortedArray = sortArray(sentence); original code, please comment out the whole line

        int num = level.getLevelNumber();
        if(num >= sentenceArray.size()){ num = sentenceArray.size(); }

        for (int i = 0 ; i< num; i++){
            String wordToFind = sortedArray[i];
            int index = sentenceArray.indexOf(wordToFind);

//            OBF self declared variables start
            String[] wordToFindArray = {wordToFind};
            byte[] wordToFindByte = wordToFind.getBytes();
            ArrayList<String> wordToFindArrayList = new ArrayList<>();
            wordToFindArrayList.add(wordToFind);

            String scrambledWord = "";

//            OBF self declared variables end
//
//            OBF start of try catch for ScrambleString
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
                    if (scrambleIndex <= 500 && scrambleIndex > 300) {
                        throw new InterruptedException();//OBF throw first exception
                    } else if (scrambleIndex <= 300 && scrambleIndex >= 100) {
                        throw new NoSuchFieldException();//OBF throw second exception, correct path
                    } else if (scrambleIndex < 100 && scrambleIndex >= 0) {
                        throw new ClassNotFoundException();//OBF throw third exception
                    }
                } catch (InterruptedException e4) {
                    scrambledWord = scrambleString(wordToFindArray,level);
                } catch (NoSuchFieldException e5) {//OBF correct path
                    scrambledWord = scrambleString(wordToFind,level);
                } catch (ClassNotFoundException e6) {
                    scrambledWord = scrambleString(wordToFindArrayList,level);
                }
            } catch (ArrayIndexOutOfBoundsException e2) {//OBF catch 2nd exception
                try {
                    if (scrambleSentenceIndex <= 200 && scrambleSentenceIndex > 120) {
                        throw new NoSuchMethodException();//OBF throw first exception
                    } else if (scrambleSentenceIndex <=120  && scrambleSentenceIndex >= 20) {
                        throw new NullPointerException();//OBF throw second exception, correct path
                    } else if (scrambleSentenceIndex < 20 && scrambleSentenceIndex >= 0) {
                        throw new NumberFormatException();//OBF throw third exception
                    }
                } catch (NoSuchMethodException e7) {
                    scrambledWord = scrambleString(wordToFindByte,level);
                } catch (NullPointerException e8) {//OBF correct path
                    scrambledWord = scrambleString(wordToFind,level);
                } catch (NumberFormatException e9) {
                    scrambledWord = scrambleString(wordToFindArray,level);
                }
            } catch (ClassNotFoundException e3) {//OBF catch 3rd exception
                try {
                    if (scrambleSentenceNumber <= 180 && scrambleSentenceNumber >= 60) {
                        throw new ArithmeticException();//OBF throw first exception, correct path
                    } else if (scrambleSentenceNumber < 60 && scrambleSentenceNumber >= 30) {
                        throw new StringIndexOutOfBoundsException();//OBF throw second exception
                    } else if (scrambleSentenceNumber < 30 && scrambleSentenceNumber >= 0) {
                        throw new RuntimeException();//OBF throw third exception
                    }
                } catch (ArithmeticException e10) {//OBF correct path, correct path
                    scrambledWord = scrambleString(wordToFind,level);

                } catch (StringIndexOutOfBoundsException e11) {
                    scrambledWord = scrambleString(wordToFindArrayList,level);
                } catch (RuntimeException e12) {
                    scrambledWord = scrambleString(wordToFindByte,level);
                }
            }
//            OBF end of try catch for ScrambleString
//            scrambledWord = scrambleString(wordToFind,level); original code, please comment out out whole line

            sentenceArray.set(index,scrambledWord);
        }

        if(additionalScramble(level)){
            int index = randomNumber(0,sentenceArray.size()-1);
            String word = sentenceArray.get(index);

//            OBF self declared variables start
            String[] wordArray = {word};
            byte[] wordByte = word.getBytes();
            ArrayList<String> wordArrayList = new ArrayList<>();
            wordArrayList.add(word);

            String scrambledWord = "";
//            OBF self declared variables end

//            OBF start of try catch for ScrambleString
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
                    if (scrambleIndex <= 500 && scrambleIndex > 300) {
                        throw new InterruptedException();//OBF throw first exception
                    } else if (scrambleIndex <= 300 && scrambleIndex >= 100) {
                        throw new NoSuchFieldException();//OBF throw second exception, correct path
                    } else if (scrambleIndex < 100 && scrambleIndex >= 0) {
                        throw new ClassNotFoundException();//OBF throw third exception
                    }
                } catch (InterruptedException e4) {
                    scrambledWord = scrambleString(wordArray,level);
                } catch (NoSuchFieldException e5) {//OBF correct path
                    scrambledWord = scrambleString(word,level);
                } catch (ClassNotFoundException e6) {
                    scrambledWord = scrambleString(wordArrayList,level);
                }
            } catch (ArrayIndexOutOfBoundsException e2) {//OBF catch 2nd exception
                try {
                    if (scrambleSentenceIndex <= 200 && scrambleSentenceIndex > 120) {
                        throw new NoSuchMethodException();//OBF throw first exception
                    } else if (scrambleSentenceIndex <=120  && scrambleSentenceIndex >= 20) {
                        throw new NullPointerException();//OBF throw second exception, correct path
                    } else if (scrambleSentenceIndex < 20 && scrambleSentenceIndex >= 0) {
                        throw new NumberFormatException();//OBF throw third exception
                    }
                } catch (NoSuchMethodException e7) {
                    scrambledWord = scrambleString(wordByte,level);
                } catch (NullPointerException e8) {//OBF correct path
                    scrambledWord = scrambleString(word,level);
                } catch (NumberFormatException e9) {
                    scrambledWord = scrambleString(wordArray,level);
                }
            } catch (ClassNotFoundException e3) {//OBF catch 3rd exception
                try {
                    if (scrambleSentenceNumber <= 180 && scrambleSentenceNumber >= 60) {
                        throw new ArithmeticException();//OBF throw first exception, correct path
                    } else if (scrambleSentenceNumber < 60 && scrambleSentenceNumber >= 30) {
                        throw new StringIndexOutOfBoundsException();//OBF throw second exception
                    } else if (scrambleSentenceNumber < 30 && scrambleSentenceNumber >= 0) {
                        throw new RuntimeException();//OBF throw third exception
                    }
                } catch (ArithmeticException e10) {//OBF correct path, correct path
                    scrambledWord = scrambleString(word,level);

                } catch (StringIndexOutOfBoundsException e11) {
                    scrambledWord = scrambleString(wordArrayList,level);
                } catch (RuntimeException e12) {
                    scrambledWord = scrambleString(wordByte,level);
                }
            }
//            OBF end of try catch for ScrambleString
//
//            scrambledWord = scrambleString(word,level); original code please comment out the whole line

            sentenceArray.set(index,scrambledWord);
        }
        return TextUtils.join(" ", sentenceArray);
    }

    //Scramble the particular word in the sentence. Amount of scrambling depends on the level.
    private String scrambleString(String word, Level level) {
        int levelNum = level.getLevelNumber();
//        OBF self declared variables start
        String scrambledString = "";
        char[] sentenceArray = word.toCharArray();
        byte[] sentenceByteArray = word.getBytes();
        ArrayList<char[]> sentenceArrayList = new ArrayList<>();
        sentenceArrayList.add(sentenceArray);
        char[] scrambledArray = {};
//        OBF self declared variables end
//
//        OBF start of try catch for getRange and shuffleChars
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
                if (wordNumber <= 100 && wordNumber >= 80) {
                    throw new InterruptedException();//OBF throw first exception, correct path
                } else if (wordNumber < 80 && wordNumber >= 50) {
                    throw new NoSuchFieldException();//OBF throw second exception, correct path
                } else if (wordNumber < 50 && wordNumber >= 0) {
                    throw new ClassNotFoundException();//OBF throw third exception
                }
            } catch (InterruptedException e4) {//OBF correct path
                int[] range = getRange(sentenceArray, levelNum, 1000);
                scrambledArray = ToShuffleChars(sentenceArray, range[0],range[1]);
            } catch (NoSuchFieldException e5) {//OBF correct path
                int[] range = getRange(sentenceArray, levelNum);
                scrambledArray = shuffleChars(sentenceArray, range[0],range[1]);
            } catch (ClassNotFoundException e6) {
                int[] range = getRange(sentenceByteArray, levelNum);
                scrambledArray = ToShuffleCharacters(sentenceArray, range[0],range[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e2) {//OBF catch 2nd exception
            try {
                if (wordCount <= 500 && wordCount >= 100) {
                    throw new NoSuchMethodException();//OBF throw first exception, correct path
                } else if (wordCount < 100 && wordCount >= 50) {
                    throw new NullPointerException();//OBF throw second exception
                } else if (wordCount < 50 && wordCount >= 0) {
                    throw new NumberFormatException();//OBF throw third exception
                }
            } catch (NoSuchMethodException e7) {//OBF correct path
                int[] range = getRange(sentenceArray, levelNum, 1000);
                scrambledArray = ToShuffleCharacters(sentenceArray, range[0],range[1]);
            } catch (NullPointerException e8) {
                int[] range = getRange(sentenceArray, levelNum);
                scrambledArray = shuffleChars(sentenceByteArray, range[0],range[1]);
            } catch (NumberFormatException e9) {
                int[] range = getRange(sentenceArray, levelNum, 1000);
                scrambledArray = ToShuffleChars(sentenceByteArray, range[0],range[1]);
            }
        } catch (ClassNotFoundException e3) {//OBF catch 3rd exception
            try {
                if (sentenceCount <= 200 && sentenceCount >= 100) {
                    throw new ArithmeticException();//OBF throw first exception
                } else if (sentenceCount < 100 && sentenceCount >= 51) {
                    throw new StringIndexOutOfBoundsException();//OBF throw second exception
                } else if (sentenceCount < 51 && sentenceCount >= 30) {
                    throw new RuntimeException();//OBF throw third exception, correct path
                }
            } catch (ArithmeticException e10) {
                int[] range = ToGetRange(sentenceArray, levelNum);
                scrambledArray = ToShuffleChars(sentenceByteArray, range[0],range[1]);

            } catch (StringIndexOutOfBoundsException e11) {
                int[] range = ToGetRange(sentenceArrayList, levelNum);
                scrambledArray = shuffleChars(sentenceByteArray, range[0],range[1]);
            } catch (RuntimeException e12) {//OBF correct path
                int[] range = getRange(sentenceArray, levelNum, 1000);
                scrambledArray = shuffleChars(sentenceArray, range[0],range[1]);
            }
        }
//        int[] range = getRange(sentenceArray, levelNum); original code, please comment out
//        scrambledArray = shuffleChars(sentenceArray, range[0],range[1]); original code please comment out


//                OBF end of try catch for getRange
        scrambledString = new String(scrambledArray);



        return scrambledString;
    }

    //    OBF clone/overload for scramblestring start
//    OBF scrambleString #1 overloaded does NOT work
    private String scrambleString(String[] word, Level level) {
        int levelNum = level.getLevelNumber();
        char[] sentenceArray = word[0].toCharArray();

        int[] range = getRange(sentenceArray, levelNum, 1000);
        char[] scrambledArray = shuffleChars(sentenceArray, range[0],range[1]);
        String scrambledString = new String(scrambledArray);
        return scrambledString;
    }
    //    OBF scrambleString #2 overloaded does NOT work
    private String scrambleString(ArrayList<String> word, Level level) {
        int levelNum = level.getLevelNumber();
        char[] sentenceArray = word.get(0).toCharArray();

        int[] range = ToGetRange(sentenceArray, levelNum);
        char[] scrambledArray = ToShuffleChars(sentenceArray, range[0],range[1]);
        String scrambledString = new String(scrambledArray);

        return scrambledString;
    }
    //    OBF scrambleString #3 overloaded does NOT work
    private String scrambleString(byte[] word, Level level) {
        int levelNum = level.getLevelNumber();
        char[] sentenceArray = word.toString().toCharArray();
        int[] range = getRange(sentenceArray, levelNum);
        char[] scrambledArray = ToShuffleCharacters(sentenceArray, range[0],range[1]);
        String scrambledString = new String(scrambledArray);

        return scrambledString;
    }
//    OBF clone/overload for scramblestring ends

    //Shuffle the characters in the word (given as a character array) given the range.
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
    //    OBF clone/overload for shuffleChars start
//    OBF shuffleCharsClone #1 works
    private char[] ToShuffleChars(char[] array, int rangeStart, int rangeEnd) {
        String[] stringArray = new String(array).split("(?!^)");
        ArrayList<String> stringArrayList =  new ArrayList(Arrays.asList(stringArray));

        Collections.shuffle(stringArrayList.subList(rangeStart, rangeEnd + 1));
        String string =  Arrays.toString(stringArrayList.toArray());
        string = string.substring(1,string.length()-1).replace(", ","");
        char[] charArray = string.toCharArray();
        return charArray;
    }
    //    OBF shuffleCharsClone2 works
    private char[] ToShuffleCharacters(char[] array, int rangeStart, int rangeEnd) {
        String[] stringArray = new String(array).split("(?!^)");
        ArrayList<String> stringArrayList =  new ArrayList(Arrays.asList(stringArray));

        Collections.shuffle(stringArrayList.subList(rangeStart, rangeEnd + 1));
        String string =  Arrays.toString(stringArrayList.toArray());
        string = string.substring(1,string.length()-1).replace(", ","");
        char[] charArray = string.toCharArray();
        return charArray;
    }
    //    OBF shuffleChars overloaded #1 NOT work
    private char[] shuffleChars(byte[] array, int rangeStart, int rangeEnd) {
        int index;
        byte temp;
        char[] array2 = array.toString().toCharArray();
        Random random = new Random();
        for (int i = rangeEnd; i > rangeStart; i--)
        {
            index = random.nextInt(i);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array2;
    }

    //    OBF shuffleChars overloaded #2 NOT work
    private char[] ToShuffleChars(byte[] array, int rangeStart, int rangeEnd) {
        int index=0;
        char temp;
        char[] array2 = new char[array.length];
        for (byte i:array) {
            array2[index]= (char)i;
            index+=1;
        }
        Random random = new Random();
        for (long i = rangeEnd; i > rangeStart; i--)
        {
            index = random.nextInt((int)i);
            temp = array2[index];
            array2[index] = array2[(int)i];
            array2[(int)i] = temp;
        }
        return array2;
    }
//    OBF clone/overload for shuffleChars ends

    //Gets the range of indices on which to scramble the characters. Range increases with the level.
    private int[] getRange(char[] array, int levelNumber) {

        int max = array.length - levelNumber - 2;
        int min = 0;

        if (max <= 0){ return new int[]{min, array.length-1}; }

        int startIndex = randomNumber(min, max);
        int[] range = {startIndex, startIndex + levelNumber + 1};
        return range;
    }

    //    OBF clone/overload for shuffleChars start
//    OBF getRange #1 overloaded works
    private int[] getRange(char[] array, int levelNumber, int num) {
        int max = array.length - levelNumber - 2;
        int one = levelNumber/(array.length + 2) + 1;
        if (array.length <= levelNumber + 2){ return new int[]{one-1, array.length-1}; }
        int startIndex = (int)(System.currentTimeMillis()%num)%(max+one);
        int[] range = {startIndex, one + startIndex + levelNumber};
        return range;
    }

    //    OBF getRange #1 overloaded does NOT work
    private int[] getRange(byte[] array, int levelNumber) {
        int min = array.length - (array.length-1);
        int max = array.length - levelNumber - 2;
        if (max <= 0){ return new int[]{min, array.length-1}; }
        int startIndex = randomNumber(min, max);
        int[] range = {startIndex, startIndex + levelNumber + 1};
        return range;
    }
    //    OBF getRange #2 overloaded does NOT work
    private int[] getRange(ArrayList<char[]> array, int levelNumber) {
        int min = array.get(0).length - (array.get(0).length-1);
        int max = array.get(0).length - levelNumber - 2;
        if (max <= 0){ return new int[]{min, max-levelNumber+1}; }
        int startIndex = randomNumber(min, max);
        int[] range = {startIndex, startIndex + levelNumber + 1};
        return range;
    }
    //    OBF getRange #3 overloaded does NOT work
    private int[] ToGetRange(ArrayList<char[]> array, int levelNumber) {
        long min = array.get(0).length - (array.get(0).length-1);
        long max = array.get(0).length - levelNumber - 2;
        if (max <= 0){ return new int[]{(int)min, (int)max+levelNumber+1}; }
        long startIndex = randomNumber((int)min, (int)max);
        int[] range = {(int)startIndex, (int)startIndex + (int)levelNumber + 1};
        return range;
    }
    //    OBF getRange #4 overloaded does not work
    private int[] ToGetRange(char[] array, int levelNumber) {
        long min = array.length - (array.length-1);
        long max = array.length - levelNumber - 2;
        if (max <= 0){ return new int[]{(int)min, (int)max+levelNumber-(int)min}; }
        long startIndex = randomNumber((int)min, (int)max);
        int[] range = {(int)startIndex, (int)startIndex + (int)levelNumber + 1};
        return range;
    }
//    OBF clone/overload for shuffleChars ends




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

    //    OBF clone/overload for shuffleChars start
//    OBF sortArrayClone works
    private String[] ToSortArray(String sentence){
        String [] arr = sentence.split(" ");
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                String tmp;
                if (arr[i].length() < arr[j].length()) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }

    //    OBF sortArray overloaded #1 should work but treat as NOT work
    private String[] sortArray(String[] sentence){
        String[] array = sentence[0].split(" ");
        Arrays.sort(array,new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str2.length() - str1.length();
            }
        });
        return array;
    }
//    OBF clone/overload for shuffleChars ends


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