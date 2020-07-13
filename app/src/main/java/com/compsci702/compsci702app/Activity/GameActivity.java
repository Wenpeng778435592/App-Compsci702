package com.compsci702.compsci702app.Activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.compsci702.compsci702app.Data.Phrase;
import com.compsci702.compsci702app.R;
import com.compsci702.compsci702app.Tools.ScoreManager;
import com.compsci702.compsci702app.Tools.Timer;
import com.compsci702.compsci702app.Tools.SentenceExtractor;
import com.compsci702.compsci702app.Tools.SentenceProcessor;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private TextView mainText;
    private TextView bonusScoreText;
    private TextView scoreText;

    private String difficulty;

    private EditText inputText;
    private ProgressBar progressBar;
    private AdView adBannerView;

    private SentenceExtractor sentenceExtractor;
    private ScoreManager scoreManager;

    private SoundPool soundPool;
    private int dingSound;
    private SentenceProcessor sentenceProcessor;
    private Timer timer;

    private boolean started = false;
    private int timeInMillis = 20000;

    private ArrayList<Phrase> correctlyUnscrambledPhrases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        adBannerView = findViewById(R.id.adBannerView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adBannerView.loadAd(adRequest);

        getComponents();
        showKeyboard(inputText);

        difficulty = getIntent().getExtras().getString("difficulty");

        timer = new Timer(progressBar, timeInMillis);

        scoreManager = new ScoreManager(scoreText,bonusScoreText);
        sentenceExtractor = new SentenceExtractor(this, difficulty);
        this.mainText.setText(sentenceExtractor.getNextSentence());

        //Set up sound player
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        dingSound = soundPool.load(this, R.raw.ding, 1);
        sentenceProcessor = new SentenceProcessor(this);

        //Add listener to user input field to watch for changes
        inputText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(started == false){
                    timer.startTimer(GameActivity.this);
                    started = true;
                }

                mainText.setText(sentenceProcessor.getColourMatchedString(s.toString(), mainText.getText().toString(),
                        sentenceExtractor.getTargetPhraseString()));

                //If the word the user entered equals the target word increase count by one for the Level and
                //play sound.
                if(sentenceExtractor.checkMatch(s.toString())){
                    phraseMatched();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    @Override
    public void onBackPressed()
    {
        timer.stopTimer();
        super.onBackPressed();
    }

    public void timerhasFinished(){
        hideKeyboard();
        timer.stopTimer();
        Intent intent = new Intent(GameActivity.this, GameFinishedActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putParcelableArrayList("phraseList", correctlyUnscrambledPhrases);
        mBundle.putInt("score", scoreManager.getScore());
        mBundle.putString("difficulty", difficulty);
        intent.putExtras(mBundle);
        startActivity(intent);
    }

    public void phraseMatched(){
        correctlyUnscrambledPhrases.add(sentenceExtractor.getTargetPhrase());
        int millisLeft = timer.stopTimer();
        scoreManager.addScore(millisLeft, timer.getTimePerPhrase());
        soundPool.play(dingSound, 1, 1, 0, 0, 1);
        mainText.setText(sentenceExtractor.getNextSentence());
        inputText.setText("");
        int newTime = timeInMillis-1000*(correctlyUnscrambledPhrases.size()/3);
        timer = new Timer(progressBar,newTime);
        timer.startTimer(GameActivity.this);
    }

    private void showKeyboard(EditText inputTextField) {
        inputTextField.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputText.getWindowToken(), 0);
    }

    //Get all the necessary components from the layout file.
    private void getComponents(){
        inputText = findViewById(R.id.inputText);
        mainText = findViewById(R.id.mainText);
        scoreText = findViewById(R.id.scoreText);
        bonusScoreText = findViewById(R.id.bonusText);
        progressBar = findViewById(R.id.progressBar);
    }
}
