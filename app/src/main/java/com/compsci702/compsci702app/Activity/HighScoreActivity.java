package com.compsci702.compsci702app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.compsci702.compsci702app.R;
import com.compsci702.compsci702app.Tools.DBHelper;

public class HighScoreActivity extends AppCompatActivity {

    private TextView easyText;
    private TextView mediumText;
    private TextView hardText;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        easyText = findViewById(R.id.easyText);
        mediumText = findViewById(R.id.mediumText);
        hardText = findViewById(R.id.hardText);

        dbHelper = new DBHelper(this);

        Cursor cursor = dbHelper.getHighScores();
        while (cursor.moveToNext()){
            String difficulty = cursor.getString(cursor.getColumnIndex("Difficulty"));
            String score = cursor.getString(cursor.getColumnIndex("Score"));

            if(difficulty.equals("Easy")){
                easyText.setText(score);
            }else if(difficulty.equals("Medium")){
                mediumText.setText(score);
            }else{
                hardText.setText(score);
            }
        }
    }

    public void homeClicked(View view){
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
    }

}
