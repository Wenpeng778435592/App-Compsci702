package com.compsci702.compsci702app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.compsci702.compsci702app.R;

public class GameFinishedActivity extends AppCompatActivity {

    LinearLayout gameWonLayout;
    LinearLayout gameLostLayout;

    private boolean gameWon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finished);

        gameWonLayout = findViewById(R.id.gameWonLayout);
        gameLostLayout = findViewById(R.id.gameLostLayout);

        gameWon = getIntent().getExtras().getBoolean("success");

        if(gameWon){
            gameWonLayout.setVisibility(View.VISIBLE);
            gameLostLayout.setVisibility(View.GONE);

        }else{
            gameWonLayout.setVisibility(View.GONE);
            gameLostLayout.setVisibility(View.VISIBLE);

        }
    }

    public void homeClicked(View view){
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
    }

    public void replayClicked(View view){
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }
}
