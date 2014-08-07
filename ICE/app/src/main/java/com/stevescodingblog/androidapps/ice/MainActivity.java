package com.stevescodingblog.androidapps.ice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;


public class MainActivity extends Activity {

    String _currentPhoneNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button callButton = (Button)this.findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(_currentPhoneNumber != null && !_currentPhoneNumber.isEmpty()) {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel:" + Uri.encode(_currentPhoneNumber)));
                    startActivity(dialIntent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter a contact phone number first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final EditText contactNumber = (EditText)findViewById(R.id.emergencyContactNumber);
        contactNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                _currentPhoneNumber = contactNumber.getText().toString();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    protected void loadData() {

        EditText contactName = (EditText)this.findViewById(R.id.emergencyContactText);
        EditText contactNumber = (EditText)this.findViewById(R.id.emergencyContactNumber);
        EditText ownerName = (EditText)this.findViewById(R.id.phoneOwnerName);
        EditText ownerNotes = (EditText)this.findViewById(R.id.phoneOwnerNotes);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        contactName.setText(preferences.getString("contactName", ""));
        contactNumber.setText(preferences.getString("contactNumber", ""));
        ownerName.setText(preferences.getString("ownerName", ""));
        ownerNotes.setText(preferences.getString("ownerNotes", ""));
    }

    protected void saveData() {

        EditText contactName = (EditText)this.findViewById(R.id.emergencyContactText);
        EditText contactNumber = (EditText)this.findViewById(R.id.emergencyContactNumber);
        EditText ownerName = (EditText)this.findViewById(R.id.phoneOwnerName);
        EditText ownerNotes = (EditText)this.findViewById(R.id.phoneOwnerNotes);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("contactName", contactName.getText().toString());
        editor.putString("contactNumber", contactNumber.getText().toString());
        editor.putString("ownerName", ownerName.getText().toString());
        editor.putString("ownerNotes", ownerNotes.getText().toString());

        editor.commit();
    }
}
