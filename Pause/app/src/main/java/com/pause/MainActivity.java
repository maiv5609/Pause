package com.pause;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    SharedPreferences myPreferences;
    int bones;
    TextView dogBones;
    static final int REQUEST_OK = 1;
    static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get preference file, by this point it should already be created
        myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
        boolean existingUser = myPreferences.getBoolean("USER?", false);
        String breed = myPreferences.getString("BREED", "unknown");
        String dogName = myPreferences.getString("DOGNAME", "unknown");

        ImageView dogImage = (ImageView)findViewById(R.id.dogImageView);
        TextView dogNameDisplay = (TextView)findViewById(R.id.textViewName);
        dogBones = (TextView)findViewById(R.id.boneCount);

        if (dogName != "unknown"){
            dogNameDisplay.setText(dogName);
        }

        if (breed.equals("beagle")) {
            dogImage.setImageResource(R.drawable.beagle);
        } else if (breed.equals("shihtzu")) {
            dogImage.setImageResource(R.drawable.shih_tzu);
        } else if (breed.equals("shiba")) {
            dogImage.setImageResource(R.drawable.shiba);
        }

        //Update current bones
        dogBones = (TextView)findViewById(R.id.boneCount);
        int currBones = myPreferences.getInt("CURRBONES", 0);
        dogBones.setText("" + currBones);

        Button pauseButton = (Button)findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PopActivity.class));
            }
        });

        Button quickPauseButton = (Button)findViewById(R.id.quickPauseButton);
        quickPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,QuickPopActivity.class));
            }
        });


        ImageButton statsButton = (ImageButton)findViewById(R.id.statsButton);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StatsActivity.class);
                startActivity(intent);
            }
        });

        ImageButton storeButton = (ImageButton)findViewById(R.id.storeButton);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StoreActivity.class);
                startActivity(intent);
            }
        });


    }

    public static void closeActivity(){
        activity.finish();
    }

}
