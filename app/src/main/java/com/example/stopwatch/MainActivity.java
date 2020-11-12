package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer ();
    }

    //start the stopwatch when the start button is clicked
    public void onClickStart (View view) {
        running = true;
    }

    //stop the stopwatch when the stop button is clicked
    public void onClickStop (View view) {
        running = false;
    }

    //reset the stopwatch when the reset button is clicked
    public void onClickReset (View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer () {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();

        /*
         * Call the post() method, passing in a new Runnable. The post()
         * method processes codes without a delay, so the code in the
         * Runnable will run almost immediately.
         **/
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",
                        hours, minutes, secs);
                timeView.setText(time);

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }
}