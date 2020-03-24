package com.compsci702.compsci702app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.compsci702.compsci702app.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void startClicked(View view){
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }

}
