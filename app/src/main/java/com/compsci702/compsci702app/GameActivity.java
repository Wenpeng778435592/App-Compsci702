package com.compsci702.compsci702app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity {

    LinearLayout nextLevelLayout;
    LinearLayout mainGameLayout;

    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        nextLevelLayout = findViewById(R.id.nextLevelLayout);
        mainGameLayout = findViewById(R.id.mainGameLayout);
        inputText = findViewById(R.id.inputText);

    }

    public void showKeyboard(EditText inputTextField) {
        inputTextField.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public void nextLevelStartClicked(View view){
        nextLevelLayout.setVisibility(View.GONE);
        mainGameLayout.setVisibility(View.VISIBLE);
        showKeyboard(inputText);
    }
}
