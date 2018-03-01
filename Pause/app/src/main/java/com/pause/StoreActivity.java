package com.pause;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by turner36 on 2/27/18.
 */

public class StoreActivity extends Activity {

    ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_layout);
        homeButton = (ImageButton)findViewById(R.id.storeHomeButton);
        homeButton.setOnClickListener(selectHome);
    }

    View.OnClickListener selectHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
