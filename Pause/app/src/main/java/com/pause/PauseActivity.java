package com.pause;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.media.AudioManager;
import android.content.Context;
import android.app.NotificationManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by summerturner on 2/24/18.
 */

public class PauseActivity extends Activity {

    SharedPreferences myPreferences;
    TextView timeTextView;
    Timer t;
    int pauseTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pause_layout);

        myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
        int selectedTime = myPreferences.getInt("PAUSE_TIME", 0);
        pauseTime = selectedTime * 60;

        /**
         * Timer increments count when activity starts until unlock button is clicked.
         * TODO: Need to display the time remaining NOT the current count time
         */
        t = new Timer();
        timeTextView = (TextView)findViewById(R.id.timeTextView);
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        timeTextView.setText(
                        String.format("%02d:%02d:%02d", pauseTime / 3600,
                                (pauseTime % 3600) / 60, (pauseTime % 60)));

                        pauseTime--;
                    }
                });
            }
        }, 1000, 1000);

        Button unlockButton = (Button)findViewById(R.id.unlockButton);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: stop Do Not Disturb
                t.cancel();

                // finish closes the activity
                finish();
            }
        });

    }

}
