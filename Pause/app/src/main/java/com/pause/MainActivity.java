package com.pause;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get preference file, by this point it should already be created
        SharedPreferences myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
        boolean existingUser = myPreferences.getBoolean("USER?", false);

        String breed = myPreferences.getString("BREED", "unknown");
        ImageView dogImage = (ImageView)findViewById(R.id.dogImageView);

        if (breed.equals("beagle")) {
            dogImage.setImageResource(R.drawable.beagle);
        } else if (breed.equals("shihtzu")) {
            dogImage.setImageResource(R.drawable.shihtzu);
        } else if (breed.equals("shiba")) {
            dogImage.setImageResource(R.drawable.shiba);
        }



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


        Button statsButton = (Button)findViewById(R.id.statsButton);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StatsActivity.class);
                startActivity(intent);
            }
        });

        Button storeButton = (Button)findViewById(R.id.storeButton);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StoreActivity.class);
                startActivity(intent);
            }
        });


    }

    // setRingerMode(this, AudioManager.RINGER_MODE_SILENT);
    /*
    private void setRingerMode(Context context, int mode) {

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Check for DND permissions for API 24+
        if (android.os.Build.VERSION.SDK_INT < 24 || (android.os.Build.VERSION.SDK_INT >= 24 && !nm.isNotificationPolicyAccessGranted())) {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(mode);
        }
    }
    */
}
