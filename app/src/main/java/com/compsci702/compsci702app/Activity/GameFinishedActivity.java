package com.compsci702.compsci702app.Activity;

import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.compsci702.compsci702app.Adapters.ExpandableListAdapter;
import com.compsci702.compsci702app.Data.Phrase;
import com.compsci702.compsci702app.R;
import com.compsci702.compsci702app.Tools.DBHelper;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.InterstitialAd;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameFinishedActivity extends AppCompatActivity {

    private ArrayList<Phrase> correctlyUnscrambledPhrases;
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;

    private TextView scoreText;
    private TextView gameOverText;

    private String difficulty;
    private int score;

    private DBHelper dbHelper;
    //private InterstitialAd interstitialAd;

    HashMap<String, ArrayList<String>> listChildData = new HashMap<>();
    ArrayList<String> listHeaderData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finished);

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//
//            }
//        });

//        interstitialAd = new InterstitialAd(this);
//        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd.loadAd(new AdRequest.Builder().build());
//
//        interstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                interstitialAd.loadAd(new AdRequest.Builder().build());
//                loadGameActivity();
//            }
//        });

        expandableListView = findViewById(R.id.expandableListView);
        expandableListAdapter = new ExpandableListAdapter(this, listHeaderData, listChildData,"heart");

        scoreText = findViewById(R.id.scoreText);
        gameOverText = findViewById(R.id.gameOverText);

        Bundle bundle = getIntent().getExtras();
        correctlyUnscrambledPhrases = bundle.getParcelableArrayList("phraseList");
        score = bundle.getInt("score");
        scoreText.setText(String.format("Score: %d", score));
        difficulty = bundle.getString("difficulty");

        dbHelper = new DBHelper(this);

        if(checkForHighScore()){
            dbHelper.updateHighScore(difficulty,score);
            gameOverText.setText("New High Score!");
        }

        prepareAdapterList();

        expandableListView.setAdapter(expandableListAdapter);
    }

    public void homeClicked(View view){
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
    }

    public void favouriteClicked(View view){
        int position = (int)view.getTag();
        Phrase phrase = correctlyUnscrambledPhrases.get(position);
        String key = phrase.getPhrase() + "-" + phrase.getFavourited();

        int index = listHeaderData.indexOf(key);
        ArrayList<String> childArray = listChildData.get(key);
        String headerString = listHeaderData.get(index);
        Toast toast;

        if(phrase.getFavourited() == 0){
            ((ImageButton)view).setImageResource(R.drawable.heart_full);
            dbHelper.updateFavourited(phrase,1);
            String newKey = headerString.replace('0', '1');
            listHeaderData.set(index,newKey);
            listChildData.remove(key);
            listChildData.put(newKey,childArray);

            phrase.setFavourited(1);
            toast = Toast.makeText(this, "Added to favourites", Toast.LENGTH_SHORT);
        }else{
            ((ImageButton)view).setImageResource(R.drawable.heart_empty);
            dbHelper.updateFavourited(phrase,0);
            String newKey = headerString.replace('1', '0');
            listHeaderData.set(index,newKey);
            listChildData.remove(key);
            listChildData.put(newKey,childArray);

            phrase.setFavourited(0);
            toast = Toast.makeText(this, "Removed from favourites", Toast.LENGTH_SHORT);
        }
        System.out.println(headerString);

        toast.show();

        expandableListAdapter.notifyDataSetChanged();
    }

    public void replayClicked(View view){
        Random random = new Random();
        int randNum = random.nextInt(2);

//        if(interstitialAd.isLoaded() && randNum == 1){
//            interstitialAd.show();
//        }else {
            loadGameActivity();
        //}
    }

    private void prepareAdapterList(){
        for (int i = 0; i < correctlyUnscrambledPhrases.size(); i++){
            String phrase = correctlyUnscrambledPhrases.get(i).getPhrase() + "-" + correctlyUnscrambledPhrases.get(i).getFavourited();
            listHeaderData.add(phrase);

            ArrayList<String> definitionList = new ArrayList<>();
            definitionList.add(correctlyUnscrambledPhrases.get(i).getMeaning() +
                    "-" + correctlyUnscrambledPhrases.get(i).getExample());
            listChildData.put(phrase, definitionList);
        }
    }

    private boolean checkForHighScore(){
        Cursor cursor = dbHelper.getHighScores();

        while (cursor.moveToNext()){
            String currentDifficulty = cursor.getString(cursor.getColumnIndex("Difficulty"));

            if(difficulty.equals(currentDifficulty)){
                String previousHighScore = cursor.getString(cursor.getColumnIndex("Score"));

                if(score > Integer.parseInt(previousHighScore)){
                    return true;
                }
            }
        }
        return false;
    }

    private void loadGameActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("difficulty", difficulty);
        startActivity(intent);
    }
}
