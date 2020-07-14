package com.compsci702.compsci702app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.compsci702.compsci702app.R;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class InfoActivity extends AppCompatActivity {

//    AdView adBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

//        adBannerView = findViewById(R.id.adBannerView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adBannerView.loadAd(adRequest);
    }

    public void homeClicked(View view){
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
    }
}