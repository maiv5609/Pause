package com.pause;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by turner36 on 2/27/18.
 */

public class StatsActivity extends AppCompatActivity {

    ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_layout);

        homeButton = (ImageButton)findViewById(R.id.statsHomeButton);
        homeButton.setOnClickListener(selectHome);
    }

    View.OnClickListener selectHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


}
