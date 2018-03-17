package com.pause;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

/**
 * Created by summerturner on 2/23/18.
 */

public class PopActivity extends Activity {

    SharedPreferences myPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.4));

        myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);

        /**
         * Spinner for the dropdown to select minutes for pause time.
         */
        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"30 minutes", "45 minutes", "60 minutes"};
        // Create adapter to describe items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        // Set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        /* Get the set time selected and store in user data preferences. */
        String text = dropdown.getSelectedItem().toString();
        int digits = Integer.parseInt(text.replaceAll("[^0-9.]", ""));
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt("PAUSE_TIME", digits);
        myEditor.commit();

        Button confirmButton = (Button)findViewById(R.id.confirmPause);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PopActivity.this,PauseActivity.class));
                finish();
            }
        });

        Button cancelButton = (Button)findViewById(R.id.cancelPause);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
