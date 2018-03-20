package com.pause;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


public class getting_started extends AppCompatActivity {
    ImageButton gettingStartedbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getting_started);
        gettingStartedbutton = (ImageButton)findViewById(R.id.gettingStartedButton);
        gettingStartedbutton.setOnClickListener(gettingStarted);
    }

    View.OnClickListener gettingStarted = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getting_started.this, BreedSelect.class));
            finish();
        }
    };
}
