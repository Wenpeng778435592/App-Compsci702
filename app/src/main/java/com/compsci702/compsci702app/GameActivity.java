package com.compsci702.compsci702app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    LinearLayout nextLevelLayout;
    LinearLayout mainGameLayout;

    EditText inputText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        nextLevelLayout = findViewById(R.id.nextLevelLayout);
        mainGameLayout = findViewById(R.id.mainGameLayout);
        inputText = findViewById(R.id.inputText);
        textView = findViewById(R.id.textView);

        inputText.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String targetText = textView.getText().toString();
                SpannableString spannableString = new SpannableString(targetText);

                textView.setText(getColourMatchedString(spannableString, s.toString()));

                if(s.equals(spannableString)){
                    //call matched method (get next word)
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

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

    public SpannableString getColourMatchedString(SpannableString targetText, String currentText){

        for(int i=0; i< targetText.length(); i++){
            if(i > currentText.length()-1){
                ForegroundColorSpan fcsgrey = new ForegroundColorSpan(getResources().getColor(R.color.darkGrey));
                targetText.setSpan(fcsgrey ,i ,i+1 ,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            }else if(currentText.charAt(i) == targetText.charAt(i)){
                ForegroundColorSpan fcsgreen = new ForegroundColorSpan(getResources().getColor(R.color.green));
                targetText.setSpan(fcsgreen ,i ,i+1 ,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            }else{
                ForegroundColorSpan fcsred = new ForegroundColorSpan(getResources().getColor(R.color.red));
                targetText.setSpan(fcsred ,i ,i+1 ,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }

        return targetText;
    }
}
