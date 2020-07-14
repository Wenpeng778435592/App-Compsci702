package com.compsci702.compsci702app.Activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.compsci702.compsci702app.R;
import com.compsci702.compsci702app.Tools.DBHelper;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.InterstitialAd;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class StartActivity extends AppCompatActivity {

//    private AdView adBannerView;
//    private InterstitialAd interstitialAd;
    private String selectedOption;
    private View selectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.createDB();
//
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
//                if(selectedOption.equals("start")){
//                    loadStartClicked(selectedView);
//                }else if(selectedOption.equals("highScore")){
//                    loadHighScoreScreen();
//                }else if(selectedOption.equals("favourite")){
//                    loadFavouriteClicked();
//                }else{
//                    loadInfoScreen();
//                }
//                interstitialAd.loadAd(new AdRequest.Builder().build());
//            }
//        });

//        adBannerView = findViewById(R.id.adBannerView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adBannerView.loadAd(adRequest);

    }

    public void startClicked(View view){
        Random random = new Random();
        int randNum = random.nextInt(3);
//
//        if (interstitialAd.isLoaded() && randNum == 1) {
//            selectedOption = "start";
//            selectedView = view;
//            interstitialAd.show();
//        } else {
            loadStartClicked(view);
        //}
    }

    public void highScoreClicked(View view){
        Random random = new Random();
        int randNum = random.nextInt(2);

//        if (interstitialAd.isLoaded() && randNum == 1) {
//            selectedOption = "highScore";
//            interstitialAd.show();
//        } else {
            loadHighScoreScreen();
        //}
    }

    public void favouriteClicked(View view){
        Random random = new Random();
        int randNum = random.nextInt(2);

//        if (interstitialAd.isLoaded() && randNum == 1) {
//            selectedOption = "favourite";
//            interstitialAd.show();
//        } else {
            loadFavouriteClicked();
        //}
    }

    public void infoClicked(View view){
        Random random = new Random();
        int randNum = random.nextInt(2);

//        if (interstitialAd.isLoaded() && randNum == 1) {
//            selectedOption = "info";
//            interstitialAd.show();
//        } else {
            loadInfoScreen();
        //}
    }

    private void loadHighScoreScreen(){
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);
    }

    private void loadInfoScreen(){
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    private void loadFavouriteClicked(){
        Intent intent = new Intent(this,FavouriteActivity.class);
        startActivity(intent);
    }

    private void loadStartClicked(View view){
        String difficulty = ((TextView)view).getText().toString();
        Intent intent = new Intent(this,GameActivity.class);
        Bundle difficultyBundle = new Bundle();
        difficultyBundle.putString("difficulty", difficulty);
        intent.putExtras(difficultyBundle);
        startActivity(intent);
    }
}