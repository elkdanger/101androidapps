package com.stevescodingblog.androidapps.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity {

    final int HANDLER_DELAY_MS = 100;

    final Handler _loopHandler = new Handler();
    Date _startDate = null;
    Boolean _isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Wire up button clicks
        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(_isRunning)
                    stop();
                else
                    start();
            }
        });

    }

    private void start() {

        if(_isRunning)
            return;

        // Change the text on the button
        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setText(R.string.stop);

        // Set a flag to show that we're running
        this._isRunning = true;

        // Record the date at which we started
        _startDate = new Date();

        updateUi();

        // Start a handler to update every 100ms
        _loopHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(_isRunning) {

                    // Update UI
                    updateUi();

                    // Wait again
                    _loopHandler.postDelayed(this, HANDLER_DELAY_MS);
                }
            }
        }, HANDLER_DELAY_MS);
    }

    private void stop() {

        if(!_isRunning)
            return;

        // Change the text on the button
        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setText(R.string.start);

        // Flag that we're not running
        this._isRunning = false;
    }

    private void updateUi() {

        Date now = new Date();

        long nowSeconds = now.getTime();
        long startSeconds = _startDate.getTime();
        double total = (nowSeconds - startSeconds) * 0.001;

        TextView currentTime = (TextView)findViewById(R.id.totalTime);
        currentTime.setText(String.valueOf(total));
    }
}
