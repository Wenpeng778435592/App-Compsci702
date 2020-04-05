package com.compsci702.compsci702app.Tools;

//rb
import android.os.CountDownTimer;
import android.widget.TextView;

public class MinuteTimer {

    //This is the text field in the UI that shows the time
    TextView timerText;
    CountDownTimer timer;

    public MinuteTimer(TextView timerText){
        this.timerText = timerText;
    }

    public void startTimer(){

        //rb start one minute timer
        timer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                timerText.setText("0");
            }
        }.start();
    }

    public void stopTimer(){
        timer.cancel();
        timerText.setText("59");
    }
}
