package com.compsci702.compsci702app.Activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
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
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private TextView mainText;
    private TextView bonusScoreText;
    private TextView scoreText;

    private String difficulty;

    private EditText inputText;
    private ProgressBar progressBar;
    //private AdView adBannerView;

    private SentenceExtractor sentenceExtractor;
    private ScoreManager scoreManager;

    private SoundPool soundPool;
    private int dingSound;
    private int clockSound;
    private SentenceProcessor sentenceProcessor;
    private Timer timer;

    private boolean started = false;
    private int timeInMillis = 30000;
    private int countdownstep = 1000;

    private int easyStepThreshold1 =20000;
    private int easyStepThreshold2 =15000;

    private int mediumStepThreshold1 =18000;
    private int mediumStepThreshold2 =13000;

    private int hardStepThreshold1 =15000;
    private int hardStepThreshold2 =10000;

    private ArrayList<Phrase> correctlyUnscrambledPhrases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        adBannerView = findViewById(R.id.adBannerView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adBannerView.loadAd(adRequest);

        getComponents();
        showKeyboard(inputText);

        difficulty = getIntent().getExtras().getString("difficulty");

        timer = new Timer(progressBar, timeInMillis);

        scoreManager = new ScoreManager(scoreText,bonusScoreText);
        sentenceExtractor = new SentenceExtractor(this, difficulty);
        this.mainText.setText(sentenceExtractor.getNextSentence());

        //Set up sound player
//        AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
//                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                .build();
//        soundPool = new SoundPool.Builder()
//                .setMaxStreams(6)
//                .setAudioAttributes(audioAttributes)
//                .build();

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        dingSound = soundPool.load(this, R.raw.ding, 1);
        clockSound = soundPool.load(this,R.raw.clock,1);

        sentenceProcessor = new SentenceProcessor(this);

        //Add listener to user input field to watch for changes
        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(started == false){
                    timer.startTimer(GameActivity.this, soundPool, clockSound);
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
        if(timer != null){
            timer.stopTimer();
        }
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

        getNewTime();
        if(timeInMillis > 0) {
            timer = new Timer(progressBar, timeInMillis);
            timer.startTimer(GameActivity.this,soundPool,clockSound);
        }
    }

    private void getNewTime(){
        int threshold1;
        int threshold2;

        if(difficulty.equals("easy")){
            threshold1 = easyStepThreshold1;
            threshold2 = easyStepThreshold2;
        }else if(difficulty.equals("medium")){
            threshold1 = mediumStepThreshold1;
            threshold2 = mediumStepThreshold2;
        }else{
            threshold1 = hardStepThreshold1;
            threshold2 = hardStepThreshold2;
        }

        if((timeInMillis < threshold1)){
            countdownstep = 500;
        }else if(timeInMillis < threshold2){
            countdownstep = 250;
        }

        timeInMillis = timeInMillis-countdownstep;
        System.out.println(timeInMillis + "-" + countdownstep);
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
