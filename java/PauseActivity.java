package com.pause;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by summerturner on 2/24/18.
 */

public class PauseActivity extends Activity {

    TextView timeTextView;
    Timer t;
    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pause_layout);

        /**
         * Timer increments count when activity starts until unlock button is clicked.
         * TODO: Need to display the time remaining NOT the current count time
         */
        t = new Timer();
        timeTextView = (TextView)findViewById(R.id.timeTextView);
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        // TODO: set text to time remaining
                        timeTextView.setText("00:"+count);
                        count++;
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
