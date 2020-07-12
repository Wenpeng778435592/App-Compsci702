package com.compsci702.compsci702app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.compsci702.compsci702app.R;
import com.compsci702.compsci702app.Tools.DBHelper;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.createDB();
    }

    public void startClicked(View view){
        String difficulty = ((TextView)view).getText().toString();
        Intent intent = new Intent(this,GameActivity.class);
        Bundle difficultyBundle = new Bundle();
        difficultyBundle.putString("difficulty", difficulty);
        intent.putExtras(difficultyBundle);
        startActivity(intent);
    }

    public void highScoreClicked(View view){
        Intent intent = new Intent(this,HighScoreActivity.class);
        startActivity(intent);
    }

    public void favouriteClicked(View view){
        Intent intent = new Intent(this,FavouriteActivity.class);
        startActivity(intent);
    }
}