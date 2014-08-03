package com.stevescodingblog.androidapps.tally;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class MainActivity extends Activity {

    int _tally = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final View layoutRoot = this.findViewById(R.id.layoutRoot);
        layoutRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            _tally += 1;
            updateUi();
            }
        });

        final Button resetButton = (Button) this.findViewById(R.id.tallyButton);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            _tally = 0;
            updateUi();
            }
        });

        this.updateUi();
    }

    protected void updateUi() {

        final TextView tallyDisplay = (TextView)this.findViewById(R.id.tallyDisplay);
        tallyDisplay.setText(Integer.toString(this._tally));
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.loadState();
        this.updateUi();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveState();
    }

    private void loadState() {
        // See if we have previously saved the count, and load it
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        this._tally = prefs.getInt("count", 0);
    }

    private void saveState() {
        // Get the preferences and the editor for the preferences
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Store our count..
        editor.putInt("count", this._tally);

        // Save changes
        editor.commit();
    }
}
