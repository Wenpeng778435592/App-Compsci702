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

import java.util.ArrayList;
import java.util.HashMap;

public class GameFinishedActivity extends AppCompatActivity {

    private ArrayList<Phrase> correctlyUnscrambledPhrases;
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;

    private TextView scoreText;
    private TextView gameOverText;

    private String difficulty;
    private int score;

    private DBHelper dbHelper;

    HashMap<String, ArrayList<String>> listChildData = new HashMap<>();
    ArrayList<String> listHeaderData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finished);

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
        Toast toast;

        if(phrase.getFavourited() == 0){
            ((ImageButton)view).setImageResource(R.drawable.heart_full);
            dbHelper.updateFavourited(phrase,1);
            phrase.setFavourited(1);
            toast = Toast.makeText(this, "Added to favourites", Toast.LENGTH_SHORT);
        }else{
            ((ImageButton)view).setImageResource(R.drawable.heart_empty);
            dbHelper.updateFavourited(phrase,0);
            phrase.setFavourited(0);
            toast = Toast.makeText(this, "Removed from favourites", Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public void replayClicked(View view){
        Intent intent = new Intent(this,GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("difficulty", difficulty);
        startActivity(intent);
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
}
