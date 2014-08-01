package com.stevescodingblog.androidapps.tally;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    int _tally = 1;

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
                _tally = 1;
                updateUi();
            }
        });
    }

    protected void updateUi() {

        final TextView tallyDisplay = (TextView)this.findViewById(R.id.tallyDisplay);
        tallyDisplay.setText(Integer.toString(this._tally));

    }
}
