package com.stevescodingblog.androidapps.tally;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
                Toast.makeText(getApplicationContext(), "Tapped!", Toast.LENGTH_LONG);
            }
        });
    }
}
