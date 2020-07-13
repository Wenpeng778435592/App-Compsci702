package com.compsci702.compsci702app.Activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.compsci702.compsci702app.R;
import com.compsci702.compsci702app.Tools.DBHelper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class StartActivity extends AppCompatActivity {

    private AdView adBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.createDB();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        adBannerView = findViewById(R.id.adBannerView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adBannerView.loadAd(adRequest);

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