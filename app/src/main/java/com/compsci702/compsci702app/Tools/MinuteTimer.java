package com.compsci702.compsci702app.Tools;

import android.widget.TextView;

public class MinuteTimer {

    //This is the text field in the UI that shows the time
    TextView timerText;

    public MinuteTimer(TextView timerText){
        this.timerText = timerText;
    }

    //Starts the timer. Counts down from one minute (01:00). Sets the
    //textField with the new time every second (01:00, 00:59, etc)
    public void startTimer(){

    }
}
