package com.pause;

import android.app.Activity;
import android.content.Intent;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.4));

        /**
         * Spinner for the dropdown to select minutes for pause time.
         */
        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"30 minutes", "45 minutes", "60 minutes"};
        // Create adapter to describe items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        // Set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

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
