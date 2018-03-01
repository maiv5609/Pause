package com.pause;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by turner36 on 2/27/18.
 */

public class StatsActivity extends Activity {

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
