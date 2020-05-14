package com.compsci702.compsci702app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.compsci702.compsci702app.R;

import java.io.UnsupportedEncodingException;

public class GameFinishedActivity extends AppCompatActivity {

    private LinearLayout gameWonLayout;
    private LinearLayout gameLostLayout;

    private TextView gameOverText;
    private TextView gameOverDescription;
    private TextView congratulationsText;
    private TextView congratulationsDescription;

    private boolean gameWon;

    private String string1 = "V293LCB5b3UgbWFkZSBpdCB0byB0aGUgZW5kLiBZb3UgYXJlIGEgdW5zY3JhbWJsaW5nIGNoYW1waW9uIQ==";
    private String string2 = "WW91IFdvbiE=";
    private String string3 = "R2FtZSBPdmVy";
    private String string4 = "VG9vIGJhZCwgYmV0dGVyIGx1Y2sgbmV4dCB0aW1l";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finished);

        gameWonLayout = findViewById(R.id.gameWonLayout);
        gameLostLayout = findViewById(R.id.gameLostLayout);

        gameOverText = findViewById(R.id.gameOverText);
        gameOverDescription = findViewById(R.id.gameOverDescription);
        congratulationsText = findViewById(R.id.congratulationsText);
        congratulationsDescription = findViewById(R.id.congratulationsDescription);

        gameWon = getIntent().getExtras().getBoolean("success");

        if(gameWon){
            gameWonLayout.setVisibility(View.VISIBLE);
            gameLostLayout.setVisibility(View.GONE);

            congratulationsText.setText(decrypt(string2));
            congratulationsDescription.setText(decrypt(string1));

        }else{
            gameWonLayout.setVisibility(View.GONE);
            gameLostLayout.setVisibility(View.VISIBLE);

            gameOverText.setText(decrypt(string3));
            gameOverDescription.setText(decrypt(string4));

        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void homeClicked(View view){
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
    }

    public void replayClicked(View view){
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }

    private String decrypt(String string){
        String decryptB64Text = null;
        byte[] decryptB64Byte = Base64.decode(string, Base64.DEFAULT);
        try {
            decryptB64Text = new String(decryptB64Byte, "UTF-8");
            //System.out.println("decryptB64 " + decryptB64Text);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("decoded " + decryptB64Text);
        return decryptB64Text;
    }
}
