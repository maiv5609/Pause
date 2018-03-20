package com.pause;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by turner36 on 2/27/18.
 */

public class StoreActivity extends AppCompatActivity {

    ImageButton homeButton;
    TextView dogBones;
    SharedPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_layout);
        homeButton = (ImageButton)findViewById(R.id.storeHomeButton);
        homeButton.setOnClickListener(selectHome);

        myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
        dogBones = (TextView)findViewById(R.id.boneCount);
        int currBones = myPreferences.getInt("CURRBONES", 0);
        dogBones.setText("" + currBones);
    }

    View.OnClickListener selectHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
