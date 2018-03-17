package com.pause;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Context;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by summerturner on 2/24/18.
 *
 * Uses android-gif-drawable for animated GIFs https://github.com/koral--/android-gif-drawable
 */

public class PauseActivity extends Activity {

    SharedPreferences myPreferences;
    TextView timeTextView;
    Timer t;
    int pauseTime;

    Button unlockButton, enableButton;
    static final int RESULT_ENABLE = 1;
    DevicePolicyManager devicePolicyManager;
    ComponentName componentName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pause_layout);

        myPreferences = this.getSharedPreferences(getString(R.string.preferenceKey), Context.MODE_PRIVATE);
        String breed = myPreferences.getString("BREED", "unknown");
        GifImageView dogImage = (GifImageView)findViewById(R.id.gifImageView);

        if (breed.equals("beagle")) {
            dogImage.setImageResource(R.drawable.beagle_sleeping);
        } else if (breed.equals("shihtzu")) {
            dogImage.setImageResource(R.drawable.shih_tzu_sleeping);
        } else if (breed.equals("shiba")) {
            dogImage.setImageResource(R.drawable.shiba_sleeping);
        }

        // enableButton = (Button) findViewById(R.id.enableButton);
        // lockButton = (Button) findViewById(R.id.lockButton);


        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        //componentName = new ComponentName(PauseActivity.this, AdminReceiver.class);
        boolean active = devicePolicyManager.isAdminActive(componentName);

        /*
        if (active) {
            enableButton.setText("DISABLE");
            // lockButton.setVisibility(View.VISIBLE);
        } else {
            enableButton.setText("ENABLE");
            // lockButton.setVisibility(View.GONE);
        }
        enableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean active = devicePolicyManager.isAdminActive(componentName);

                lockScreen();
                if (active) {
                    devicePolicyManager.removeActiveAdmin(componentName);
                    enableButton.setText("ENABLE");
                    // lockButton.setVisibility(View.GONE);
                } else {
                    Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                    intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
                    intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "You Should Enable the app!");
                    startActivityForResult(intent, RESULT_ENABLE);
                }
            }
        });
        */


        /*
        Button lockButton = (Button)findViewById(R.id.lockButton);
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devicePolicyManager.lockNow();

            }
        });
        */



        int selectedTime = myPreferences.getInt("PAUSE_TIME", 0);
        pauseTime = selectedTime * 60;

        /**
         * Timer increments count when activity starts until unlock button is clicked.
         *
         */
        t = new Timer();

        timeTextView = (TextView)findViewById(R.id.timeTextView);
        timeTextView.setText(
                String.format("%02d:%02d:%02d", pauseTime / 3600,
                        (pauseTime % 3600) / 60, (pauseTime % 60)));


        if (active) {
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
        }

        Button unlockButton = (Button)findViewById(R.id.unlockButton);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.cancel();
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case RESULT_ENABLE:
                if(resultCode == Activity.RESULT_OK ){
                    // enableButton.setText("DISABLE");
                    // lockButton.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "DISABLE", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
                return;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void lockScreen() {
        PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        if (pm.isScreenOn()) {
            DevicePolicyManager policy = (DevicePolicyManager)
                    getSystemService(Context.DEVICE_POLICY_SERVICE);
            try {
                policy.lockNow();
            } catch (SecurityException ex) {
                Toast.makeText(
                        PauseActivity.this,
                        "You must enable this app as a device administrator\n\n" +
                                "Please enable it and press back button to return here.",
                        Toast.LENGTH_LONG).show();
                //ComponentName admin = new ComponentName(PauseActivity.this, AdminReceiver.class);
               // Intent intent = new Intent(
                       // DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).putExtra(
                       // DevicePolicyManager.EXTRA_DEVICE_ADMIN, admin);
                //PauseActivity.this.startActivity(intent);
            }
        }
    }
}
