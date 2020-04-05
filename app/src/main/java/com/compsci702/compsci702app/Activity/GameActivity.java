package com.compsci702.compsci702app.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.compsci702.compsci702app.Level.Level;
import com.compsci702.compsci702app.Level.Level1;
import com.compsci702.compsci702app.Level.LevelType;
import com.compsci702.compsci702app.LevelController;
import com.compsci702.compsci702app.R;
import com.compsci702.compsci702app.Tools.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    LinearLayout nextLevelLayout;
    LinearLayout mainGameLayout;
    LinearLayout levelFinishedLayout;

    TextView levelText;
    TextView levelDescription;
    TextView mainText;
    TextView timerText;
    TextView progressText;
    TextView levelFinishedText;
    TextView levelFinishedDescription;

    EditText inputText;

    ProgressBar progressBar;

    Level currentLevel;
    LevelController levelController;

    private SoundPool soundPool;
    private int dingSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //rb delete and create database
        //SQLiteDatabase db;
        ///this.deleteDatabase("WordBank.db");
       //db = new DBHelper(this).getWritableDatabase();

        //rb database all rows query
        //new DBHelper(this).onDeleteAllRows();

       //rb database insert query
        //new DBHelper(this).onInsert();


        //rb read from database
//        Cursor cursor = new DBHelper(this).alldata();
//        if (cursor.getCount() == 0){
//            Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            while (cursor.moveToNext()){
//                Toast.makeText(getApplicationContext(), "Sentence" +cursor.getString(1), Toast.LENGTH_SHORT).show();
//                }
//        }


        //Getting all components specified in layout files
        nextLevelLayout = findViewById(R.id.nextLevelLayout);
        levelFinishedLayout = findViewById(R.id.levelFinishedLayout);
        mainGameLayout = findViewById(R.id.mainGameLayout);

        levelText = findViewById(R.id.levelText);
        levelDescription = findViewById(R.id.levelDescription);
        levelFinishedText = findViewById(R.id.levelFinishedText);
        levelFinishedDescription = findViewById(R.id.levelFinishedDescription);

        inputText = findViewById(R.id.inputText);
        mainText = findViewById(R.id.mainText);
        progressText = findViewById(R.id.progressText);
        timerText = findViewById(R.id.timerText);
        levelDescription = findViewById(R.id.levelDescription);

        progressBar = findViewById(R.id.progressBar);

        levelController = new LevelController();
        currentLevel = new Level1();
        mainText.setText(currentLevel.getNextWord());

        //Set up sound player
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        dingSound = soundPool.load(this, R.raw.ding, 1);

        //Add listener to user input field to watch for changes
        inputText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String targetText = mainText.getText().toString();

                SpannableString spannableString = new SpannableString(targetText);
                mainText.setText(getColourMatchedString(spannableString, s.toString()));

                //If the word the user entered equals the target word increase count by one for the Level and
                //play sound.
                if(s.toString().equals(spannableString.toString())){
                    currentLevel.wordMatched();
                    soundPool.play(dingSound, 1, 1, 0, 0, 1);

                    progressBar.setProgress(currentLevel.getCurrentWordCount());
                    progressText.setText(currentLevel.getProgressIndicatorText());

                    if(currentLevel.levelFinished()){
                        setLevelFinishedLayout();
                    } else {
                        //If the level isn't finished display the next word in the list.
                        mainText.setText(currentLevel.getNextWord());
                        inputText.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }


        });

    }

    public void nextLevelStartClicked(View view){
        nextLevelLayout.setVisibility(View.GONE);
        levelFinishedLayout.setVisibility(View.GONE);
        mainGameLayout.setVisibility(View.VISIBLE);

        inputText.setText("");
        mainText.setText(currentLevel.getNextWord());

        progressText.setText(currentLevel.getProgressIndicatorText());
        progressBar.setMax(currentLevel.getWordCountGoal());
        progressBar.setProgress(currentLevel.getCurrentWordCount());

        showKeyboard(inputText);
    }

    public void nextLevelInfoClicked(View view){
        nextLevelLayout.setVisibility(View.VISIBLE);
        levelFinishedLayout.setVisibility(View.GONE);
        mainGameLayout.setVisibility(View.GONE);

        this.levelText.setText(currentLevel.getLevelText());
        this.levelDescription.setText(currentLevel.getLevelDescription());
    }

    private void setLevelFinishedLayout(){
        hideKeyboard();
        mainGameLayout.setVisibility(View.GONE);
        nextLevelLayout.setVisibility(View.GONE);
        levelFinishedLayout.setVisibility(View.VISIBLE);

        System.out.println("currentType " + currentLevel.getLevelType());

        if (currentLevel.getLevelType() == LevelType.LEVEL_3) {
            System.out.println("game finished");
            Intent intent = new Intent(this,GameFinishedActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putBoolean("success", true);
            intent.putExtras(mBundle);
            startActivity(intent);
        }else{
            currentLevel = levelController.getNextLevel(currentLevel);
            levelFinishedText.setText(currentLevel.getCongratulationsText());
            levelFinishedDescription.setText(currentLevel.getLevelFinishedDescription());
        }
    }

    private void showKeyboard(EditText inputTextField) {
        inputTextField.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputText.getWindowToken(), 0);
    }

    //Change colour of target word in relation to what the user has currently written.
    private SpannableString getColourMatchedString(SpannableString targetText, String currentText){

        for(int i=0; i< targetText.length(); i++){
            if(i > currentText.length()-1){
                ForegroundColorSpan fcsgrey = new ForegroundColorSpan(getResources().getColor(R.color.darkGrey));
                targetText.setSpan(fcsgrey ,i ,i+1 ,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            }else if(currentText.charAt(i) == targetText.charAt(i)){
                ForegroundColorSpan fcsgreen = new ForegroundColorSpan(getResources().getColor(R.color.green));
                targetText.setSpan(fcsgreen ,i ,i+1 ,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            }else{
                ForegroundColorSpan fcsred = new ForegroundColorSpan(getResources().getColor(R.color.red));
                targetText.setSpan(fcsred ,i ,i+1 ,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }
        return targetText;
    }
}
