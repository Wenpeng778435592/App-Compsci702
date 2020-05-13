package com.compsci702.compsci702app.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
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
import com.compsci702.compsci702app.Tools.MinuteTimer;
import com.compsci702.compsci702app.Tools.SentenceProcessor;

import java.io.UnsupportedEncodingException;

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
    private SentenceProcessor sentenceProcessor;
    private MinuteTimer timer;

    public static String input1b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //rb delete and create database
        //SQLiteDatabase db;
        //this.deleteDatabase("WordBank.db");
        //db = new DBHelper(this).getWritableDatabase();

        //rb database all rows query
        //new DBHelper(this).onDeleteAllRows();

        //rb database insert query
        new DBHelper(this).onInsert();

        getComponents();

        levelController = new LevelController();
        currentLevel = new Level1(this);
        timer = new MinuteTimer(timerText);
        this.mainText.setText(currentLevel.getNextSentence());
        this.levelText.setText(decrypt(currentLevel.getLevelText()));
        this.levelDescription.setText(decrypt(currentLevel.getLevelDescription()));

        //Set up sound player
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        dingSound = soundPool.load(this, R.raw.ding, 1);
        sentenceProcessor = new SentenceProcessor(this);

        input1b = this.getString(R.string.ring);

        //Add listener to user input field to watch for changes
        inputText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mainText.setText(sentenceProcessor.getColourMatchedString(s.toString(), mainText.getText().toString(),
                        currentLevel.getTargetSentence()));

                //If the word the user entered equals the target word increase count by one for the Level and
                //play sound.
                if(currentLevel.checkMatch(s.toString())){
                    soundPool.play(dingSound, 1, 1, 0, 0, 1);

                    progressBar.setProgress(currentLevel.getCurrentWordCount());
                    progressText.setText(currentLevel.getProgressIndicatorText());

                    if(currentLevel.levelFinished()){
                        setLevelFinishedLayout();
                    } else {
                        //If the level isn't finished display the next sentence in the list.
                        mainText.setText(currentLevel.getNextSentence());
                        inputText.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        timerText.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equals("0")) {
                    timer.stopTimer();
                    Intent intent = new Intent(GameActivity.this, GameFinishedActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putBoolean("success", false);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }
            }
        });

    }

    public void nextLevelStartClicked(View view){
        nextLevelLayout.setVisibility(View.GONE);
        levelFinishedLayout.setVisibility(View.GONE);
        mainGameLayout.setVisibility(View.VISIBLE);

        timer.startTimer();

        mainText.setText(currentLevel.getNextSentence());
        inputText.setText("");

        progressText.setText(currentLevel.getProgressIndicatorText());
        progressBar.setMax(currentLevel.getWordCountGoal());
        progressBar.setProgress(currentLevel.getCurrentWordCount());

        showKeyboard(inputText);
    }

    //Player clicks next level button from the level passed screen. Leads player to
    //next level information screen.
    public void nextLevelInfoClicked(View view){
        nextLevelLayout.setVisibility(View.VISIBLE);
        levelFinishedLayout.setVisibility(View.GONE);
        mainGameLayout.setVisibility(View.GONE);

        this.levelText.setText(currentLevel.getLevelText());
        this.levelDescription.setText(currentLevel.getLevelDescription());
    }

    //Shown when user completes the level. Shows level passed text.
    private void setLevelFinishedLayout(){
        hideKeyboard();
        mainGameLayout.setVisibility(View.GONE);
        nextLevelLayout.setVisibility(View.GONE);
        levelFinishedLayout.setVisibility(View.VISIBLE);

        timer.stopTimer();

        //If this is the last level, load the game finished class.
        if (currentLevel.getLevelType() == LevelType.LEVEL_3) {
            Intent intent = new Intent(this,GameFinishedActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putBoolean("success", true);
            intent.putExtras(mBundle);
            startActivity(intent);
        }else{
            currentLevel = levelController.getNextLevel(currentLevel, this);
            this.levelText.setText(decrypt(currentLevel.getCongratulationsText()));
            this.levelDescription.setText(decrypt(currentLevel.getLevelFinishedDescription()));
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

    //Get all the necessary components from the layout file.
    private void getComponents(){
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
    }

    private String decrypt(String string){
        String decryptB64Text = null;
        byte[] decryptB64Byte = Base64.decode(string, Base64.DEFAULT);
        try {
            decryptB64Text = new String(decryptB64Byte, "UTF-8");
            //System.out.println("decryptB64 " + decryptB64Text);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decryptB64Text;
    }
}
